package com.henry;

import java.io.File;

public class FileHandler {
    public static void handleFiles() {
        File idList = new File("./ids.txt");
        if(!idList.exists()) {
            System.out.println("No id list was found. Please format a list of ids called \"ids.txt\" and place is in this folder!");
            System.exit(-1);
        }
        File outputFolder = new File("./playlist/");
        if(outputFolder.exists()) {
            if(outputFolder.listFiles().length != 0) {
                System.out.println("Please clear your last playlist folder before creating a new playlist!");
                System.exit(-1);
            }
        } else {
            outputFolder.mkdir();
        }
    }
}
