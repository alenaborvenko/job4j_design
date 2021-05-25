package ru.job4j.work;

public class Passport {
    private String passport;

    public Passport(String passport) {
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "passport=" + getPassport();
    }
}
