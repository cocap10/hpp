package fr.tse.fi2.hpp.labs.queries.impl.projet;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.GridPoint;
import fr.tse.fi2.hpp.labs.beans.Route;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class ZoneRentable extends AbstractQueryProcessor {
	private List<DebsRecord> maFenetre30;
	private List<DebsRecord> maFenetre15;
	private long temps1;
	private long temps2;
	private long delay;
	private String result;
	private static Multiset<GridPoint> arriveeFrequentes;
	private static HashMap<GridPoint, Float> profits; 
	private static TreeMap<GridPoint, Float> rentabilites;
	public ZoneRentable(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		this.maFenetre30 = new LinkedList<DebsRecord>();
		this.maFenetre15 = new LinkedList<DebsRecord>();
		this.arriveeFrequentes= HashMultiset.create();
		this.profits= new HashMap<GridPoint, Float>();
		this.rentabilites= new TreeMap<GridPoint, Float>();

	}

	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
		maFenetre30.add(record);
		maFenetre15.add(record);
		if (maFenetre30.size()>0)
		{
			temps1 = System.nanoTime();

			for (int i=0;i<maFenetre30.size();i++) {
				if (record.getDropoff_datetime()>=maFenetre30.get(0).getDropoff_datetime()+1800000 && maFenetre30.get(i).getDropoff_datetime()<record.getDropoff_datetime()-1800000)
					maFenetre30.remove(i);
				else if(maFenetre30.get(i).getHack_license().equals(record.getHack_license()))
					maFenetre30.remove(i);
			}
			arriveeFrequentes.clear();
			for (DebsRecord cursRecord : maFenetre30) {
				GridPoint cursRoute= this.convert2(cursRecord.getDropoff_latitude(),cursRecord.getDropoff_longitude());
				arriveeFrequentes.add(cursRoute);
			}
			//on recup le nombre de taxi vide sur le GridPoint gp grace à arriveeFrequentes.count(gp)
			if (record.getDropoff_datetime()>=maFenetre15.get(0).getDropoff_datetime()+900000)
			{
				for(int i=0;i<maFenetre15.size();i++)
				{
					if (maFenetre15.get(i).getDropoff_datetime()<record.getDropoff_datetime()-900000)
					{
						maFenetre15.remove(i);
					}
				}
			}
			
			for (DebsRecord cursRecord : maFenetre15) {
				GridPoint gp = convert2(cursRecord.getPickup_latitude(), cursRecord.getPickup_longitude());
				float amount = profits.get(gp);
				profits.put(gp, amount+(cursRecord.getTip_amount()+cursRecord.getFare_amount()));
			}
			for (GridPoint gp : profits.keySet())
			{
				rentabilites.put(gp, profits.get(gp)/(float)arriveeFrequentes.count(gp));
			}
			
		}



		Date pickup_datetime = new Date(record.getPickup_datetime());
		Date dropoff_datetime= new Date(record.getDropoff_datetime());
		Format datetime_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result=datetime_format.format(pickup_datetime)+","+datetime_format.format(dropoff_datetime);



		temps2 = System.nanoTime();
		delay = temps2-temps1;
		//this.writeLine("delay = "+ delay);
		result+=","+ delay;//c'est en nano secondes
		this.writeLine(result);
	}

}
