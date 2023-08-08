package org.itstep;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.itstep.dao.impl.ToDoItemDao;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteServlet extends HttpServlet {
    private ToDoItemDao dao;

//    static {
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            dao = new ToDoItemDao("jdbc:mariadb://localhost/todolist", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var taskId = Integer.parseInt(request.getParameter("id"));
        dao.delete(taskId);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
