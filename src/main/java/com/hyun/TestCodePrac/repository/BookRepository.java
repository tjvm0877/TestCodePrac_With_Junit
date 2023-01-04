package com.hyun.TestCodePrac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyun.TestCodePrac.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
