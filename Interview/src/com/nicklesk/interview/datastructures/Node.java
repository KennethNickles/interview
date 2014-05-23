package com.nicklesk.interview.datastructures;

public class Node implements Comparable<Node> {
	public Node mLeftChild;
	public Node mRightChild;
	public int mValue;

	public Node(int value) {
		mValue = value;
	}

	@Override
	public int compareTo(Node other) {
		return Integer.valueOf(this.mValue).compareTo(other.mValue);
	}
}