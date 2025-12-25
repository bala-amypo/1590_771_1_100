package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/health")
public class HealthServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }
}
