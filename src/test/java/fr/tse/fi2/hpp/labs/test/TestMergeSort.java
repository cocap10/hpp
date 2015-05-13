package fr.tse.fi2.hpp.labs.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import fr.tse.fi2.hpp.labs.utils.SimpleMergeSort;

public class TestMergeSort {

	@Test
	public void test() {
		//fail("Not yet implemented");

		int[] tab1=new RandomArray(10).nextArray();
		int[] tab2=tab1;
		System.out.println(Arrays.toString(tab1)+"   "+Arrays.toString(tab2));
		SimpleMergeSort.sort(tab1);
		Arrays.sort(tab2);
		assertArrayEquals(tab1, tab2);
		System.out.println(Arrays.toString(tab1)+"   "+Arrays.toString(tab2));






	}

}
