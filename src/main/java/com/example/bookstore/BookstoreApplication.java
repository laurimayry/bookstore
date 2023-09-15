package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

//https://github.com/laurimayry/bookstore.git

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner studentDemo(CategoryRepository repository, CategoryRepository grepository) {
		return (args) -> {
			log.info("save a couple of students");
			grepository.save(new Category("Fantasy"));
			grepository.save(new Category("Horror"));
			grepository.save(new Category("Classic"));


		};
	}
}
