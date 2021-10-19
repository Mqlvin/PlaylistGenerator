package com.henry;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class PlaylistGenerator {
    public static void main(String[] args) {
        FileHandler.handleFiles();

        ArrayList<String> read = Reader.readArrayList(new File("./ids.txt"));
        ArrayList<String> idList = new ArrayList<>();
        for(String getID : read) {
            if(getID.toLowerCase().contains("youtu.be")) {
                String[] tempParse = getID.split("youtu.be/");
                idList.add(tempParse[tempParse.length - 1]);
            } else if(getID.toLowerCase().contains("youtube.com")) {
                String[] tempParse = getID.split("youtube.com/watch");
                for(String s : tempParse) {
                    if(s.startsWith("?v=")) {
                        idList.add(s.substring(3));
                        break;
                    }
                }
            } // Otherwise, we are assuming it's an invalid URL.
        }

        Integer currentNumber = 1;
        ArrayList<File> filesToDelete = new ArrayList<>();
        for(String ids : idList) {
            Downloader.downloadSong(ids, currentNumber);
            currentNumber += 1;
        }
    }
}
