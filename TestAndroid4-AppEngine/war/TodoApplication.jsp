<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.velan.testandroid4.CostItem" %>
<%@ page import="com.velan.testandroid4.CostTotal" %>
<%@ page import="com.velan.testandroid4.Dao" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
  <head>
    <title>Costs</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
      <meta charset="utf-8"> 
  </head>
  <body>
<%
Dao dao = Dao.INSTANCE;

UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

String url = userService.createLoginURL(request.getRequestURI());
String urlLinktext = "Login";
List<CostItem> costItems = new ArrayList<CostItem>();
List<CostTotal> costTotals = new ArrayList<CostTotal>();

if (user != null){
    url = userService.createLogoutURL(request.getRequestURI());
    urlLinktext = "Logout";
    costItems = dao.getCostItems(user.getUserId());
    costTotals = dao.getCostTotals(user.getUserId());
}
    
%>
  <div style="width: 100%;">
    <div class="line"></div>
    <div class="topLine">
      <div style="float: left;"><img src="images/todo.png" /></div>
      <div style="float: left;" class="headline">Costs</div>
      <div style="float: right;"><a href="<%=url%>"><%=urlLinktext%></a> <%=(user==null? "" : user.getNickname())%></div>
    </div>
  </div>

<div style="clear: both;"/>
You have a total number of <%= costItems.size() %>  costItems.
You have a total number of <%= costTotals.size() %>  costTotals.

<table>
  	<tr>
      <th>Description </th>
      <th>Is finished?</th>
      <th>Number of items</th>
      <th>Unit Cost</th>
      <th>Total</th>
      <th>Done</th>
    </tr>
<% for (CostItem i : costItems) {%>
<tr> 
<td>
<%=i.getDescription()%>
</td>
<td>
<%=i.isFinished()%>
</td>
<td>
<%=i.getNumberOfItem()%>
</td>
<td>
<%=i.getUnitCost()%>
</td>
<td>
<%=i.getTotal()%>
</td>
<td>
<a class="done" href="/done?id=<%=costItem.getId()%>" >Done</a>
</td>
</tr> 
<%}
%>
</table>

<table>
  <tr>
      <th>Cost Total</th>
    </tr>
<% for (CostTotal costTotal : costTotals) {%>
<tr> 
<td>
<%=costTotal.getTotal()%>
</td>
</tr>
<%}
%>
</table>

<hr />

<div class="main">

<div class="headline">New todo</div>

<% if (user != null){ %> 

<form action="/new" method="post" accept-charset="utf-8">
  <table>
    <tr>
      <td><label for="summary">Summary</label></td>
      <td><input type="text" name="summary" id="summary" size="65"/></td>
    </tr>
    <tr>
      <td valign="description"><label for="description">Description</label></td>
      <td><textarea rows="4" cols="50" name="description" id="description"></textarea></td>
    </tr>
  <tr>
    <td valign="top"><label for="url">URL</label></td>
    <td><input type="url" name="url" id="url" size="65" /></td>
  </tr>
    <tr>
    <td valign="top"><label for="A">A</label></td>
    <td><input type="A" name="A" id="A" size="65" /></td>
  </tr>
  <tr>
    <td valign="top"><label for="B">B</label></td>
    <td><input type="B" name="B" id="B" size="65" /></td>
  	<tr>
      <td colspan="2" align="right"><input type="submit" value="Create"/></td>
    </tr>
  </table>
</form>

<% }else{ %>

Please login with your Google account

<% } %>
</div>
</body>
</html> 