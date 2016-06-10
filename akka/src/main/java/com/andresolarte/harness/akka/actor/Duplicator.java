package com.andresolarte.harness.akka.actor;

import com.andresolarte.harness.akka.pojo.IDuplicator;

public class Duplicator implements IDuplicator  {

    public void duplicate(int value, int times) {
        System.out.println("Working.");

    }
}
