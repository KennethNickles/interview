package com.nicklesk.interview.datastructures;

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
}
