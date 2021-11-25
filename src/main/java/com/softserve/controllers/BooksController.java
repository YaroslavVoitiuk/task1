package com.softserve.controllers;

import com.softserve.dao.BookDAO;
import com.softserve.models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BooksController {

    private BookDAO bookDAO;
    @Autowired
    public BooksController(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showAllBooks(Model model){
        model.addAttribute("books",bookDAO.showAllBooks());
        return "/show";
    }
    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello_world";
    }
//    @GetMapping()
//    public String showUnavailableBooks(Model model){
//        model.addAttribute("books",bookDAO.showUnavailableBooks());
//        return "/show";
//    }
//    @GetMapping("/new")
//    public String newBook(@ModelAttribute("book") Book book){
//      return "books/new";
//    }
//    @PostMapping
//    public String addBook(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult){
//        if (bindingResult.hasErrors())
//            return "books/new";
//        bookDAO.addBook(book);
//        return "redirect:/books";
//    }

}
