package fr.tse.fi2.hpp.labs.queries.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class NaiveAverage extends AbstractQueryProcessor {
	private List<Float> numbers = null;

	public NaiveAverage(QueryProcessorMeasure measure) {
		super(measure);
		numbers = new ArrayList<>();
		_queued= new ConcurrentLinkedQueue<Float>();
	}

	@Override
	protected void process(DebsRecord record) {
		numbers.add(record.getFare_amount());

		float sum = 0f;
		for (Float f : numbers) {
			sum += f;
		}
		_queued.add(sum);
		
		//Le pb est I/O Bound on pas de 27s à 9s d'exe en commentant la ligne suivante
		//writeLine("current mean : " + (sum / numbers.size()));
	}

}