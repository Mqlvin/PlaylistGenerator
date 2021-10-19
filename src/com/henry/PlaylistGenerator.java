package com.henry;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlaylistGenerator {
    public static ArrayList<String> consoleLog = new ArrayList<>();

    public static void main(String[] args) {
        FileHandler.handleFiles();

        ArrayList<String> read = Reader.readArrayList(new File("./ids.txt"));
        ArrayList<String> idList = new ArrayList<>();
        for(String getID : read) {
            if(getID.toLowerCase().contains("youtu.be")) {
                String[] tempParse = getID.split("youtu.be/");
                if(!idList.contains(tempParse[tempParse.length - 1])) {
                    idList.add(tempParse[tempParse.length - 1]);
                }
            } else if(getID.toLowerCase().contains("youtube.com")) {
                String[] tempParse = getID.split("youtube.com/watch");
                for(String s : tempParse) {
                    if(s.startsWith("?v=")) {
                        if(!idList.contains(s.substring(3))) {
                            idList.add(s.substring(3));
                        }
                        break;
                    }
                }
            } // Otherwise, we are assuming it's an invalid URL.
        }

        Integer currentSong = 1;
        for(String ids : idList) {
            Downloader.downloadSong(ids, currentSong);
            currentSong += 1;
        }

        /*
        TODO: Could definitely multi-thread this, considering it is currently quite slow.
         */
    }
}
