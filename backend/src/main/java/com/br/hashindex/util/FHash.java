package com.br.hashindex.util;

import com.br.hashindex.model.Configuration;

public class FHash {

    public static int FHash1(String text) {
        char[] words = text.toCharArray();
        int hash = 0;

        for (char word : words) hash += word;

        return hash;
    }

    public static int FHash2(String text) {
        char[] words = text.toCharArray();
        int hash = 0;

        for (char word : words) hash += word;

        hash = (int) (Math.log(hash) * Math.log(hash) * hash);

        return hash;
    }

    public static int FHash3(String text) {
        char[] words = Util.padRight(text, 80).toCharArray();
        int hash = 0;

        for (int i = 0; i < words.length; i++) hash += words[i] * (i + 1);

        hash = (int) (Math.log(hash) * Math.log(hash) * Math.pow(Configuration.getBucketSize(), 2));

        return hash;
    }

    public static int FHash4(String text) {
        char[] words = text.toCharArray();
        int hash = 0;

        for (char word : words) {
            hash += word;
            hash += (hash << 10);
            hash ^= (hash >>> 6);
        }

        return hash;
    }

    public static int FHash5(String text) {
        char[] words = text.toCharArray();
        int hash = 0;

        for (char word : words) {
            hash += word;
            hash += (hash << Configuration.getBucketSize() / 2);
            hash ^= (hash >>> Configuration.getBucketSize());
        }

        return hash;
    }

    public static int FHash6(String text) {
        char[] words = Util.padRight(text, 80).toCharArray();
        int hash = 0;

        for (int i = 0; i < words.length; i++) hash += words[i] * (i + 1);

        hash = (int) (Math.log(hash) * Math.log(hash) * hash);

        return hash;
    }

    public static int FHash7(String text, int tuplasSize) {
        char[] words = text.toCharArray();
        int maxSize = (tuplasSize / Configuration.getBucketSize()) + 1;
        int hash = 0;

        for (int i = 0; i < words.length; i++) {
            hash = (hash << 5) | (hash >>> 27);
            hash += (hash << 10);
            hash ^= (hash >> 6);
            hash += words[i] * (i + 1);
        }

        hash = (hash + 1) % maxSize;

        return hash;
    }
}
