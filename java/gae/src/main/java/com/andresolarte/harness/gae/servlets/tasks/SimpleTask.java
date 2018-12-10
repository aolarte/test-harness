package com.andresolarte.harness.gae.servlets.tasks;



import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@WebServlet(name = "SimpleTask", value = "/tasks/simple")
public class SimpleTask extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(SimpleTask.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        LOG.log(Level.INFO,"Simple Task");

    }
}
