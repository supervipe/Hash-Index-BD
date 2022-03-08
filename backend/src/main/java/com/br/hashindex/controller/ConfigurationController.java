package com.br.hashindex.controller;

import com.br.hashindex.model.Configuration;
import com.br.hashindex.model.RequestConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

    @CrossOrigin
    @PostMapping
    public ResponseEntity create(@RequestBody RequestConfiguration requestConfiguration) {
        Configuration.setPageSize(requestConfiguration.getPageSize());
        Configuration.setBucketSize(requestConfiguration.getBucketSize());

        Map<String, Object> res = new LinkedHashMap<>();
        res.put("pageSize", Configuration.getPageSize());
        res.put("bucketSize", Configuration.getBucketSize());

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity show() {
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("pageSize", Configuration.getPageSize());
        res.put("bucketSize", Configuration.getBucketSize());

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
