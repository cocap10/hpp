package fr.tse.fi2.hpp.labs.benchmark;

//
//import java.util.concurrent.TimeUnit;
//
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.BenchmarkMode;
//import org.openjdk.jmh.annotations.Fork;
//import org.openjdk.jmh.annotations.Measurement;
//import org.openjdk.jmh.annotations.Mode;
//import org.openjdk.jmh.annotations.OutputTimeUnit;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.Setup;
//import org.openjdk.jmh.annotations.State;
//import org.openjdk.jmh.annotations.Warmup;
//
//import fr.tse.fi2.hpp.labs.utils.RandomArray;
//import fr.tse.fi2.hpp.labs.utils.SimpleMergeSort;
//
//@State(Scope.Thread)
//public class BenchmarkMergeSort {
//	private int[] tab;
//
//	@Setup
//	public void init(){
//		tab=new RandomArray(1000000).nextArray();
//	}
//
//	@Benchmark
//	@BenchmarkMode(Mode.AverageTime)
//	@OutputTimeUnit(TimeUnit.MILLISECONDS)
//	@Fork(1)
//	@Warmup(iterations=3)
//	@Measurement(iterations=3)
//	public void maTestMethod() {
//		SimpleMergeSort.sort(tab);
//	}
//
//}
