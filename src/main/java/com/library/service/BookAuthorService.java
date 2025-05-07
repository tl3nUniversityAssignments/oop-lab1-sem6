package com.library.service;

import com.library.dao.BookAuthorDAO;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.BookAuthor;
import com.library.model.BookAuthorSearchParameter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BookAuthorService {
    private final BookAuthorDAO dao;
    private final BookService bookService;
    private final AuthorService authorService;

    public BookAuthorService() {
        this.dao = new BookAuthorDAO();
        this.bookService = new BookService();
        this.authorService = new AuthorService();
    }

    public BookAuthor addAuthorToBook(int bookId, int authorId) {
        BookAuthor bookAuthor = BookAuthor.builder()
                .bookId(bookId)
                .authorId(authorId)
                .build();

        dao.create(bookAuthor);
        log.info("Created bookAuthor {}", bookAuthor);
        return bookAuthor;
    }

    public List<Book> getBooksByAuthor(int authorId) {
        List<BookAuthor> authorBooks = dao.getBy(BookAuthorSearchParameter.AUTHOR_ID, authorId);
        List<Book> books = new ArrayList<>();

        authorBooks.forEach(authorsBook -> {
            int bookId = authorsBook.getBookId();
            books.add(bookService.getById(bookId).getFirst());
        });
        return books;
    }

    public List<Author> getAuthorsOfBook(int bookId) {
        List<BookAuthor> bookAuthors = dao.getBy(BookAuthorSearchParameter.BOOK_ID, bookId);
        List<Author> authors = new ArrayList<>();

        bookAuthors.forEach(bookAuthor -> {
            int authorId = bookAuthor.getAuthorId();
            authors.add(authorService.getById(authorId).getFirst());
        });
        return authors;
    }

    public List<String> getAuthorNames(int bookId) {
        List<Author> authors = getAuthorsOfBook(bookId);
        return authors.stream().map(Author::getName).toList();
    }

    public void deleteAuthorFromBook(BookAuthor bookAuthor) {
        dao.delete(bookAuthor);
    }
}
