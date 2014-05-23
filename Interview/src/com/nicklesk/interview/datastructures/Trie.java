package com.nicklesk.interview.datastructures;

public class Trie {
	public TrieNode mRoot;
	
	public void addNode(TrieNode node) {
		addNode(mRoot, node);
	}

	private void addNode(TrieNode parent, TrieNode node) {
		if (parent == null) {
			parent = node;
			return;
		}
		if (node.mValue <= parent.mValue) {
			// Still need to implement this
		} else {
			// Still need to implement this
		}
	}
}