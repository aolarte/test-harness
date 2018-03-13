package com.andresolarte.harness.gae.servlets;

import com.andresolarte.harness.gae.services.CacheService;
import com.andresolarte.harness.gae.services.DataService;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "InfoServlet", value = "/info")
public class InfoServlet extends HttpServlet {



    private static final Logger LOG = Logger.getLogger(InfoServlet.class.getName());

    private final DataService datastoreService;
    private final CacheService cacheService;

    @Inject
    public InfoServlet(DataService datastoreService, CacheService cacheService) {
        this.datastoreService = datastoreService;
        this.cacheService = cacheService;
    }


    @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

        Key key = datastoreService.addEntity();
        try {
            Object datastoreObject = datastoreService.getEntity(key);
            LOG.log( Level.INFO, "Got object for key "+ key);
        } catch (EntityNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }


        String s = "XXXX";
        Object o1 = cacheService.get("key1");
        LOG.log(Level.INFO,"o1 is null " + (o1==null));


        cacheService.put("key1", s);
        Object o2 = cacheService.get("key1");
        LOG.log(Level.INFO,"o2 is null " + (o2==null));

      Queue queue = QueueFactory.getDefaultQueue();
      queue.add(TaskOptions.Builder.withUrl("/tasks/simple").method(TaskOptions.Method.GET).param("id","test id"));
      LOG.log(Level.INFO,"Message posted to Queue");

    Properties properties = System.getProperties();

    response.setContentType("text/plain");
    response.getWriter().println("Hello App Engine - Standard using "
        + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));


  }

  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }

}
