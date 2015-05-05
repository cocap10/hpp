package fr.tse.fi2.hpp.labs.queries.impl.lab4;

import java.util.ArrayList;
import java.util.List;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class RouteMembershipProcessor extends AbstractQueryProcessor {
	private List<DebsRecord> recs=null;

	public RouteMembershipProcessor(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		recs= new ArrayList<DebsRecord>();
	}

	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
		recs.add(record);

	}
	public int printRoute(int index)
	{
		System.out.println(recs.get(index).getPickup_longitude()+" "+recs.get(index).getPickup_latitude()+" "+recs.get(index).getDropoff_longitude()+" "+recs.get(index).getDropoff_latitude()+" "+recs.get(index).getHack_license());
		return index;
	}
	
	public int lookupForRoute(float longDep, float latDep, float longArr, float latArr, String HackLic)
	{
		int nbTrouve=0;
		for (int i=0; i<recs.size();i++) {
			DebsRecord debsRecord = recs.get(i);
			//System.out.println(i);
			if (debsRecord.getPickup_longitude()==longDep && debsRecord.getPickup_latitude()==latDep
					&& debsRecord.getDropoff_longitude()==longArr && debsRecord.getDropoff_latitude()==latArr
					&& debsRecord.getHack_license().equals(HackLic))
			{
				nbTrouve++;
			}
		}
		return nbTrouve;
	}

}
