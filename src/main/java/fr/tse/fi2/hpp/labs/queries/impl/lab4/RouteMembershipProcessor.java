package fr.tse.fi2.hpp.labs.queries.impl.lab4;

import java.util.ArrayList;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
@State(Scope.Benchmark)
public class RouteMembershipProcessor extends AbstractQueryProcessor {
	private static ArrayList<DebsRecord> recs=null;
	private static DebsRecord recTest;
	private int count = 0;

	public RouteMembershipProcessor(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		recs= new ArrayList<DebsRecord>();
	}

	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
		recs.add(record);

		count++;
		
		if(count==15){
			recTest = record;
		}
	}
	public static DebsRecord getRec(){ return recTest; }

	public static int checkroute(DebsRecord rec){
		int nbTrouve=0;
		for(int i=0; i<recs.size(); i++){
			if(        (recs.get(i).getPickup_longitude() 	== rec.getPickup_longitude())
					&& (recs.get(i).getPickup_latitude()		== rec.getPickup_latitude())
					&& (recs.get(i).getDropoff_longitude() 	== rec.getDropoff_longitude())
					&& (recs.get(i).getDropoff_latitude() 	== rec.getDropoff_latitude())
					&& (recs.get(i).getHack_license().equals(rec.getHack_license()))	)
			{
				nbTrouve++;
			}
		}
		return nbTrouve;
	}

}
