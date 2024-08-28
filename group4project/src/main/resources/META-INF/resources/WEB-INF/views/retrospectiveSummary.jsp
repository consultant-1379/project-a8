<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Retrospective Summary</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
       <style>
            .row {
                width: 100%;
            }

            .column {
              float: left;
              padding: 5px;
            }

            .left {
                width: 65%;
                margin-left: 20px;
            }

            .right {
                width: 30%;
            }

       </style>

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>
   </head>
   <body>
       <div class="container">
       <div class="row"><div class="column left">
          <br/>
           <h2>You have Successfully Created a New Retrospective!</h2>
           <h3> ${retrospective.name} </h3>
            <br/>
        </div>
        <div class="column right">
           <br/> <br/>
           <form action="http://localhost:8080/">
               <input type="submit" value="View Home Page" class="btn btn-lg btn-info"/>
           </form>
           <br/>

            <form action="showRetrospective">
                <input type="hidden" name="retroId" value="${retrospective.rid}">
                <input type="hidden" name="teamId" value="${currentTeamId}">
                <input type="submit" value="View Retrospective" class="btn btn-lg btn-success" />
            </form>
            <br/>

            <form action="showTeam">
                <input type="hidden" name="id" value="${currentTeamId}">
                <input type="submit" value="View Team Page" class="btn btn-lg btn-primary" />
            </form>


        </div></div>
       </div>
   </body>
   </html>