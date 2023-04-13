package com.giftr.gifting;

public enum Occasion {
    EASTER("Easter"),
    CHRISTMAS("Christmas"),
    GRADUATION("Graduation"),
    ANNIVERSARY("Anniversary"),
    HANUKKAH("Hanukkah"),
    FITR("Eid al-Fitr");

    private final String displayValue;

    private Occasion(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
