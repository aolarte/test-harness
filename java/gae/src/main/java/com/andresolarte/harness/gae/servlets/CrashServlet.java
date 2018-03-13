package com.andresolarte.harness.gae.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class CrashServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("Crash !!");
        }

        response.setContentType("text/plain");
        response.getWriter().println("Ok");


    }
}
