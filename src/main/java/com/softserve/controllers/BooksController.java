package com.softserve.controllers;

import com.softserve.dao.BookDAO;
import com.softserve.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private BookDAO bookDAO;
    @Autowired
    public BooksController(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showAllBooks(Model model){
        model.addAttribute("books",bookDAO.showAllBooks());
        return "books/show";
    }
    @GetMapping("/unBooks")
    public String showUnavailableBooks(Model model){
        model.addAttribute("unbooks",bookDAO.showUnavailableBooks());
        return "books/unBooks";
    }
    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
      return "books/new";
    }
    @PostMapping()
    public String addBook(@ModelAttribute("book") Book book){
        bookDAO.addBook(book);
        return "redirect:/books";
    }

}
