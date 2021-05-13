package ru.job4j.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = -1;
        short b = 6;
        int c = -10;
        long h = 100_000_000L;
        char d = '\u3333';
        float e = 1.1f;
        double f = 1e-6;
        boolean j = true;
        LOG.debug("value a : {}", a);
        LOG.debug("value b : {}", b);
        LOG.debug("value c : {}", c);
        LOG.debug("value d : {}", d);
        LOG.debug("value e : {}", e);
        LOG.debug("value f : {}", f);
        LOG.debug("value j : {}", j);
        LOG.debug("value h : {}", h);
    }
}
