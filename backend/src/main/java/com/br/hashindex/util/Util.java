package com.br.hashindex.util;

import com.br.hashindex.model.Configuration;
import com.br.hashindex.model.Page;
import com.br.hashindex.model.Tupla;

import java.io.*;
import java.util.ArrayList;

public class Util {

    public static String padRight(String text, int size) {
        return String.format("%1$" + size + "s", text).replace(" ", text).substring(0, size);
    }

    public static void addTupla(ArrayList<Tupla> tuplas, String name) {
        Tupla newTupla = new Tupla(name);

        tuplas.add(newTupla);
    }

    public static void readFile(
            String path,
            Page firstPage,
            ArrayList<Tupla> tuplas
    ) throws FileNotFoundException, UnsupportedEncodingException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "ISO_8859_1"));

        try {
            Page page = firstPage;

            while (br.ready()) {
                if (br.ready()) {
                    String register = br.readLine();

                    addTupla(tuplas, register);

                    if (!page.setRegister(register)) page = page.getNextPage();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void readFile(
            BufferedReader br,
            Page firstPage,
            ArrayList<Tupla> tuplas
    ) {
        if (Configuration.getPageSize() == 0 || Configuration.getBucketSize() == 0) return;

        try {
            Page page = firstPage;

            while (br.ready()) {
                if (br.ready()) {
                    String register = br.readLine();

                    addTupla(tuplas, register);

                    if (!page.setRegister(register)) page = page.getNextPage();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
