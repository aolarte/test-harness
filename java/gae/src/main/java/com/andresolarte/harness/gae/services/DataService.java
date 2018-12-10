package com.andresolarte.harness.gae.services;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class DataService {


    private static final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();



    public Key addEntity() {
        Entity employee = new Entity("Employee", "asalieri");
        employee.setProperty("firstName", "Antonio");
        employee.setProperty("lastName", "Salieri");

        employee.setProperty("attendedHrTraining", true);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key key = datastore.put(employee);
        return key;
    }

    public Object getEntity(Key key) throws EntityNotFoundException {

        return datastore.get(key);

    }
}
