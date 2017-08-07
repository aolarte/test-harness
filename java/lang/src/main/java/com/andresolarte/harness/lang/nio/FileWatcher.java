package com.andresolarte.harness.lang.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatcher {

    public static void main(String... args) throws IOException, InterruptedException {
        File tempFile = new File("/tmp/test.tmp");
        if (tempFile.exists()) {
            tempFile.delete();
        }
        Path tempFilePath=tempFile.toPath();
        Path tempPath= tempFile.toPath().getParent();
        WatchService watcher=tempPath.getFileSystem().newWatchService();
        tempPath.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
        System.out.println("Watch has been registered");

        boolean done=false;
        while(!done) {
            WatchKey watchKey=watcher.poll();
            if (watchKey!=null) {
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    final Path changed = (Path) event.context();
                    Path changedFull=tempPath.resolve(changed);
                    if (tempFilePath.equals(changedFull)) {
                        if (event.kind()==StandardWatchEventKinds.ENTRY_CREATE) {
                            System.out.println("My file has been created");
                        }

                        if (event.kind()==StandardWatchEventKinds.ENTRY_DELETE) {
                            System.out.println("My file has been deleted OR moved");
                        }


                        // done=true;
                    }
                }
                // reset the key
                boolean valid = watchKey.reset();
                if (!valid) {
                    System.out.println("Key has been unregistered");
                }
            }
        }

    }
}
