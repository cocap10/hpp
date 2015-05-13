package fr.tse.fi2.hpp.labs.queries.impl.lab4;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;
/**
 * @author root
 *
 */
public class FiltreBloom extends AbstractQueryProcessor {
	/**
	 * The filter
	 */
	private BitSet bloom=null;
	/**
	 * Number of items in the filter
	 */
	private final int n;
	/**
	 * Probability of false positives, float between 0 and 1 or a number indicating 1-in-p
	 */
	private final double p;
	/**
	 * Number of bits in the filter
	 */
	private static int m;
	/**
	 * Number of hash functions
	 */
	private int k;
	public static final int SALT_SIZE = 100;
	public final String[] salts;

	public FiltreBloom(QueryProcessorMeasure measure, int n, double p) {
		super(measure);
		/*
		 * Bloom Filter with 1000Records.csv 
		 * We want 0.1% probability error
		 * http://hur.st/bloomfilter?n=1000&p=0.001
		 * The size of the bloom filter should be m=14 378b(or1.76KB)
		 * With k=10 Hash functions
		 */
		//size of bloom filter 
		//ceil : partie sup
		this.n=n;
		this.p=p;
		this.m = (int) Math.ceil((this.n * Math.log(this.p)) / Math.log(1.0 / (Math.pow(2.0, Math.log(2.0)))));
		this.k = (int) Math.round(Math.log(2.0) * this.m / this.n);
		this.bloom=new BitSet(m);
		this.salts=new String[this.k];
		for (int i = 0; i < this.k; i++) {
			this.salts[i] = RandomString.nextString(SALT_SIZE);
		}
	}
	public int hash(final String message) {
        final HashFunction hasher = Hashing.murmur3_32();
        return Math.abs(hasher.hashUnencodedChars(message).asInt() % this.m);
        // return Math.abs(SHA3Util.digest(message, 512) % this.m);
    }

	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
		add(record.toString());
	}
	
	public void add(final String message) {
        for (final String salt : this.salts) {
            this.bloom.set(this.hash(message + salt));
        }
    }

    public boolean contains(final String message) {
        int i = 0;
        while (i < this.salts.length) {
            final String salt = this.salts[i];
            if (!this.bloom.get(this.hash(message + salt))) {
                return false;
            }
            i++;
        }
        return true;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i = 0;
        while (i < this.bloom.size() - 1) {
            sb.append(this.bloom.get(i) ? 1 : 0);
            i++;
        }
        sb.append("]");
        return sb.toString();
    }



}
