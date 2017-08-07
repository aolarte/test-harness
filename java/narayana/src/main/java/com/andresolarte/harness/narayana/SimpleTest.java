package com.andresolarte.harness.narayana;

import com.arjuna.ats.arjuna.AtomicAction;
import com.arjuna.ats.arjuna.coordinator.OnePhaseResource;
import com.arjuna.ats.arjuna.state.InputObjectState;
import com.arjuna.ats.arjuna.state.OutputObjectState;
import com.arjuna.ats.internal.arjuna.abstractrecords.LastResourceRecord;

import java.io.IOException;

public class SimpleTest {
    public static void main(String... args) {
        boolean success = false;
        AtomicAction action = new AtomicAction();
        OnePhaseResource opRes = new OnePhaseResource() {

            @Override
            public int commit() {
                System.out.println("Commit");
                return 0;
            }

            @Override
            public int rollback() {
                System.out.println("Rollback");
                return 0;
            }

            @Override
            public void pack(OutputObjectState os) throws IOException {

            }

            @Override
            public void unpack(InputObjectState os) throws IOException {

            }
        };

        System.out.println("Starting top-level action.");

        action.begin();
        action.add(new LastResourceRecord(opRes));


        action.commit();
    }
}
