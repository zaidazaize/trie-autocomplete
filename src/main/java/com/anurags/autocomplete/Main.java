package com.anurags.autocomplete;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    AutoComplete ac = new AutoComplete();
    String fileName = "rockyou.txt";
    ac.feedFile(fileName);

    List<String> result = ac.search("josh");
    for (String word : result) {
      System.out.println(word);
    }
  }
}

