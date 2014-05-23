package com.nicklesk.interview.datastructures;

public class Edge {
	public Vertex mTarget;
	public long mDistance;

	public Edge(Vertex target, long distance) {
		mTarget = target;
		mDistance = distance;
	}
}
