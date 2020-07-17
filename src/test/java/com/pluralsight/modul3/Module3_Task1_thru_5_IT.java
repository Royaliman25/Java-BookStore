// package Modul 3
package com.pluralsight.module3;

// import pluralsight
import com.pluralsight.*;

//import static org.junit.Assert
import static org.junit.Assert.*;

//import java sql Connection
import java.sql.Connection;

//import java sql DriverManager
import java.sql.DriverManager;

//import java sql PreparedStatement
import java.sql.PreparedStatement;

//import Before Class
import org.junit.BeforeClass;

//import Test
import org.junit.Test;

//import Before
import org.junit.Before;

//import mockito
import org.mockito.Mockito;

//import runner
import org.junit.runner.RunWith;

//import Mockito api
import org.powermock.api.mockito.PowerMockito;

//import code classloder
import org.powermock.core.classloader.annotations.PrepareForTest;

//import modules
import org.powermock.modules.junit4.PowerMockRunner;

//import method java lang reflact
import java.lang.reflect.Method;

//import java input output
import java.io.*;

// PowerMockRunner
@RunWith(PowerMockRunner.class)

// DriverManager
@PrepareForTest({DriverManager.class, PreparedStatement.class, BookDAO.class})

// Module3_Task1_thru_5_IT 
public class Module3_Task1_thru_5_IT {

    static Method method = null;
    static String sql = "UPDATE book SET title = ?, author = ?, price = ?" +
               " WHERE id = ?";
    
    // spyConnection
    Connection spyConnection;
    
    //PreparedStatement
    PreparedStatement mockStatement;

    //static BookDAO
    static BookDAO bookDAO;

    //static spyBookDAO
    static BookDAO spyBookDAO;

    //static called_prepareStatement 
    static boolean called_prepareStatement = false;

    //static called_setTitle 
    static boolean called_setTitle = false;

    //static called_setAuthor 
    static boolean called_setAuthor = false;

    //static called_setPrice 
    static boolean called_setPrice = false;

    //static called_setId 
    static boolean called_setId = false;

    //static called_executeUpdate 
    static boolean called_executeUpdate = false;

    //static called_close 
    static boolean called_close = false;

    //static message
    static String message = "";


    

    @Before
    /*String errorMsg = "private void setUp () 
        does not exist in ControllerServlet";
        assertNotNull(errorMsg, deleteMethod);*/

    public void setUp() {
      spyConnection = Mockito.mock(Connection.class);
      mockStatement = Mockito.mock(PreparedStatement.class);
      bookDAO = new BookDAO(spyConnection);
      spyBookDAO = Mockito.spy(bookDAO);
      
      //new book
      Book tempBookObject = new Book(1, "1984", "George Orwell", 1.50f);
      //Update Book
      try {
         Mockito.when(spyConnection.prepareStatement(sql)).thenReturn(mockStatement);
         method =  BookDAO.class.getMethod("updateBook", Book.class);
         method.invoke(spyBookDAO, tempBookObject);
      } catch (Exception e) {
         //e.printStackTrace();
      }
    }


    /* Verify updateBook() 
     method exists in BookDAO*/
    //task 1
    @Test
    public void _task1() throws Exception {
      /*doesn't exist in BookDAO.java*/
      message = "The method updateBook() doesn't exist in BookDAO.java.";
      assertNotNull(message, method);
    }

    @Test
    public void _task2() throws Exception {
      try {
        Mockito.verify(spyConnection).prepareStatement(sql);
        called_prepareStatement = true;
      } catch (Throwable e) {}

      message = "The method updateBook() doesn't call prepareStatement() correctly.";
      assertTrue(message, called_prepareStatement);
    }

    //task 3
@Test
    public void _task3() throws Exception {
      try {
        Mockito.verify(mockStatement).setString(1, "1984");
        //call title
        called_setTitle = true;
        Mockito.verify(mockStatement).setString(2, "George Orwell");
        //called author
        called_setAuthor = true;
      } catch (Throwable e) {}
      // The method updateBook() doesn't call setString() for the title
      message = "The method updateBook() doesn't call setString() for the title.";
      assertTrue(message, called_setTitle);
      // The method updateBook() doesn't call setString() for the author
      message = "The method updateBook() doesn't call setString() for the author.";
      assertTrue(message, called_setAuthor);
    }


    
    //Task 5   
 public void _task5() throws Exception {
      try {
        //executeUpdate
        Mockito.verify(mockStatement).executeUpdate();
        //call executeUpdate
        called_executeUpdate = true;
        
        Mockito.verify(mockStatement).close();
        //close
        called_close = true;
      } catch (Throwable e) {}

      // doesn't call executeUpdate
      message = "The method updateBook() doesn't call executeUpdate().";
      
      assertTrue(message, called_executeUpdate);
      
      //doesn't call PreparedStatement 
      message = "The method updateBook() doesn't call PreparedStatement close().";

      assertTrue(message, called_close);
    }

}

