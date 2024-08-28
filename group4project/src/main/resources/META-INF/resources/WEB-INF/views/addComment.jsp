<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>New Comment</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
       <link rel="stylesheet" href="bootstrap/styles/index.css">

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>
   </head>
   <body>
       <div class="container ">
            <div class="row"><div class="column left">
           <h3>Add Comment to Item in:  ${retroName} Retrospective </h3>
           <br/><br/>
            <div id="form-container">
                <form action="submitComment">
                    <input type="hidden" name="retroId" value="${retroId}"><br/>
                    <input type="hidden" name="teamId" value="${teamId}"><br/>
                    <input type="hidden" name="itemId" value="${itemId}"><br/>

                    <textarea rows="5" cols="30" name="comment" >New Comment</textarea><br/>
                    <input type="submit" class="btn btn-lg btn-success" value="Submit">
                </form>
           </div> <%-- close form-container --%>
          </div>  <%-- close column left --%>

          <div class="column right">
              <br/>
              <form action="showItem">
                 <input type="hidden" name="itemId" value="${itemId}">
                 <input type="hidden" name="retroId" value="${retroId}">
                 <input type="hidden" name="teamId" value="${teamId}">
                 <input type="submit" class="btn btn-lg btn-primary" value="Cancel">
            </form>
            <br/>

          </div> <%-- close column right --%>
         </div> <%-- close row --%>
       </div> <%-- close container --%>
   </body>
   </html>