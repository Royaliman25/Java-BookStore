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


