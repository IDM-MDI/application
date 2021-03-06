package by.ishangulyev.application.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSetting {
    private static final ConnectionSetting INSTANCE = new ConnectionSetting();
    private final String PATH = "property/database.properties";
    private String url;
    private String username;
    private String password;

    private ConnectionSetting() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PATH)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        } catch (IOException | ClassNotFoundException | NoSuchMethodException ex) {
            //ex.printStackTrace();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionSetting getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
