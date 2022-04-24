package com.example.testtask.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileLogger {
    private static final String NEW_LINE = "\n";
    private static final File PROJECT_HOME = findProjectHome();

    @Value("${file-log.path}")
    private String filePath;

    private static File findProjectHome() {
        final String dir = System.getProperty("user.dir");
        return new File(dir);
    }

    public File createFileIfNotExist() {
        final File file = new File(PROJECT_HOME + filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public void log(final String text) {
        final File file = createFileIfNotExist();
        try (FileWriter writer = new FileWriter(file,true)) {
            writer.write(NEW_LINE + text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
