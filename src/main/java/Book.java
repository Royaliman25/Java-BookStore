package com.pluralsight;

public class Book {
	int id;
	String title;
	String author;
	float price;

	public Book(String title, String author, float price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public Book(int id, String title, String author, float price) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
	}
@Override
	public String toString() {
		return "(" + title + ", " + author + ", " + price + ")";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}


package com.pluralsight;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
  private Connection jdbcConnection;

  public DBConnection() {
    connect();
  }

  public Connection getConnection() {
    return jdbcConnection;
  }

  public void connect()  {
    try {
      Class.forName("org.sqlite.JDBC");
      jdbcConnection = DriverManager.getConnection("jdbc:sqlite:book_store.db");
      System.out.println("Opened database successfully");

      createTableIfNotExists();
    } catch ( Exception e ) {
     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     System.exit(0);
   }
 }

	
	private void createTableIfNotExists() {
   try {
       DatabaseMetaData meta = jdbcConnection.getMetaData();
       ResultSet res = meta.getTables(null, null, null, new String[] {"TABLE"});
       Statement stmt = jdbcConnection.createStatement();
       if (!res.next()) {
       	// Create table

           String sql = "CREATE TABLE book " +
                          "(id INTEGER PRIMARY KEY NOT NULL," +
                          " title TEXT NOT NULL, " +
                          " author TEXT NOT NULL, " +
                          " price REAL)";
           stmt.executeUpdate(sql);

           sql = "INSERT INTO book (title, author, price) VALUES (\"1984\", \"George Orwell\", 1.00)";
           stmt.executeUpdate(sql);

           stmt.close();
       }
    } catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       System.exit(0);
    }
 }


  public void disconnect() {
    try {
    	if (jdbcConnection != null && !jdbcConnection.isClosed()) {
    	    jdbcConnection.close();
    	}
    } catch (SQLException e) {
    	e.printStackTrace();
    }
  }
}
