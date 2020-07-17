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
