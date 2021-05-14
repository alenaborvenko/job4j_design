package ru.job4j.serializable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UsageXML {
    public static void main(String[] args) throws IOException {
        Student student = new Student(
                true, 20, "Pol", new Contact(1, "7987545"),
                "Math", "Computer science"
        );
        ArrayList<String> studentToXML = studenttoXML(student);
        try (PrintWriter pw = new PrintWriter(new FileWriter("./student.xml"))) {
            for (String line : studentToXML) {
                pw.println(line);
            }
        }
    }
    public static ArrayList<String> studenttoXML(Student student) {
        ArrayList<String> rsl = new ArrayList<>();
        rsl.add("<?xml version=\"1.1\" encoding=\"UTF-8\" ?>");
        rsl.add("<student sex=\"" + student.isSex() + "\" age=\"" + student.getAge()
                + "\" name=\"" + student.getName() + "\">");
        rsl.add("\t<contact zipCode=\"" + student.getContact().getZipCode()
                + "\" phone=\"" + student.getContact().getPhone() + "\" />");
        rsl.add("\t<courses>");
        for (String course : student.getCourse()) {
            rsl.add("\t\t<course>" + course + "</course>");
        }
        rsl.add("\t</courses>");
        rsl.add("</student>");
        return rsl;
    }
}
