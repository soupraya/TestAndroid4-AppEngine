package com.velan.testandroid4;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.velan.testandroid4.Dao;

@SuppressWarnings("serial")
public class ServletCreateTodo extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    System.out.println("ServletCreateTodo");
    User user = (User) req.getAttribute("user");
    if (user == null) {
      UserService userService = UserServiceFactory.getUserService();
      user = userService.getCurrentUser();
    }

    String summary = checkNull(req.getParameter("summary"));
    String longDescription = checkNull(req.getParameter("description"));
    String url = checkNull(req.getParameter("url"));
    
    String A = checkNull(req.getParameter("A"));
    String B = checkNull(req.getParameter("B"));
    //String AT = checkNull(req.getParameter("AT"));
    //String BT = checkNull(req.getParameter("BT"));
    
    new CalculateTodoTask(user.getUserId(),summary, longDescription, url,A,B);
    
    resp.sendRedirect("/TodoApplication.jsp");
  }

  private String checkNull(String s) {
    if (s == null) {
      return "";
    }
    return s;
  }
} 
