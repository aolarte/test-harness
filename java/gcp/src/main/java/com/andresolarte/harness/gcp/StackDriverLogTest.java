package com.andresolarte.harness.gcp;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload.StringPayload;
import com.google.cloud.logging.Severity;

import java.io.IOException;
import java.util.Collections;

public class StackDriverLogTest {
    public static void main(String... args) throws InterruptedException {


        CredentialsUtil.examineApplicationDefaultCredentials();


        // Instantiates a client
        Logging logging = LoggingOptions.getDefaultInstance().getService();

        // The name of the log to write to
        String logName = "my-log";  // "my-log";

        // The data to write to the log
        String text = "Hello, world from API!";

        LogEntry entry = LogEntry.newBuilder(StringPayload.of(text))
                .setSeverity(Severity.ERROR)
                .setLogName(logName)
                .setResource(MonitoredResource.newBuilder("global").build())
                .build();

        // Writes the log entry asynchronously
        logging.write(Collections.singleton(entry));
        Thread.sleep(1000);
        System.out.printf("Logged: %s%n", text);
    }
}
