package fr.tse.fi2.hpp.labs.queries.impl.projet;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;










import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.Route;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class FrequentRoutes extends AbstractQueryProcessor {
	private List<DebsRecord> maFenetre;
	private static Multiset<Route> top10;
	//private Route cursRoute;

	public FrequentRoutes(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		this.maFenetre = new LinkedList<DebsRecord>();//On va essayer avec une ArrayList apres
		this.top10 = HashMultiset.create(); //On va essayer avec une ArrayList apres
	}
	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
		maFenetre.add(record);
		if (maFenetre.size()>1)
		{
			if (record.getDropoff_datetime()>=maFenetre.get(0).getDropoff_datetime()+1800000)
			{
				for (int i=0;i<maFenetre.size();i++) {
					if(maFenetre.get(i).getDropoff_datetime()<record.getDropoff_datetime()-1800000)
						maFenetre.remove(i);
				}
			}
			top10.clear();
			for (DebsRecord cursRecord : maFenetre) {
				Route cursRoute= convertRecordToRoute(cursRecord);
				top10.add(cursRoute);
			}
			System.out.println("Nouvelle Fenetre:");
			for (Route route : Multisets.copyHighestCountFirst(top10).elementSet()) {

				System.out.println(route+" : "+top10.count(route));
			}
			

		}

	}
}


