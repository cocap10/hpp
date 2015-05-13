package fr.tse.fi2.hpp.labs.utils;

import java.lang.reflect.Array;

import org.junit.internal.InexactComparisonCriteria;

import cern.colt.Arrays;

public class SimpleMergeSort {
	public SimpleMergeSort() {
		// TODO Auto-generated constructor stub
	}
	public static void sort(int[] tab){

		if (tab.length==1)
			return;

		if (tab.length==2)
		{
			if (tab[0]>tab[1])
			{
				int permut=tab[0];
				tab[0]=tab[1];
				tab[1]=permut;
			}
			return;
		}
		int demiTaille=tab.length/2;
		int[] buf1 = new int[demiTaille];
		int[] buf2 = new int[tab.length-demiTaille];
		System.arraycopy(tab, 0, buf1, 0, demiTaille);
		System.arraycopy(tab, demiTaille, buf2, 0, tab.length-demiTaille);
		sort(buf1);
		sort(buf2);
		fusion(buf1,buf2,tab);
		return;

	}

	private static int[] fusion(int[] tab1,int [] tab2, int[] rst)
	{
		int k=0;
		int index1=0;
		int index2=0;
		while(k<rst.length){
			if (index1==tab1.length)
			{
				try {
					System.arraycopy(tab2,index2,rst,k,tab2.length-index2);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
			if (index2==tab2.length)
			{
				try {
					System.arraycopy(tab1,index1,rst,k,tab1.length-index1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
			
			if(tab1[index1]<tab2[index2])
			{
				rst[k]=tab1[index1];
				index1++;
				k++;
			}
			else
			{
				rst[k]=tab2[index2];
				index2++;
				k++;	
			}
		}
		return rst;

	}
}


