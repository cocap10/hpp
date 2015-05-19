package fr.tse.fi2.hpp.labs.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MergSortPara extends RecursiveAction {
	int[] tab;
	public MergSortPara(int[] _tab) {
		// TODO Auto-generated constructor stub
		this.tab=_tab;
	}

	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		if (tab.length>20)
		{
            List<MergSortPara> subtasks = new ArrayList<MergSortPara>();
            subtasks.addAll(createSubtasks());
            for(MergSortPara subtask : subtasks){
                subtask.fork();
            }
            for(MergSortPara subtask : subtasks){
                subtask.join();
            }
            int[] res1=subtasks.get(0).tab;
            int[] res2=subtasks.get(1).tab;
            SimpleMergeSort.fusion(res1, res2, tab);
            
		}
        else
        {
			for(int i=1;i<tab.length;i++)
			{
				int memory=tab[i];
				int compt=i-1;
				boolean marqueur;
				do
				{
					marqueur=false;
					if (tab[compt]>memory)
					{
						tab[compt+1]=tab[compt];
						compt--;
						marqueur=true;
					}
					if (compt<0) marqueur=false;
				}
				while(marqueur);
				tab[compt+1]=memory;
			}
		}

	}

	private List<MergSortPara> createSubtasks() {
		// TODO Auto-generated method stub
		List<MergSortPara> subtasks = new ArrayList<MergSortPara>();
		int demiTaille=tab.length/2;
		int[] buf1 = new int[demiTaille];
		int[] buf2 = new int[tab.length-demiTaille];
		System.arraycopy(tab, 0, buf1, 0, demiTaille);
		System.arraycopy(tab, demiTaille, buf2, 0, tab.length-demiTaille);
		MergSortPara subtask1 = new MergSortPara(buf1);
		MergSortPara subtask2 = new MergSortPara(buf2);
		subtasks.add(subtask1);
        subtasks.add(subtask2);
		return subtasks;
	}

}
