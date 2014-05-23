package com.nicklesk.interview.datastructures;

import java.util.PriorityQueue;
import java.util.Queue;

public class Tree {
	public Node mRoot;

	public void addNode(Node node) {
		addNode(mRoot, node);
	}

	private void addNode(Node parent, Node node) {
		if (parent == null) {
			parent = node;
			return;
		}
		if (node.mValue <= parent.mValue) {
			if (parent.mLeftChild == null) {
				parent.mLeftChild = node;
			} else {
				addNode(parent.mLeftChild, node);
			}
		} else {
			if (parent.mRightChild == null) {
				parent.mRightChild = node;
			} else {
				addNode(parent.mRightChild, node);
			}
		}
	}

	public Node dfs(int value) {
		return dfs(mRoot, value);
	}

	// Recursive Depth First Search
	private Node dfs(Node node, int value) {
		// If the currently visited node is null return null
		if (node == null) {
			return null;
		}

		System.out.println(node.mValue);
		// If the currently visited node has the value we are looking for
		// return the currently visited node
		if (node.mValue == value) {
			System.out.println("Found the Value");
			return node;
		}
		// If the left child is not null visit the left child
		if (node.mLeftChild != null) {
			// if searching the left child results in a non-null node it
			// has the value we are looking for
			final Node leftSide = dfs(node.mLeftChild, value);
			if (leftSide != null) {
				System.out.println("Node was found in the left side");
				return leftSide;
			}
		}
		// If the right child is not null visit the right child
		if (node.mRightChild != null) {
			// if searching the right child results in a non-null node it
			// has the value we are looking for
			final Node rightSide = dfs(node.mRightChild, value);
			if (rightSide != null) {
				System.out.println("Node was found in the right side");
				return rightSide;
			}
		}
		return null;
	}

	public Node bfs(int value) {

		// if the root has the value we are looking for return the root
		if (mRoot.mValue == value) {
			return mRoot;
		}

		// Create a queue and add the first two children to the queue
		final Queue<Node> queue = new PriorityQueue<Node>();
		queue.add(mRoot.mLeftChild);
		queue.add(mRoot.mRightChild);

		Node node;
		// While the queue is not empty
		while (!queue.isEmpty()) {
			// grab the node at the front of the queue
			node = queue.poll();
			System.out.println(node.mValue);
			// if the node has the value we are looking for return the node
			if (node.mValue == value) {
				System.out.println("Value found");
				return node;
			}
			// if the node's left child is not null add it to the queue
			if (node.mLeftChild != null) {
				queue.add(node.mLeftChild);
			}
			// if the node's right child is not null add it to the queue
			if (node.mRightChild != null) {
				queue.add(node.mRightChild);
			}
		}
		return null;
	}
}
