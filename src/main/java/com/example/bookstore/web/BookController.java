package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;



@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository; 

	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
		public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());

		return "booklist";
	}
}
