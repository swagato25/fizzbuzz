package com.roche.fizzbuzz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class FizzBuzzController {
    private Map<Map<String, String>, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    @GetMapping("/fizzbuzz")
    public List<String> fizzBuzz(@RequestParam(defaultValue = "3") int int1,
                                 @RequestParam(defaultValue = "5") int int2,
                                 @RequestParam(defaultValue = "100") int limit,
                                 @RequestParam(defaultValue = "fizz") String str1,
                                 @RequestParam(defaultValue = "buzz") String str2) {
        updateRequestCount(String.valueOf(int1), String.valueOf(int2), String.valueOf(limit), str1, str2);
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            if (i % (int1 * int2) == 0) {
                result.add(str1 + str2);
            } else if (i % int1 == 0) {
                result.add(str1);
            } else if (i % int2 == 0) {
                result.add(str2);
            } else {
                result.add(Integer.toString(i));
            }
        }
        return result;
    }

    @GetMapping("/statistics")
    public Map<String, String> statistics() {
        return requestCounts.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getValue().get()))
                .map(entry -> {
                    Map<String, String> result = new HashMap<>(entry.getKey());
                    result.put("count", String.valueOf(entry.getValue().get()));
                    return result;
                })
                .orElse(Collections.emptyMap());
    }

    private void updateRequestCount(String int1, String int2, String limit, String str1, String str2) {
        Map<String, String> params = new HashMap<>();
        params.put("int1", String.valueOf(int1));
        params.put("int2", String.valueOf(int2));
        params.put("limit", String.valueOf(limit));
        params.put("str1", str1);
        params.put("str2", str2);
        requestCounts.computeIfAbsent(params, k -> new AtomicInteger()).incrementAndGet();
    }
}
