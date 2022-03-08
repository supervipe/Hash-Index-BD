package com.br.hashindex.model;

import java.util.ArrayList;

public class Page {
    private ArrayList<Tupla> register;
    private int maxSize;
    private Page nextPage;

    public Page(int maxSize) {
        this.register = new ArrayList<>();
        this.maxSize = maxSize;
        this.nextPage = null;
    }

    public Page getNextPage() {
        return nextPage;
    }

    public void setNextPage(Page nextPage) {
        this.nextPage = nextPage;
    }

    public ArrayList<Tupla> getRegister() {
        return register;
    }

    public boolean setRegister(String text) {
        if (Configuration.getPageSize() == 0 || Configuration.getBucketSize() == 0) return false;
        int size = this.register.size();

        if (size < this.maxSize) {
            Tupla tupla = new Tupla(text);

            register.add(tupla);
            return true;
        } else {
            Page newPage = new Page(maxSize);
            newPage.setRegister(text);

            this.nextPage = newPage;
            return false;
        }
    }
}
