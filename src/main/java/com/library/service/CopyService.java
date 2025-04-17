package com.library.service;

import com.library.dao.CopyDAO;
import com.library.model.Copy;
import com.library.model.CopySearchParameter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class CopyService {
    private final CopyDAO dao;

    public CopyService() {
        this.dao = new  CopyDAO();
    }

    public Copy create(int bookId, boolean available) {
        Copy copy = Copy.builder()
                .bookId(bookId)
                .available(available)
                .build();

        int createdId = dao.create(copy);
        copy.setCopyId(createdId);
        log.info("Created new copy {}", copy.toString());
        return copy;
    }

    public List<Copy> getAll() {
        log.info("Getting all copies");
        return dao.getAll();
    }

    public List<Copy> getById(int copyId) {
        log.info("Getting copy by id {}", copyId);
        return dao.getBy(CopySearchParameter.COPY_ID, copyId);
    }

    public List<Copy> getByBookId(int bookId) {
        log.info("Getting copy by book id {}", bookId);
        return dao.getBy(CopySearchParameter.BOOK_ID, bookId);
    }

    public List<Copy> getByAvailability(boolean available) {
        log.info("Getting copies by availability {}", available);
        return dao.getBy(CopySearchParameter.AVAILABLE, available);
    }

    public Copy update(Copy copy, boolean available) {
        copy.setAvailable(available);
        dao.update(copy);
        log.info("Updated copy {}", copy.toString());
        return copy;
    }

    public void delete(int copyId) {
        dao.delete(copyId);
        log.info("Deleted copy with id {}", copyId);
    }
}
