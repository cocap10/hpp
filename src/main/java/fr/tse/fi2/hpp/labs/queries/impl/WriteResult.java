package fr.tse.fi2.hpp.labs.queries.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class WriteResult implements Runnable {
	/**
	 * Writer to write the output of the queries
	 */
	private BufferedWriter outputWriter;
	private int id;
	public final BlockingQueue<String> resultqueue;

	final static Logger logger = LoggerFactory
			.getLogger(WriteResult.class);

	public WriteResult(int id, BlockingQueue<String> resQ ) {
		// TODO Auto-generated constructor stub
		super();
		this.id=id;

		this.resultqueue=resQ;
		// Initialize writer
		try {
			outputWriter = new BufferedWriter(new FileWriter(new File(
					"result/query" + id + ".txt")));
		} catch (IOException e) {
			logger.error("Cannot open output file for " + id, e);
			System.exit(-1);
		}
	}

	@Override
	public void run() {
		//recup queue
		//ecrire
		while (true){
			try {
				String line = resultqueue.take();
				if(line.equals("DIE!!!")){
					break;
				}
				writeLine("moy" +line);	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		finish();


	}


	private void finish() {
		// TODO Auto-generated method stub
		// Close writer
		try {
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) {
			logger.error("Cannot property close the output file for query "
					+ id, e);
		}
	}

	protected void writeLine(String line) {
		try {
			outputWriter.write(line);
			outputWriter.newLine();
		} catch (IOException e) {
			logger.error("Could not write new line for query processor " + id
					+ ", line content " + line, e);
		}

	}


}
