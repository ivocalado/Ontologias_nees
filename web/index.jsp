<%--
  Created by IntelliJ IDEA.
  User: animal505
  Date: 15/05/17
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>

<%@page import="com.abuarquemf.entities.Avatar" %>
<%@page import="com.abuarquemf.entities.Emotion" %>
<%@page import="java.util.List"%>
<%@page import="com.mongodb.DBCollection" %>
<%@page import="com.mongodb.MongoClient" %>
<%@page import="com.mongodb.*" %>
<%@page import="com.mongodb.util.JSON" %>
<%@page import="com.google.gson.*" %>
<%@page import="com.abuarquemf.persistence.DataBaseHandler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <title>NEES Ontology</title>
  <link rel="icon" type="image/jpg" href="pic_dir/icon.jpg">
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

  <!-- jQuery library -->
  <script
          src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

  <!-- Latest compiled JavaScript -->
  <script
          src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

  <meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body>
<div class="container">
  <h1>To build url request:</h1>
  <p>/avatar + /avatar_name + /emotion name</p>
  <p>Ex: /avatar/adriana_marangoni/anger</p>
  <p>would return image below:</p>
  <img src="pic_dir/adriana_marangoni_anger.bmp" />

  <div>
    <h1>Avaiables avatars/emotions:</h1>
    <%
      DataBaseHandler handler = DataBaseHandler.getInstance();
      List<Avatar> avatars = handler.getAvatars();
    %>

    <table class="table">
      <thead>
      <tr>
        <th>Avatar</th>
        <th>Emotion</th>
      </tr>
      </thead>
      <tbody>
      <%
        for(Avatar av : avatars) {
      %>
      <%
        for(int i = 0; i < av.getEmotions().size(); i++) {
      %>
      <tr>
        <%
          if(i == 0) {
        %>
        <td rowspan="<%=av.getEmotions().size()%>"><%=av.getName()%></td>
        <%
          }
        %>
        <td><%=av.getEmotions().get(i)%></td>
      </tr>
      <%
        }
      %>

      <%
        }
      %>
      </tbody>


    </table>
  </div>
</div>
</body>
</html>

