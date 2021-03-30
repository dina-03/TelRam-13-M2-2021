package de.telran.hello_web.dto;

public class Greetings {

    public Greetings() {
    }

    public String name;
    public String greetings;

    public Greetings(String name, String greetings) {
        this.name = name;
        this.greetings = greetings;
    }

    @Override
    public String toString() {
        return greetings + " " + name + "!";
    }
}
