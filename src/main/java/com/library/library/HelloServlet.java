package com.library.library;

import java.io.*;
import java.sql.Connection;
import java.util.List;

import com.library.model.Book;
import com.library.model.BookSearchParameter;
import com.library.util.DBConnection;
import com.library.dao.BookDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = "hello world";
        Connection connection = null;
        try{
            connection = DBConnection.getConnection();
            if (connection != null) {
                message = "Connection successful";
            }
            else {
                message = "Connection failed";
            }
        } catch (Exception e) {
            message = "Error while connecting to db: " + e.getStackTrace().toString();
        }

        BookDAO bookDAO = new BookDAO(connection);
        List<Book> books = bookDAO.getBy(BookSearchParameter.TITLE, "The Hobbit");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body>");
        out.println("<h1>" + books + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}