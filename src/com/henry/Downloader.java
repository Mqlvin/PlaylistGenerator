package com.henry;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Downloader {
    public static String downloadSong(String id, Integer number) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\" && youtube-dl --extract-audio --audio-format mp3 -o \"\\playlist\\(" + number + ") %(title)s.%(ext)s\" https://www.youtube.com/watch?v=" + id);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while(true) {
                line = br.readLine();
                if(line == null) {
                    break;
                }
                PlaylistGenerator.consoleLog.add(line);
            }
        } catch(Exception e) {
            System.out.println("Exception " + e);
            System.exit(-1);
        }
        return "unknown";
    }
}
