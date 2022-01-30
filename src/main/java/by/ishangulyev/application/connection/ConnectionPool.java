package by.ishangulyev.application.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final AtomicBoolean instanceCreator = new AtomicBoolean(false);
    private static final ReentrantLock lock = new ReentrantLock();
    private final ConnectionSetting setting = ConnectionSetting.getInstance();

    private final int POOL_SIZE = 16;
    private final BlockingQueue<ProxyConnection> free;
    private final BlockingQueue<ProxyConnection> used;


    private ConnectionPool() {
        free = new LinkedBlockingDeque<>(POOL_SIZE);
        used = new LinkedBlockingDeque<>(POOL_SIZE);
        fillConnection();
    }


    public static ConnectionPool getInstance() {
        if (!instanceCreator.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreator.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        boolean result = true;
        ProxyConnection proxyConnection = (ProxyConnection) connection;
        used.remove(proxyConnection);
        try {
            free.put(proxyConnection);
        } catch (InterruptedException e) {

            result = false;
        }
        return result;
    }

    public boolean closeConnection() {
        boolean result = true;
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                free.take().release();
                used.take().release();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    private void fillConnection() {
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection connection = null;
            try {
                connection = new ProxyConnection(setting.getConnection());
                free.offer(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
