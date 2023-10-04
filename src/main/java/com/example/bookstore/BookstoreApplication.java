package com.example.bookstore;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.CategoryRepository;

import com.example.bookstore.domain.AppUser;
import com.example.bookstore.domain.AppUserRepository;

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
	public CommandLineRunner studentDemo(BookRepository repository, CategoryRepository grepository, AppUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			grepository.save(new Category("Fantasy"));
			grepository.save(new Category("Horror"));
			grepository.save(new Category("Classic"));
			
			repository.save(new Book("sddfas", "Lauri", 1, 1, 1, grepository.findByName("Fantasy").get(0)));
			repository.save(new Book("Katfdafay", "Harry", 1, 1, 1, grepository.findByName("Classic").get(0)));
			
			// Create users: admin/admin user/user
						AppUser user1 = new AppUser("user", "lauri.mayry@gmail.com", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
						AppUser user2 = new AppUser("admin", "lauri.admin.mayry@gmail.com", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
						urepository.save(user1);
						urepository.save(user2);


			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
			
	};
	}}
