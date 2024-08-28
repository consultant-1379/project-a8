<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Item Summary</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
       <link rel="stylesheet" href="bootstrap/styles/index.css">

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>
   </head>
   <body>
       <div class="container">

         <div class="row"><div class="column left">
           <h2>${retroName} : &nbsp; Item Summary</h2>
            <br/>

            <p> Title: &nbsp; ${item.title} </p>
            <p> Category: &nbsp; ${item.category} </p>
            <p> Description: &nbsp; ${item.description} </p><br/><br/>
            <p> Comments: </p><br/>

            <ul>
              <c:forEach  var="comment" items="${comments}" >
                <li>  ${comment} </li>
              </c:forEach>
            <br/>
            </div> <%-- close col left --%>

            <div class="column right">
            <br/>
               <form action="addCommentToItem">
                    <input type="hidden" name="itemId" value="${item.itemId}">
                    <input type="hidden" name="retroId" value="${retroId}">
                    <input type="hidden" name="teamId" value="${teamId}">
                    <input type="submit" class="btn btn-md btn-success" value="Add Comment" />
               </form><br/>


            <form action="showTeam">
                 <input type="hidden" name="id" value="${teamId}">
                 <input type="submit" value="View Team Page" class="btn btn-md btn-primary" />
            </form>
            <br/>

            <form action="showRetrospective">
                <input type="hidden" name="retroId" value="${retroId}">
                <input type="hidden" name="teamId" value="${teamId}">
                <input type="submit" value="Return to Retrospective" class="btn btn-md btn-warning" />
            </form>
            <br/>

           <form action="http://localhost:8080/">
               <input type="submit" value="View Home Page" class="btn btn-md btn-info"/>
           </form>

           </div> <%-- close column right --%>
           </div> <%-- close row --%>

       </div>   <%-- close container --%>
   </body>
   </html>