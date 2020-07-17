package com.pluralsight.module1;
import com.pluralsight.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import static org.junit.Assert.*;

import java.io.IOException;

public class Module1_Task1_IT {

  private String indexUrl;
  private WebClient webClient;
  HtmlPage page;

  @Before
    public void setUp() throws IOException {
      indexUrl = "http://localhost:8080"; //System.getProperty("integration.base.url");
      webClient = new WebClient();
      // Open the admin page
      page = webClient.getPage(indexUrl + "/books/admin");
    }
    @After
    public void tearDown() {
      webClient.closeAllWindows();
    }

    // Verify the edit and delete hrefs, in BookAdmin.jsp contain the id
    @Test
    public void _task1() {
      url_contains_id("Delete");
      url_contains_id("Edit");
    }

  
  
  private boolean findURLWithID(String urlStr) {
      String foundURL = "";
      try {
        for (  HtmlAnchor a : page.getAnchors()) {
          String href = a.getHrefAttribute();
          if (href.contains(urlStr)) {
            foundURL = a.getHrefAttribute().toString();
            break;
          }
        }
      }
      catch (  ElementNotFoundException e) {
        return false;
      }
      foundURL = foundURL.replaceAll("\\s+","");
      // Might have different id's in the database so remove them.
      foundURL = foundURL.replaceAll("[0-9]","");
      String testingURL = urlStr+"?id=";
      return foundURL.equals(testingURL);
    }
}


