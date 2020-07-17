package com.pluralsight.module2;
import com.pluralsight.*;

import static org.junit.Assert.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;
import java.io.*;


public class Module2_Task3_thru_6_IT extends Mockito{

   static StringWriter stringWriter = new StringWriter();
   static String tempID = "1";
  static int tempIntID = 1;
   static HttpServletRequest request;
   static HttpServletResponse response;
   static RequestDispatcher mockRequestDispatcher;
   static Book mockBook;

  @Mock
  private BookDAO mockBookDAO;

  @InjectMocks
  private ControllerServlet controllerServlet;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

      request = mock(HttpServletRequest.class);
      response = mock(HttpServletResponse.class);
      mockRequestDispatcher = mock(RequestDispatcher.class);
      mockBook = mock(Book.class);

      when(request.getPathInfo()).thenReturn("/edit");
      when(request.getParameter("id")).thenReturn(tempID);
      when(mockBookDAO.getBook(tempIntID)).thenReturn(mockBook);
      when(request.getRequestDispatcher("/BookForm.jsp"))
                         .thenReturn(mockRequestDispatcher);

      try {
         controllerServlet.doGet(request, response);
     } catch (Exception e) {}
  }

