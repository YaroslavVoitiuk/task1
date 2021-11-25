package com.softserve.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    @NotEmpty(message = "This field can`t be empty")
    @Size(min = 3, max = 20, message = "name should be between 3 and 20 characters")
    private String bookName;

    private boolean availability;

    public Book(){}

    public String getBookName() {
        return bookName;
    }

    @NotEmpty(message = "This field can`t be empty")
    @Size(min = 3, max = 20, message = "name should be between 3 and 20 characters")
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
