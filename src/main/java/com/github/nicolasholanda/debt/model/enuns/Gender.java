package com.github.nicolasholanda.debt.model.enuns;

public enum Gender {

    MALE('M'),
    FEMALE('F'),
    UNISEX('U');

    private char letter;

    Gender(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }
}
