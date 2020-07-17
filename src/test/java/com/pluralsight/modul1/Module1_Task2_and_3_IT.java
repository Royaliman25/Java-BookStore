package com.pluralsight.module1;
import com.pluralsight.*;

import static org.junit.Assert.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



@RunWith(PowerMockRunner.class)
@PrepareForTest(ControllerServlet.class)
public class Module1_Task2_and_3_IT {

  private ControllerServlet controllerServlet;
  private Method method = null;

  @Before
  public void setUp() throws Exception {
    try {
      method = Whitebox.getMethod(ControllerServlet.class,
                "deleteBook", HttpServletRequest.class, HttpServletResponse.class);
    } catch (Exception e) {}
}

  @Test
    public void _task3() throws Exception {
       String tempID = "0";
       String errorMsg = "private void deleteBook() does not exist in ControllerServlet";
       assertNotNull(errorMsg, method);

       ControllerServlet controllerServlet = PowerMockito.spy(new ControllerServlet());
       boolean called_deleteBook = false;
       HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
       HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

       try {
         Mockito.when(request.getPathInfo()).thenReturn("/delete");
         //PowerMockito.doNothing().when(controllerServlet, "deleteBook", request, response);
         Mockito.when(request.getParameter("id")).thenReturn(tempID);
       } catch (MethodNotFoundException e) {}

       try {
        controllerServlet.doGet(request, response);
        try {
           PowerMockito.verifyPrivate(controllerServlet)
                       .invoke("deleteBook", request, response);
           called_deleteBook = true;
        } catch (Throwable e) {}
       } catch (Exception e) {}

       errorMsg = "After action \"" + "/delete" +
                         "\", did not call deleteBook().";
       assertTrue(errorMsg, called_deleteBook);
    }
}


