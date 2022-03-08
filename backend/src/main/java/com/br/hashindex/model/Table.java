package com.br.hashindex.model;

import com.br.hashindex.util.FHash;
import com.br.hashindex.util.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class Table {
    private ArrayList<Tupla> tuplas = new ArrayList<>();
    private Page firstPage;
    private Map<Integer, Bucket> buckets;

    public Table(Map<Integer, Bucket> buckets) {
        this.buckets = buckets;
        int maxSizePage = Configuration.getPageSize();
        this.firstPage = new Page(maxSizePage);
    }

    public ArrayList<Tupla> getTuplas() {
        return tuplas;
    }

    public int getName(String name) {
        int hash = FHash.FHash7(name, tuplas.size());
//        int hash = FHash.FHash6(name);

        Bucket bucket = buckets.get(hash);

        if (bucket == null) {
            return -1;
        }

        return bucket.getReg(name);
    }

    public void fillPages(String path) throws FileNotFoundException, UnsupportedEncodingException {
        int maxSizePage = Configuration.getPageSize();
        this.firstPage = new Page(maxSizePage);

        Util.readFile(path, firstPage, tuplas);
    }

    public void fillPages(BufferedReader br) {
        int maxSizePage = Configuration.getPageSize();
        this.firstPage = new Page(maxSizePage);

        Util.readFile(br, firstPage, tuplas);
    }

    public void hashGenerator(Map<Integer, Bucket> buckets) {
        Page page = this.firstPage;

        while (page != null) {
            ArrayList<Tupla> tuplas = page.getRegister();

            for (Tupla tupla : tuplas) {
                String name = tupla.getName();
                int hash = FHash.FHash7(name, this.tuplas.size());
//                int hash = FHash.FHash6(name);

                if (buckets.get(hash) == null) {
                    Bucket newBucket = new Bucket(Configuration.getBucketSize());

                    Configuration.incrementBucketNumber();

                    newBucket.addRef(page);
                    buckets.put(hash, newBucket);
                } else {
                    Bucket bucket = buckets.get(hash);
                    Configuration.incrementColisionCount();

                    bucket.addRef(page);
                }
            }

            page = page.getNextPage();
        }
    }
}
