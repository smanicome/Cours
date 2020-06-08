package fr.umlv.rank;

import java.util.Objects;

public class Review {
    private final String comment;
    private final int value;

    public Review(String comment, int value) {
        if (comment == null || value < 0 || value > 5) {
            throw new IllegalArgumentException("Bad arguments");
        }
        this.comment = comment;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<"+value+">" + " " + comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return value == review.value && comment.equals(review.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, value);
    }
}
