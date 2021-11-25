package com.softserve.controllers;

import com.softserve.dao.BookDAO;
import com.softserve.models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("unBooks",bookDAO.showUnavailableBooks());
        return "books/show";
    }
//    @GetMapping("/unBooks")
//    public String showUnavailableBooks(Model model){
//        model.addAttribute("unbooks",bookDAO.showUnavailableBooks());
//        return "books/unBooks";
//    }
    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
      return "books/new";
    }
    @PostMapping()
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/new";
        bookDAO.addBook(book);
        return "redirect:/books";
    }

}
