package org.itstep;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.itstep.dao.impl.ToDoItemDao;
import org.itstep.model.ToDoItem;

import java.io.IOException;
import java.sql.SQLException;

public class HomeServlet extends HttpServlet {

    private ToDoItemDao dao;

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            dao = new ToDoItemDao("jdbc:mariadb://localhost/todolist", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("todos", dao.findAll());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        if (description != null && !description.isBlank()) {
            dao.save(new ToDoItem(description));
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
