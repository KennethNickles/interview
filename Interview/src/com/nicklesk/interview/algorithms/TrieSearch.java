package com.nicklesk.interview.algorithms;

import java.util.PriorityQueue;
import java.util.Queue;

import com.nicklesk.interview.datastructures.TrieNode;

public class TrieSearch {

	public TrieNode dfsTrie(TrieNode trie, int value) {
		if (trie == null) {
			return null;
		}
		// Found
		if (trie.mValue == value) {
			return trie;
		}
		for (TrieNode node : trie.mChildren) {
			if (node != null) {
				return dfsTrie(node, value);
			}
		}
		// Didn't find
		return null;
	}

	public TrieNode bfsTrie(TrieNode trie, int value) {

		if (trie.mValue == value) {
			return trie;
		}

		final Queue<TrieNode> queue = new PriorityQueue<TrieNode>();

		for (TrieNode child : trie.mChildren) {
			if (child != null) {
				queue.add(child);
			}
		}

		TrieNode node;
		while (!queue.isEmpty()) {
			node = queue.poll();
			if (node.mValue == value) {
				return node;
			}
			for (TrieNode child : node.mChildren) {
				if (child != null) {
					queue.add(child);
				}
			}
		}
		return null;
	}
}
