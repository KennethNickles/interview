package com.nicklesk.interview.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.nicklesk.interview.datastructures.Edge;
import com.nicklesk.interview.datastructures.Vertex;

public class Djikstras {
	public static void dijkstra() {

		final Queue<Vertex> mGraph = new PriorityQueue<Vertex>();
		final Map<Vertex, Vertex> mPath = new HashMap<Vertex, Vertex>();

		final Vertex vacaville = new Vertex("Vacaville");
		final Vertex fairfield = new Vertex("Fairfield");
		final Vertex sanfran = new Vertex("San Francisco");
		final Vertex seattle = new Vertex("Seattle");
		final Vertex salem = new Vertex("Salem");
		final Vertex redding = new Vertex("Redding");

		vacaville.mEdges.add(new Edge(fairfield, 20L));
		vacaville.mEdges.add(new Edge(redding, 200L));
		redding.mEdges.add(new Edge(salem, 300L));
		salem.mEdges.add(new Edge(seattle, 250L));
		fairfield.mEdges.add(new Edge(sanfran, 45L));

		mGraph.add(vacaville);
		vacaville.mTravelThroughDistance = 0L;

		// While the current queue is not empty
		while (!mGraph.isEmpty()) {
			// go to a new source vertex
			final Vertex source = mGraph.poll();
			System.out.println(source.mId);
			// for all the edges connected to that vertex
			for (Edge edge : source.mEdges) {
				System.out.println(edge.mTarget.mId);
				// find the travelThrough distance for the currently explored
				// edge plus the source's total travel through distance
				final Vertex target = edge.mTarget;
				final long distanceToTravelThrough = edge.mDistance
						+ source.mTravelThroughDistance;

				System.out.println(distanceToTravelThrough);
				// if the travel through distance is less than the target's
				// travel through distance add it as a node in the travel path
				// and add the target to the current queue of explorable nodes
				if (distanceToTravelThrough < target.mTravelThroughDistance) {
					target.mTravelThroughDistance = distanceToTravelThrough;
					mPath.put(source, target);
					mGraph.add(target);
				}
			}
		}

		System.out.println("Paths");
		for (Vertex vertex : mPath.keySet()) {
			System.out.println(vertex.mId + " " + mPath.get(vertex).mId + " "
					+ vertex.mTravelThroughDistance);
		}

		findShortestPath(vacaville, seattle, mPath);

	}

	private static void findShortestPath(Vertex source, Vertex target,
			Map<Vertex, Vertex> paths) {

		System.out.print(source.mId + " --> ");
		Vertex vertex = paths.get(source);
		while (vertex != target) {
			System.out.print(vertex.mId + " --> ");
			vertex = paths.get(vertex);
		}
		System.out.println(vertex.mId);
	}
}
