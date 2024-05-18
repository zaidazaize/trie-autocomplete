package com.anurags.autocomplete;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        AutoComplete ac = new AutoComplete();
        String fileName = "rockyou.txt";
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        if(inputStream == null) {
            System.out.println("File not found");
            return;
        }
        // print all the resources

        try(BufferedReader br = new BufferedReader(new java.io.InputStreamReader(inputStream))) {
            String line;
            while((line = br.readLine()) != null) {
                ac.insert(line);
            }
            System.out.println("Enter the prefix to search");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

