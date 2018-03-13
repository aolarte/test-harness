package com.andresolarte.harness.gae.servlets;

import com.google.appengine.api.utils.SystemProperty;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

//@WebServlet(name = "CronTask", value = "/cron")
public class CronTask extends HttpServlet {

  private static final Logger log = Logger.getLogger(CronTask.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    Properties properties = System.getProperties();

    response.setContentType("text/plain");
    response.getWriter().println("Hello App Engine - Standard using "
        + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));


  }

}
