package com.moerlong.carloan.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class IDGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(IDGenerator.class);

    public IDGenerator() {
    }

    public static String getNumUUID(){

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;

    }
    public static void main(String[] args) throws Exception {


    }
}
