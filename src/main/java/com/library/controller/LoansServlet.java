package com.library.controller;

import com.google.gson.Gson;
import com.library.dto.LoanDTO;
import com.library.service.LoanService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="loanServlet", value = "/loans")
public class LoansServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LoanService loanService = new LoanService();
        List<LoanDTO> loans = loanService.getAll().stream().map(LoanService::getDTO).toList();

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String jsonLoans = gson.toJson(loans);

        PrintWriter out = res.getWriter();
        out.println(jsonLoans);
        out.flush();
    }
}
