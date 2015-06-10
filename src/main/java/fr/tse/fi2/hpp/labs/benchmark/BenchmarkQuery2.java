//package fr.tse.fi2.hpp.labs.benchmark;
//
//import fr.tse.fi2.hpp.labs.queries.impl.projet.ZoneRentable;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.Fork;
//import org.openjdk.jmh.annotations.Measurement;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.Setup;
//import org.openjdk.jmh.annotations.State;
//import org.openjdk.jmh.annotations.Warmup;
//
//import java.util.concurrent.CountDownLatch;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
//import fr.tse.fi2.hpp.labs.dispatcher.StreamingDispatcher;
//import fr.tse.fi2.hpp.labs.main.MainNonStreaming;
//import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;
//
//
//@Warmup(iterations = 1)
//@Measurement(iterations = 1)
//@Fork(1)
//@State(Scope.Benchmark)
//public class BenchmarkQuery2 {
//	final static Logger logger = LoggerFactory
//			.getLogger(MainNonStreaming.class);
//	private List<AbstractQueryProcessor> processors;
//	private StreamingDispatcher dispatch;
//	private CountDownLatch latch;
//	private QueryProcessorMeasure measure;
//		
//	@Setup
//	public void init(){
//		// Init query time measure
//		measure = new QueryProcessorMeasure();
//		// Init dispatcher
//		dispatch = new StreamingDispatcher(
//				"src/main/resources/data/1000Records.csv");
//
//		// Query processors
//		processors = new ArrayList<>();
//		// Add you query processor here
//		processors.add(new ZoneRentable(measure));
//		// Register query processors
//		for (AbstractQueryProcessor queryProcessor : processors) {
//			dispatch.registerQueryProcessor(queryProcessor);
//		}
//		// Initialize the latch with the number of query processors
//		latch = new CountDownLatch(processors.size());
//		// Set the latch for every processor
//		for (AbstractQueryProcessor queryProcessor : processors) {
//			queryProcessor.setLatch(latch);
//		}
//
//	}
//
//
//	@Benchmark
//	public void test(){
//		// Start everything
//		for (AbstractQueryProcessor queryProcessor : processors) {
//			// queryProcessor.run();
//			Thread t = new Thread(queryProcessor);
//			t.setName("QP" + queryProcessor.getId());
//			t.start();
//		}
//		Thread t1 = new Thread(dispatch);
//		t1.setName("Dispatcher");
//		t1.start();
//
//		// Wait for the latch
//		try {
//			latch.await();
//		} catch (InterruptedException e) {
//			logger.error("Error while waiting for the program to end", e);
//		}
//		// Output measure and ratio per query processor
//		measure.setProcessedRecords(dispatch.getRecords());
//		measure.outputMeasure();
//	}
//}
