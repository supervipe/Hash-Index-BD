package com.br.hashindex.model;

public class Configuration {
    static private int pageSize;
    static private int bucketSize;
    static private int colisionCount;
    static private int overflowCount;
    static private int bucketNumber;
    static private Database database;

    public static class Builder {
        private int pageSize = 0;
        private int bucketSize = 0;
        private int colisionCount = 0;
        private int overflowCount = 0;
        private int bucketNumber = 0;
        private Database database = null;

        public Builder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder setBucketSize(int bucketSize) {
            this.bucketSize = bucketSize;
            return this;
        }

        public Builder setColisionCount(int colisionCount) {
            this.colisionCount = colisionCount;
            return this;
        }

        public Builder setOverflowCount(int overflowCount) {
            this.overflowCount = overflowCount;
            return this;
        }

        public Builder setDatabase(int bucketNumber) {
            this.bucketNumber = bucketNumber;
            return this;
        }

        public Builder setDatabase(Database database) {
            this.database = database;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }

    public Configuration() {}

    public Configuration(int pageSize, int bucketSize, int colisionCount, int overflowCount, int bucketNumber) {
        Configuration.pageSize = pageSize;
        Configuration.bucketSize = bucketSize;
        Configuration.colisionCount = colisionCount;
        Configuration.overflowCount = overflowCount;
        Configuration.bucketNumber = bucketNumber;
    }

    public Configuration(Builder builder) {
        Configuration.pageSize = builder.pageSize;
        Configuration.bucketSize = builder.bucketSize;
        Configuration.colisionCount = builder.colisionCount;
        Configuration.overflowCount = builder.overflowCount;
        Configuration.bucketNumber = builder.bucketNumber;
        Configuration.database = builder.database;

    }

    public static void resetValues() {
        Configuration.colisionCount = 0;
        Configuration.overflowCount = 0;
        Configuration.bucketNumber = 0;
    }

    public static int getPageSize() {
        return pageSize;
    }

    public static void setPageSize(int pageSize) {
        Configuration.pageSize = pageSize;
    }

    public static int getBucketSize() {
        return bucketSize;
    }

    public static void setBucketSize(int bucketSize) {
        Configuration.bucketSize = bucketSize;
    }

    public static int getColisionCount() {
        return colisionCount;
    }

    public static void setColisionCount(int colisionCount) {
        Configuration.colisionCount = colisionCount;
    }

    public static void incrementColisionCount() {
        Configuration.colisionCount++;
    }

    public static int getOverflowCount() {
        return overflowCount;
    }

    public static void setOverflowCount(int overflowCount) {
        Configuration.overflowCount = overflowCount;
    }

    public static void incrementOverflowCount() {
        Configuration.overflowCount++;
    }

    public static void incrementBucketNumber() {
        Configuration.bucketNumber++;
    }

    public static int getBucketNumber() {
        return bucketNumber;
    }

    public static Database getDatabase() {
        return database;
    }

    public static void setDatabase(Database database) {
        Configuration.database = database;
    }


}
