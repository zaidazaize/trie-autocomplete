package com.anurags.autocomplete;

import java.util.*;

public class TrieNode {
  char c;
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  PriorityQueue<TrieNode> order = new PriorityQueue<>(new Comparator<TrieNode>() {
    public int compare(TrieNode a, TrieNode b) {
      if (a.wordCount != b.wordCount) {
        return a.wordCount - b.wordCount;
      } else {
        return a.wordsAhead - b.wordsAhead;
      }
    }
  });
  int wordsAhead;
  int wordCount;

  public TrieNode() {}
  public TrieNode(char c){
    this.c = c;
  }
}
