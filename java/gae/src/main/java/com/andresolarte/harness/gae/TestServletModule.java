package com.andresolarte.harness.gae;

import com.andresolarte.harness.gae.servlets.CrashServlet;
import com.andresolarte.harness.gae.servlets.InfoServlet;
import com.andresolarte.harness.gae.servlets.tasks.SimpleTask;
import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;

public class TestServletModule extends ServletModule {
    @Override protected void configureServlets() {
        serve("/info").with(InfoServlet.class);

        serve("/tasks/simple").with(SimpleTask.class);
        serve("/crash").with(CrashServlet.class);

        bind(SimpleTask.class).in(Scopes.SINGLETON);
        bind(InfoServlet.class).in(Scopes.SINGLETON);
        bind(CrashServlet.class).in(Scopes.SINGLETON);
    }
}
