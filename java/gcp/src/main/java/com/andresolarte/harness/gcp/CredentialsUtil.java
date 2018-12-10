package com.andresolarte.harness.gcp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.auth.oauth2.UserCredentials;

import java.io.IOException;

public class CredentialsUtil {
    public static void examineApplicationDefaultCredentials() {
        try {
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
            if (credentials instanceof ServiceAccountCredentials) {
                //Normally set in the environment variable GOOGLE_APPLICATION_CREDENTIALS, pointing to the file path of the service account key JSON file
                ServiceAccountCredentials serviceAccountCredentials = (ServiceAccountCredentials) credentials;
                System.out.println("ServiceAccountCredentials (Normally set in the environment variable GOOGLE_APPLICATION_CREDENTIALS, pointing to the file path of the service account key JSON file)");
                        System.out.println("projectId: " + serviceAccountCredentials.getProjectId());
                System.out.println("clientId: " + serviceAccountCredentials.getClientId());
                System.out.println("clientEmail: " + serviceAccountCredentials.getClientEmail());
            } if (credentials instanceof UserCredentials) {
                //Uses the config of gcloud
                UserCredentials userCredentials = (UserCredentials)credentials;
                System.out.println("UserCredentials (Uses the config of gcloud)");
                System.out.println("clientId: " + userCredentials.getClientId());
            } else {
                System.out.println("Unknown class: " + credentials.getClass().getCanonicalName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
