package com.library.service;

import com.library.dao.ReaderDAO;
import com.library.model.Reader;
import com.library.model.ReaderSearchParameter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ReaderService {
    private final ReaderDAO dao;

    public ReaderService() {
        this.dao = new ReaderDAO();
    }

    public Reader create(String name, String address, String phone, String email) {
        Reader reader = Reader.builder()
                .name(name)
                .address(address)
                .phone(phone)
                .email(email)
                .build();

        int createdId = dao.create(reader);
        reader.setReaderId(createdId);
        log.info("Created new reader {}", reader.toString());

        return reader;
    }

    public List<Reader> getAll() {
        log.info("Getting all readers");
        return dao.getAll();
    }

    public List<Reader> getById(int readerId) {
        log.info("Getting reader by id {}", readerId);
        return dao.getBy(ReaderSearchParameter.READER_ID, readerId);
    }

    public List<Reader> getByName(String name) {
        log.info("Getting readers by name {}", name);
        return dao.getBy(ReaderSearchParameter.NAME, name);
    }

    public List<Reader> getByAddress(String address) {
        log.info("Getting readers by address {}", address);
        return dao.getBy(ReaderSearchParameter.ADDRESS, address);
    }

    public List<Reader> getByPhone(String phone) {
        log.info("Getting readers by phone {}", phone);
        return dao.getBy(ReaderSearchParameter.PHONE, phone);
    }

    public List<Reader> getByEmail(String email) {
        log.info("Getting readers by email {}", email);
        return dao.getBy(ReaderSearchParameter.EMAIL, email);
    }

    public Reader update(Reader reader, String name, String address, String phone, String email) {
        if (name != null) {
            reader.setName(name);
        }
        if (address != null) {
            reader.setAddress(address);
        }
        if (phone != null) {
            reader.setPhone(phone);
        }
        if (email != null) {
            reader.setEmail(email);
        }

        dao.update(reader);
        log.info("Updated reader {}", reader.toString());
        return reader;
    }

    public void delete(int readerId) {
        log.info("Deleting reader with id {}", readerId);
        dao.delete(readerId);
    }

}
