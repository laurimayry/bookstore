package com.example.bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

	//BOOK CONTROLLER
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());

		return "booklist";
	}

	//FUNCTIONALITY FOR ADDING BOOKS
	
	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}

	//FUNCTIONALITY FOR SAVING BOOKS

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	
	//FUNCTIONALITY FOR DELETING BOOKS

	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long Id, Model model) {
	repository.deleteById(Id);
	return "redirect:../booklist";
	}
	
	//FUNCTIONALITY FOR EDITING BOOKS
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long Id, Model model) {

		Optional<Book> optionalBook = repository.findById(Id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			model.addAttribute("book", book);
			return "editbook";
		} else {

			return "redirect:/booklist";
		}
	}

	//FUNCTIONALITY FOR UPDATING EDITED BOOKS

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute Book updatedBook) {
		
		Book existingBook = repository.findById(updatedBook.getId()).orElse(null);
	    if (existingBook != null) {
	        // Päivitä kirjan tiedot
	        existingBook.setAuthor(updatedBook.getAuthor());
	        existingBook.setTitle(updatedBook.getTitle());
	        existingBook.setIsbn(updatedBook.getIsbn());
	        existingBook.setPublicationYear(updatedBook.getPublicationYear());
	        
	        repository.save(existingBook);
	        return "redirect:/booklist";
	        
	    } else {
	        return "redirect:/booklist";
	    }
}
}
