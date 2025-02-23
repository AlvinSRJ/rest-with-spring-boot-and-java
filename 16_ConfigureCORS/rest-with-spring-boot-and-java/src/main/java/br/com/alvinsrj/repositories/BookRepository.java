package br.com.alvinsrj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alvinsrj.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{}
