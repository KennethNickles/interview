package com.nicklesk.interview.algorithms;

import java.util.ArrayList;
import java.util.List;

import com.nicklesk.interview.datastructures.Item;

public class Knapsack {
	
	public static void knapsack() {

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
}
