package fr.tse.fi2.hpp.labs.queries.impl.lab4;

import java.util.Random;

public class RandomString {
private static final char[] symbols;

    private static final Random random = new Random();

    static {
        final StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            tmp.append(ch);
        }
        symbols = tmp.toString().toCharArray();
    }

    public static String nextString(final int length) {
        final char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }
}