package com.br.hashindex.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private Table table;
    private Map<Integer, Bucket> buckets;

    public Database() {
        this.table = null;
        this.buckets = new HashMap<>();
    }

    public Table getTable() {
        return table;
    }

    public void setTables(Table table) {
        this.table = table;
    }

    public void readTable(String path) throws FileNotFoundException, UnsupportedEncodingException {
        Configuration.resetValues();
        buckets.clear();
        
        Table table = new Table(this.buckets);
        table.fillPages(path);
        table.hashGenerator(buckets);
        this.table = table;
    }

    public void readTable(BufferedReader br) {
        Configuration.resetValues();
        buckets.clear();

        Table table = new Table(this.buckets);
        table.fillPages(br);
        table.hashGenerator(buckets);
        this.table = table;
    }
}
