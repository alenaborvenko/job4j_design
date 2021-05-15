package ru.job4j.serializable;

import javax.xml.bind.annotation.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private String name;

    private Contact contact;

    @XmlElementWrapper(name = "courses")
    @XmlElement(name = "course")
    private String[] course;

    public Student() {
    }

    public Student(boolean sex, int age, String name, Contact contact, String... course) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.contact = contact;
        this.course = course;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return sex == student.sex
                && age == student.age
                && Objects.equals(name, student.name)
                && Objects.equals(contact, student.contact)
                && Arrays.equals(course, student.course);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sex, age, name, contact);
        result = 31 * result + Arrays.hashCode(course);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("sex=" + sex)
                .add("age=" + age)
                .add("name='" + name + "'")
                .add("contact=" + contact)
                .add("course=" + Arrays.toString(course))
                .toString();
    }
}
