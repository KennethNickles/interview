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

	private static class Vertex implements Comparable<Vertex> {

		public String mId;
		public Long mTravelThroughDistance = 1000L;

		public List<Edge> mEdges = new ArrayList<Edge>();

		Vertex(String id) {
			mId = id;
		}

		@Override
		public int compareTo(Vertex other) {
			return Double.compare(this.mTravelThroughDistance,
					other.mTravelThroughDistance);
		}
	}

	private static class Edge {

		public Vertex mTarget;
		public long mDistance;

		Edge(Vertex target, long distance) {
			mTarget = target;
			mDistance = distance;
		}
	}

	private static class Node implements Comparable<Node> {
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

	private static class Tree {
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

	public static class TrieNode {
		public TrieNode[] mChildren;

		public int mValue;
	}

	public static class Trie {
		public TrieNode mRoot;

		public TrieNode dfsTrie(int value) {
			return dfsTrie(mRoot, value);
		}

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

			if (mRoot.mValue == value) {
				return mRoot;
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

}
