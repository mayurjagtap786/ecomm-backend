package com.example.auth;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestApp {

    public static void main(String[] args) {
        String str = "";

        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");

        String name = StringUtils.collectionToDelimitedString(names,", ");
        System.out.println(name);
    }

    public static String test(String str){
        Assert.notNull(str,"object should not null");
        return str;
    }
}
