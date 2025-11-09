package com.tictactoe;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TicTacToeServlet extends HttpServlet {
    private char currentPlayer = 'X';
    private char[] board = new char[9];

    public void init() throws ServletException {
        for (int i = 0; i < 9; i++) {
            board[i] = '-';
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Tic Tac Toe</title></head><body>");
        out.println("<h2>Tic Tac Toe Game</h2>");
        out.println("<table border='1' style='text-align:center;'>");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) out.println("<tr>");
            out.println("<td width='50' height='50'>" + board[i] + "</td>");
            if (i % 3 == 2) out.println("</tr>");
        }
        out.println("</table>");
        out.println("<p>Current Player: " + currentPlayer + "</p>");
        out.println("</body></html>");
    }
}
