package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.model.BookSearchParameter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class BookService {
    private final BookDAO dao;

    public BookService() {
        this.dao = new BookDAO();
    }

    public Book create(String title, String isbn, int publicationYear) {
        // TODO: validate creation parameters
        Book book = Book.builder()
                .title(title)
                .isbn(isbn)
                .publicationYear(publicationYear)
                .build();

        int createdId = dao.create(book);
        book.setBookId(createdId);
        log.info("Created book {}", book.toString());

        return book;
    }

    public List<Book> getAll() {
        log.info("Getting all books");
        return dao.getAll();
    }

    public List<Book> getById(int bookId) {
        log.info("Getting book by id {}", bookId);
        return dao.getBy(BookSearchParameter.BOOK_ID, bookId);
    }

    public List<Book> getByTitle(String title) {
        log.info("Getting book by title {}", title);
        return dao.getBy(BookSearchParameter.TITLE, title);
    }

    public List<Book> getByIsbn(String isbn) {
        log.info("Getting book by isbn {}", isbn);
        return dao.getBy(BookSearchParameter.ISBN, isbn);

    }

    public List<Book> getByPublicationYear(int publicationYear) {
        log.info("Getting book by publication year {}", publicationYear);
        return dao.getBy(BookSearchParameter.PUBLISHED_YEAR, publicationYear);
    }

    // provide nulls if don't want to change
    public Book update(Book book, String title, String isbn, int publicationYear) {
        if (title != null) {
            book.setTitle(title);
        }
        if (isbn != null) {
            book.setIsbn(isbn);
        }
        if (publicationYear != 0) {
            book.setPublicationYear(publicationYear);
        }

        dao.update(book);
        log.info("Updating book {}", book.toString());

        return book;
    }

    public void delete (int bookId) {
        dao.delete(bookId);
        log.info("Deleted book with id {}", bookId);
    }
}
