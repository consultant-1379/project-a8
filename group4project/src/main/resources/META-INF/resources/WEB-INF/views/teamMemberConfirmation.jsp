<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Confirmation</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
       <link rel="stylesheet" href="styles/index.css">

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>
   </head>
   <body>
       <div id="container">
           <h2>You have successfully added a new Team Member: </h2><br/>
           <h3>${newMemberName}</h3>

            <br/><br/>
            <form action="showTeam">
                <input type="hidden" name="id" value="${currentTeamId}">
                <input type="submit" value="Return to Team Page" class="btn btn-lg btn-primary" />
            </form>
            <br/>

           <form action="http://localhost:8080/">
               <input type="submit" value="Return to Home Page" class="btn btn-lg btn-info" />
           </form>

       </div>
   </body>
   </html>