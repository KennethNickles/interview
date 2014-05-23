package com.nicklesk.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import com.nicklesk.interview.datastructures.Edge;
import com.nicklesk.interview.datastructures.Node;
import com.nicklesk.interview.datastructures.Tree;
import com.nicklesk.interview.datastructures.Vertex;

public class Main {

	public static void main(String[] args) {

		final Tree tree = new Tree();
		final Node rootNode = new Node(52);
		tree.mRoot = rootNode;

		tree.addNode(new Node(30));
		tree.addNode(new Node(12));
		tree.addNode(new Node(67));
		tree.addNode(new Node(82));
		tree.addNode(new Node(15));
		tree.addNode(new Node(27));
		tree.addNode(new Node(91));
		tree.addNode(new Node(48));
		tree.addNode(new Node(9));
		tree.addNode(new Node(11));

		tree.bfs(82);

	}

	private static void knapsack() {

		final List<Item> mItems = new ArrayList<Item>();

		mItems.add(new Item(4, 12));
		mItems.add(new Item(6, 10));
		mItems.add(new Item(5, 8));
		mItems.add(new Item(7, 11));
		mItems.add(new Item(3, 14));
		mItems.add(new Item(1, 7));
		mItems.add(new Item(6, 9));

		final int numberOfItems = 7;
		final int capacityOfSack = 18;

		int[][] solutionMatrix = new int[numberOfItems][capacityOfSack];

		for (int i = 0; i < numberOfItems; i++) {
			solutionMatrix[0][i] = 0;
		}

		printMatrix(solutionMatrix);

		// for i equal to 1 for the number of items
		for (int i = 1; i < numberOfItems; i++) {
			// for j equal to 1 for the capacities of the sack
			for (int j = 1; j < capacityOfSack; j++) {
				// if the weight of the current item is greater than the current
				// capacity
				if (mItems.get(i - 1).mWeight > j) {
					// take the last solution
					solutionMatrix[i][j] = solutionMatrix[i - 1][j];
				} else {
					// else take the max of the last solution and this items
					// value plus its last solved value subtract this items
					// weight
					solutionMatrix[i][j] = Math.max(solutionMatrix[i - 1][j],
							(mItems.get(i - 1).mValue + solutionMatrix[i - 1][j
									- mItems.get(i - 1).mWeight]));
				}
			}
		}

		printMatrix(solutionMatrix);
	}

	private static void printMatrix(int[][] matrixToPrint) {
		for (int i = 0; i < matrixToPrint.length; i++) {
			for (int j = 0; j < matrixToPrint[i].length; j++) {
				System.out.format("%4d", matrixToPrint[i][j]);
			}
			System.out.println();
		}
	}

	private static class Item {

		public int mWeight;
		public int mValue;

		Item(int weight, int value) {
			mWeight = weight;
			mValue = value;
		}

	}

	private static void dijkstra() {

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
