package fr.fms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBAccess {
    private static DBAccess single_instance = null;
 
    /* ------------ ATTRIBUTES ------------ */
    
    private String url;
    private String username;
    private String password;
    private String driver;
	public Properties credentials = new Properties();
	public Connection connection;
    
    /* ------------ ACCESSORS ------------ */
 
    public Properties getCredentials() {
		return credentials;
	}

	public void setCredentials(Properties credentials) {
		this.credentials = credentials;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	/* ------------ CONSTRUCTORS ------------ */

    private DBAccess()
    {
        url = "Hello I am a string part of Singleton class";
        driver = "";
        username = "";
        password = "";
    }
 
	/* ------------ METHODS ------------ */  
    
    public static synchronized DBAccess getInstance()
    {
        if (single_instance == null)
            single_instance = new DBAccess();
 
        return single_instance;
    }

	public void initCredentials() {
		try {
			getCredentials().load(new FileInputStream("credentials.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver = this.getCredentials().getProperty("driver");
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		url = this.getCredentials().getProperty("url");
		username = this.getCredentials().getProperty("username");
		password = this.getCredentials().getProperty("password");

		try {
			this.setConnection(DriverManager.getConnection(url, username, password));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}