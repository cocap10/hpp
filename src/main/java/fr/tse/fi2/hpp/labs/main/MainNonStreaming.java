package fr.tse.fi2.hpp.labs.main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.dispatcher.LoadFirstDispatcher;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;
import fr.tse.fi2.hpp.labs.queries.impl.lab4.FiltreBloom;
import fr.tse.fi2.hpp.labs.queries.impl.lab4.RouteMembershipProcessor;
import fr.tse.fi2.hpp.labs.queries.impl.projet.FrequentRoutes;
import fr.tse.fi2.hpp.labs.queries.impl.projet.ZoneRentable;

/**
 * Main class of the program. Register your new queries here
 * 
 * Design choice: no thread pool to show the students explicit
 * {@link CountDownLatch} based synchronization.
 * 
 * @author Julien
 * 
 */
public class MainNonStreaming {

	final static Logger logger = LoggerFactory
			.getLogger(MainNonStreaming.class);

	/**
	 * @param args
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// Initialize query time measure
		QueryProcessorMeasure measure = new QueryProcessorMeasure();
		// Initialize dispatcher and load everything
		LoadFirstDispatcher dispatch = new LoadFirstDispatcher(
				"src/main/resources/data/1000Records.csv");
		logger.info("Finished parsing");
		// Query processors
		List<AbstractQueryProcessor> processors = new ArrayList<>();
		// Add you query processor here
		//FiltreBloom fb= new FiltreBloom(measure, 1000, 0.001);
		processors.add(new FrequentRoutes(measure));
		processors.add(new ZoneRentable(measure));
		// Register query processors
		for (AbstractQueryProcessor queryProcessor : processors) {
			dispatch.registerQueryProcessor(queryProcessor);
		}
		// Initialize the latch with the number of query processors
		CountDownLatch latch = new CountDownLatch(processors.size());
		// Set the latch for every processor
		for (AbstractQueryProcessor queryProcessor : processors) {
			queryProcessor.setLatch(latch);
		}
		for (AbstractQueryProcessor queryProcessor : processors) {
			Thread t = new Thread(queryProcessor);
			t.setName("QP" + queryProcessor.getId());
			t.start();
		}
		// Start everything dispatcher first, not as a thread
		dispatch.run();
		logger.info("Finished Dispatching");
		// Wait for the latch
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error("Error while waiting for the program to end", e);
		}
		// Output measure and ratio per query processor
		measure.setProcessedRecords(dispatch.getRecords());
		measure.outputMeasure();

		
		//System.out.println(fb);
		//System.out.println("Route find : " + RouteMembershipProcessor.checkroute(recordTest));
		//System.out.println(recordTest.getHack_license());
		
		//System.out.println("HASH : " +r);
		//System.out.println("HASH SIZE : "+r.length+"bytes(octets)");

	}

}
