<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Confirmation</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">


       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>
   </head>
   <body>
       <div class="container">
           <br/>
           <h2>You have successfully removed the Team: </h2><br/>
           <h3>${teamName}</h3>

            <br/><br/>

           <form action="http://localhost:8080/">
               <input type="submit" value="Return to Homepage" class="btn btn-lg btn-info"/>
           </form>

       </div>
   </body>
   </html>