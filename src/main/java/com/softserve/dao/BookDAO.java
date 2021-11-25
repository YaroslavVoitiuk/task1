package com.softserve.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.softserve.models.Book;
import org.springframework.stereotype.Component;


@Component
public class BookDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/Library";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "8667";

    private static Connection connection;


    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Book> showAllBooks(){
        List<Book> books = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM books";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Book book = new Book();
                book.setBookName(resultSet.getString("name"));
                book.setAvailability(resultSet.getBoolean("availability"));

                books.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;

    }

    public List<Book> showUnavailableBooks(){
        List<Book> unavailBooks = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM books WHERE availability=false ";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Book book = new Book();
                book.setBookName(resultSet.getString("name"));
                book.setAvailability(resultSet.getBoolean("availability"));

                unavailBooks.add(book);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return unavailBooks;
    }

    public void addBook(Book book){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO books (name) VALUES (?)");

            preparedStatement.setString(1,book.getBookName());
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
