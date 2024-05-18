package com.anurags.autocomplete;

import java.util.*;
import java.io.*;
import com.anurags.autocomplete.interfaces.AutoCompleteInterface;

class AutoComplete implements AutoCompleteInterface {
  TrieNode root;
  AutoComplete(){
    root = new TrieNode();
  }

  public void feedFile(String fileName) {
    try{
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("fileName").getFile());
      InputStream inputStream = new FileInputStream(file);

      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
      String line;
      while ((line = br.readLine()) != null) {
        insert(line);
      }
      br.close();
      System.out.println("File " + fileName + " has been read successfully.");
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());

    }
  }

  public void insert(String word) {
    TrieNode current = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!current.children.containsKey(c)) {
        TrieNode tmp = new TrieNode(c);
        current.children.put(c, tmp);
        current.order.offer(tmp);
      }
      current = current.children.get(c);
      current.wordsAhead++;
    }
    current.wordCount++;
  }

  public List<String> search(String prefix) {
    return search(prefix, 5);
  }
  public List<String> search(String prefix, int count) {
    List<String> result = new ArrayList<>();
    TrieNode current = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      if (!current.children.containsKey(c)) {
        return result;
      }
      current = current.children.get(c);
    }
    dfs(current, prefix, result, count);
    return result;
  }

  private void dfs(TrieNode current, String prefix, List<String> result, int count) {
    if (current.wordCount > 0) {
      result.add(prefix);
      count--;
    }
    if (count == 0) {
      return;
    }
    for (TrieNode child : current.order) {
      dfs(child, prefix + child.c, result, count);
    }
  }
  public void acceptSuggestion(String word) {
    insert(word);
  }
}
