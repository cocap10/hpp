package fr.tse.fi2.hpp.labs.queries.impl.projet;



import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
	private static Multiset<Route> routeFrequente;
	private long temps1;
	private long temps2;
	private long delay;
	private String result;

	public FrequentRoutes(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		this.maFenetre = new LinkedList<DebsRecord>();//On va essayer avec une ArrayList apres
		this.routeFrequente = HashMultiset.create(); //On va essayer avec une ArrayList apres
	}
	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
		maFenetre.add(record);
		if (maFenetre.size()>0)
		{
			temps1 = System.nanoTime();
			if (record.getDropoff_datetime()>=maFenetre.get(0).getDropoff_datetime()+1800000)
			{
				for (int i=0;i<maFenetre.size();i++) {
					if(maFenetre.get(i).getDropoff_datetime()<record.getDropoff_datetime()-1800000)
						maFenetre.remove(i);
				}
			}
			routeFrequente.clear();
			for (DebsRecord cursRecord : maFenetre) {
				Route cursRoute= convertRecordToRoute(cursRecord);
				routeFrequente.add(cursRoute);
			}
			//System.out.println("Nouvelle Fenetre:");
			Date pickup_datetime = new Date(record.getPickup_datetime());
			Date dropoff_datetime= new Date(record.getDropoff_datetime());
			Format datetime_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result=datetime_format.format(pickup_datetime)+","+datetime_format.format(dropoff_datetime);
			int i=1;
			for (Route route : Multisets.copyHighestCountFirst(routeFrequente).elementSet()) {
				if(i<11)
				{
					//this.writeLine("start_cell_id_"+i+" = "+route.getPickup().getX()+", "+route.getPickup().getY());
					//this.writeLine("end_cell_id_"+i+" = "+route.getDropoff().getX()+", "+route.getDropoff().getY());
					result+=","+route.getPickup().getX()+"."+route.getPickup().getY();
					result+=","+route.getDropoff().getX()+"."+route.getDropoff().getY();
				}

				//System.out.println(route+" : "+routeFrequente.count(route));
				i++;	
			}
			while(i<11)
			{
				//this.writeLine("start_cell_id_"+i+" = NULL");
				//this.writeLine("end_cell_id_"+i+" = NULL");
				result+=",NULL,NULL";
				i++;
			}
			
			temps2 = System.nanoTime();
			delay = temps2-temps1;
			//this.writeLine("delay = "+ delay);
			result+=","+ delay;//c'est en nano secondes
			this.writeLine(result);
			
		}
		

	}
}


