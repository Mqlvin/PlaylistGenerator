package com.henry;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Reader {
    public static ArrayList<String> readArrayList(File path) {
        if(path.exists()) {
            try {
                ArrayList<String> textFromFile = new ArrayList<>(Files.readAllLines(Paths.get(path.toString())));
                return textFromFile;
            } catch(Exception e) {
                System.out.println("There was an error reading the text, " + e);
                System.exit(-1);
            }
        }
        return null;
    }
}
