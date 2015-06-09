package fr.tse.fi2.hpp.labs.queries.impl.projet;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class ZoneRentable extends AbstractQueryProcessor {
	private List<DebsRecord> maFenetre;
	private long temps1;
	private long temps2;
	private long delay;
	private String result;
	public ZoneRentable(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		this.maFenetre = new LinkedList<DebsRecord>();

	}

	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
		maFenetre.add(record);
		if (maFenetre.size()>0)
		{
			temps1 = System.nanoTime();

			for (int i=0;i<maFenetre.size();i++) {
				if (record.getDropoff_datetime()>=maFenetre.get(0).getDropoff_datetime()+1800000 && maFenetre.get(i).getDropoff_datetime()<record.getDropoff_datetime()-1800000)
					maFenetre.remove(i);
				else if(maFenetre.get(i).getHack_license().equals(record.getHack_license()))
					maFenetre.remove(i);
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
