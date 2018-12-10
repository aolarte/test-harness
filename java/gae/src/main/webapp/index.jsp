<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.andresolarte.harness.gae.servlets.InfoServlet" %>
<html>
<head>
  <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
  <title>Hello App Engine Standard Java 8</title>
</head>
<body>
<h1>Hello App Engine -- Java 8!</h1>

<p>This is <%= InfoServlet.getInfo() %>.</p>
  <table>
    <tr>
      <td colspan="2" style="font-weight:bold;">Available Servlets:</td>
    </tr>
    <tr>
      <td><a href='/info'>Info</a></td>
    </tr>
  </table>

</body>
</html>
