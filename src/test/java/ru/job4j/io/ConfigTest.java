package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = ".\\src\\main\\java\\ru\\job4j\\io\\pairWithoutComment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("Alena Borvenko"));
        assertThat(config.value("age"),is("30"));
        assertThat(config.value("weight"),is("59"));
        assertThat(config.value("height"),is("167"));
    }

    @Test
    public void whenPairWithComment() {
        String path = ".\\src\\main\\java\\ru\\job4j\\io\\pairWithComment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("Alena Borvenko"));
        assertThat(config.value("age"),is("30"));
        assertThat(config.value("weight"),is("59"));
        assertThat(config.value("height"),is("167"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExceptionPatternPair() {
        String path = ".\\src\\main\\java\\ru\\job4j\\io\\patternException.properties";
        Config config = new Config(path);
        config.load();
    }
}