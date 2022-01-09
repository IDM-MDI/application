package edu.by.ishangulyev.application.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool
{
    private static ConnectionPool INSTANCE;
    private static final AtomicBoolean aBoolean = new AtomicBoolean(false);
    private static final ReentrantLock rl = new ReentrantLock();
    private final ConnectionSetting setting = ConnectionSetting.getInstance();

    private final int POOL_SIZE = 16;
    private final BlockingQueue<ProxyConnection> free;
    private final BlockingQueue<ProxyConnection> used;


    private ConnectionPool()
    {
        free = new LinkedBlockingDeque<>(POOL_SIZE);
        used = new LinkedBlockingDeque<ProxyConnection>(POOL_SIZE);
        fillConnection();
    }


    public static ConnectionPool getINSTANCE()
    {
        if(!aBoolean.get())
        {
            rl.lock();
            try
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new ConnectionPool();
                    aBoolean.set(true);
                }
            }
            finally
            {
                rl.unlock();
            }
        }
        return INSTANCE;
    }

    public Connection getConnection() throws InterruptedException
    {
        ProxyConnection connection = null;

        connection = free.take();
        used.put(connection);

        return connection;
    }

    public boolean releaseConnection(Connection proxyConnection)
    {
        boolean result = true;
        used.remove((ProxyConnection) proxyConnection);
        try
        {
            free.put((ProxyConnection) proxyConnection);
        }
        catch (InterruptedException e)
        {

            result = false;
        }
        return result;
    }

    public boolean closeConnection()
    {
        boolean result = true;
        for (int i = 0; i < POOL_SIZE; i++)
        {
            try
            {
                free.take().release();
                used.take().release();
            }
            catch (SQLException | InterruptedException e)
            {
                e.printStackTrace();
                result = false;
            }
        }

        return result;
    }

    private void fillConnection()
    {
        for (int i = 0; i < POOL_SIZE; i++) {
            ProxyConnection connection = null;
            try
            {
                connection = new ProxyConnection(setting.getConnection());
                free.offer(connection);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
