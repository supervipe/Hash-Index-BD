package com.br.hashindex.controller;

import com.br.hashindex.model.Configuration;
import com.br.hashindex.model.Database;
import com.br.hashindex.model.Table;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@RestController
@RequestMapping("/table")
public class TableController {

    @CrossOrigin
    @PostMapping
    public ResponseEntity create() throws IOException {
        if (Configuration.getPageSize() == 0 || Configuration.getBucketSize() == 0) {
            Map<String, Object> res = new LinkedHashMap<>();
            res.put("error", "Page size = 0 or Bucket size = 0" );

            return ResponseEntity.status(400).body(res);
        } else {
            String data = "";
            try {
                File myObj = new File("words.txt");
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNextLine()) {
                    data = data + myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            Database database = Configuration.getDatabase();
            database.readTable(data);

            Map<String, Object> res = new LinkedHashMap<>();
            res.put("success", "Read Completed");
            res.put(
                "readSize",
                database.getTable().getTuplas().size()
            );
            res.put("pageSize", Configuration.getPageSize());
            res.put("bucketSize", Configuration.getBucketSize());
            res.put("colisionCount", Configuration.getColisionCount());
            res.put("overflowCount", Configuration.getOverflowCount());
            res.put("bucketNumber", Configuration.getBucketNumber());

            return ResponseEntity.status(201).body(res);
        }
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity show() throws FileNotFoundException, UnsupportedEncodingException {
        Map<String, Object> res = new LinkedHashMap<>();
        BufferedReader data = null;
        if (Configuration.getPageSize() == 0 || Configuration.getBucketSize() == 0) {
            res.put("error", "Page size = 0 or Bucket size = 0" );

            return ResponseEntity.status(400).body(res);
        } else {
            try {
                data = new BufferedReader(new FileReader("words.txt"));

            } catch (FileNotFoundException e) {
                res.put("erro", "no file found");
                e.printStackTrace();
            }

            Database database = Configuration.getDatabase();
            database.readTable(data);
            res.put("success", "Read Completed");
            res.put(
                    "readSize",
                    database.getTable().getTuplas().size()
            );
            res.put("pageSize", Configuration.getPageSize());
            res.put("bucketSize", Configuration.getBucketSize());
            res.put("colisionCount", Configuration.getColisionCount());
            res.put("overflowCount", Configuration.getOverflowCount());
            res.put("bucketNumber", Configuration.getBucketNumber());

        }
        Database database = Configuration.getDatabase();
        Table table = database.getTable();


        if (table == null) {
            res.put("erro", "no table found");
            return ResponseEntity.status(404).body(res);
        }

        res.put(
            "readSize",
            database.getTable().getTuplas().size()
        );
        res.put("pageSize", Configuration.getPageSize());
        res.put("bucketSize", Configuration.getBucketSize());
        res.put("colisionCount", Configuration.getColisionCount());
        res.put("overflowCount", Configuration.getOverflowCount());
        res.put("bucketNumber", Configuration.getBucketNumber());

        return ResponseEntity.status(200).body(res);
    }

    @CrossOrigin
    @GetMapping("/search")
    public ResponseEntity searchReg(@RequestParam(value = "search") String search) {
        Database database = Configuration.getDatabase();
        Table table = database.getTable();
        Map<String, Object> res = new LinkedHashMap<>();

        if (table == null) {
            res.put("erro", "no table found");
            return ResponseEntity.status(404).body(res);
        }

        int cost = table.getName(search);
        res.put("name", search);
        res.put("cost", cost);

        return ResponseEntity.status(200).body(res);
    }
}
