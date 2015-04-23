package com.velan.testandroid4;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.velan.testandroid4.Dao;

public class ServletRemoveTodo extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
  throws IOException {
    String id = req.getParameter("id");
    Dao.INSTANCE.removeCostTotal(Long.parseLong(id));
    resp.sendRedirect("/TodoApplication.jsp");
  }
}
