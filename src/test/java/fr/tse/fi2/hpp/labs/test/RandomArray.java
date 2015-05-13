package fr.tse.fi2.hpp.labs.test;


import java.util.Random;

public class RandomArray {
	private static int[] array = null;
	private static final Random random = new Random();
	public RandomArray (final int length) {
        array = new int[length];
    }
	public int[] nextArray() {
		// TODO Auto-generated method stub
		for (int idx = 0; idx < array.length; ++idx) 
            array[idx] = random.nextInt()%10;
        return array;
	}
}
