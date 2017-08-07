package com.andresolarte.harness.narayana.recovery;

import com.arjuna.ats.arjuna.coordinator.*;
import java.io.File;

public class SimpleRecord extends AbstractRecord {
    public String filename = "c:/tmp/RecordState";

    public SimpleRecord() {
        System.out.println("Creating new resource");
    }

    public static AbstractRecord create() {
        return new SimpleRecord();
    }



    public int topLevelAbort() {
        try {
            File fd = new File(filename);
            if (fd.exists()) {
                if (fd.delete())
                    System.out.println("File Deleted");
            }
        } catch (Exception ex) {
            // …
        }
        return TwoPhaseOutcome.FINISH_OK;
    }

    public int topLevelCommit() {
        if (TestRecoveryModule._crash)
            System.exit(0);
        try {
            java.io.FileOutputStream file = new java.io.FileOutputStream(
                    filename);
            java.io.PrintStream pfile = new java.io.PrintStream(
                    file);
            pfile.println("I'm Committed");
            file.close();
        } catch (java.io.IOException ex) {
            // ...
        }
        return TwoPhaseOutcome.FINISH_OK;
    }

    public int topLevelPrepare() {
        try {
            java.io.FileOutputStream file = new java.io.FileOutputStream(
                    filename);
            java.io.PrintStream pfile = new java.io.PrintStream(
                    file);
            pfile.println("I'm prepared");
            file.close();
        } catch (java.io.IOException ex) {
            // ...
        }
        return TwoPhaseOutcome.PREPARE_OK;
    }

    @Override
    public void merge(AbstractRecord a) {

    }

    @Override
    public void alter(AbstractRecord a) {

    }

    @Override
    public boolean shouldAdd(AbstractRecord a) {
        return false;
    }

    @Override
    public boolean shouldAlter(AbstractRecord a) {
        return false;
    }

    @Override
    public boolean shouldMerge(AbstractRecord a) {
        return false;
    }

    @Override
    public boolean shouldReplace(AbstractRecord a) {
        return false;
    }
    // …

    @Override
    public int typeIs() {
        return 0;
    }

    @Override
    public Object value() {
        return null;
    }

    @Override
    public void setValue(Object o) {

    }

    @Override
    public int nestedAbort() {
        return 0;
    }

    @Override
    public int nestedCommit() {
        return 0;
    }

    @Override
    public int nestedPrepare() {
        return 0;
    }
}