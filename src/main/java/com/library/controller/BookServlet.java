package com.library.controller;

import com.google.gson.Gson;
import com.library.dao.BookDAO;
import com.library.dto.BookDTO;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.util.DBConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "bookServlet", value = "/books")
public class BookServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        BookService bookService = new BookService();
        List<BookDTO> books = bookService.getAll().stream().map(BookService::getBookDTO).toList();

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String jsonBooks = gson.toJson(books);

        PrintWriter out = res.getWriter();
        out.println(jsonBooks);
        out.flush();
    }
}
