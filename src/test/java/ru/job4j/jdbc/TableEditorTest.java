package ru.job4j.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore
public class TableEditorTest {
    TableEditor tableEditor;
    PrintStream old = System.out;
    ByteArrayOutputStream print = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        Properties properties = new Properties();
        properties.setProperty("Driver", "org.postgresql.Driver");
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/idea_db");
        properties.setProperty("login", "postgres");
        properties.setProperty("password", "password");
        tableEditor = new TableEditor(properties);
        System.setOut(new PrintStream(print));
        tableEditor.dropTable("test");
    }

    @Test
    public void whenCreateTable() throws SQLException {
        tableEditor.dropTable("test");
        tableEditor.createTable("test");
        assertThat(tableEditor.getScheme("test"), is(String.format("%-15s %-15s%n", "column", "type")
                + String.format("%-15s %-15s%n", "id", "serial")));
    }

    @Test
    public void whenDropTable() throws SQLException {
        tableEditor.dropTable("test");
        assertThat(tableEditor.getScheme("test"), is(String.format("%-15s %-15s%n", "column", "type")));
    }

    @Test
    public void addTableColumnTableNotFound() {
        tableEditor.addColumn("test", "name", "varchar(255)");
        assertThat(print.toString(), is("Cant execute statement!" + System.lineSeparator()));
    }

    @Test
    public void addTableColumn() throws SQLException {
        tableEditor.createTable("test");
        tableEditor.addColumn("test", "name", "varchar");
        assertThat(tableEditor.getScheme("test"), is(
                String.format("%-15s %-15s%n", "column", "type")
                        + String.format("%-15s %-15s%n", "id", "serial")
                        + String.format("%-15s %-15s%n", "name", "varchar")));
    }

    @Test
    public void dropTableColumnTableNotFound() {
        tableEditor.dropColumn("test", "name");
        assertThat(print.toString(), is("Cant execute statement!" + System.lineSeparator()));
    }

    @Test
    public void dropTableColumn() throws SQLException {
        tableEditor.createTable("test");
        tableEditor.addColumn("test", "name", "varchar");
        tableEditor.dropColumn("test", "name");
        assertThat(tableEditor.getScheme("test"), is(
                String.format("%-15s %-15s%n", "column", "type")
                        + String.format("%-15s %-15s%n", "id", "serial")));
    }

    @Test
    public void renameTableColumnTableNotFound() {
        tableEditor.renameColumn("test", "id", "id_test");
        assertThat(print.toString(), is("Cant execute statement!" + System.lineSeparator()));
    }

    @Test
    public void renameTableColumn() throws SQLException {
        tableEditor.createTable("test");
        tableEditor.addColumn("test", "name", "varchar");
        tableEditor.renameColumn("test", "name", "name_test");
        assertThat(tableEditor.getScheme("test"), is(
                String.format("%-15s %-15s%n", "column", "type")
                        + String.format("%-15s %-15s%n", "id", "serial")
                        + String.format("%-15s %-15s%n", "name_test", "varchar")));
    }

    @After
    public void after() {
        System.setOut(old);
    }
}