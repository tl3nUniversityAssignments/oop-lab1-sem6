package com.library.service;

import com.library.dao.AuthorDAO;
import com.library.model.Author;
import com.library.model.AuthorSearchParameter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class AuthorService {
    private final AuthorDAO dao;

    public AuthorService() {
        this.dao = new AuthorDAO();
    }

    public Author create(String name) {
        Author author = Author.builder()
                .name(name)
                .build();

        int createdId = dao.create(author);
        author.setAuthorId(createdId);
        log.info("Created author {}", author.toString());

        return author;
    }

    public List<Author> getAll() {
        return dao.getAll();
    }

    public List<Author> getById(int authorId) {
        return dao.getBy(AuthorSearchParameter.AUTHOR_ID, authorId);
    }

    public List<Author> getByName(String name) {
        return dao.getBy(AuthorSearchParameter.NAME, name);
    }

    public Author update(Author author, String name) {
        if (name != null) {
            author.setName(name);
        }

        dao.update(author);
        return author;
    }

    public void delete(int authorId) {
        dao.delete(authorId);
    }
}
