package com.nicklesk.interview.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
	public String mId;
	public Long mTravelThroughDistance = 1000L;

	public List<Edge> mEdges = new ArrayList<Edge>();

	public Vertex(String id) {
		mId = id;
	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(this.mTravelThroughDistance,
				other.mTravelThroughDistance);
	}
}
