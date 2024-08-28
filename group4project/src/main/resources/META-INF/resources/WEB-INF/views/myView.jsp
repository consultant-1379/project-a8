<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Home Page</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
       <link rel="stylesheet" href="bootstrap/styles/index.css">

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>
   </head>
   <body>
       <div class="container">
        <div class="row"> <div class="column left">
           <h1>Retrospective Tool - Homepage</h1>
           <br/><br/>
          </div>
          <div class="column right">
              <br/>
              <form action="getAllRetrospectiveItems" method="GET">
                  <input type="submit" class="btn btn-lg btn-info" value="Inspect Retrospective Items">
              </form>

          </div>
        </div> <%-- close row --%>
         <div class="row">
           <div class="column left">
           <h3>Team Overview - Select team to view more details</h3>
           <br/>

           <table>
            <tr>
                <th hidden> Team ID </th>
                <th class="nameCol" style="width: 300px;">
                   Team Name </th>
                <th style="width: 80px;"></th>
                <th style="width: 80px;"></th>
            </tr>
            <c:forEach var= "team"  items="${teams}">
               <tr>
                <td hidden name="id"> ${team.id} </td>
                <td> ${team.name} </td>
                <td>
                    <form action="showTeam" method="GET">
                        <input type="hidden" name="id" value="${team.id}">
                        <input type="submit" class="btn btn-md btn-primary" value="Select">
                    </form>

                </td>
                <td>
                    <form action="removeTeam" method="DELETE">
                        <input type="hidden" name="id" value="${team.id}">
                        <input type="submit" class="btn btn-md btn-danger" value="Delete">
                    </form>

                </td>
             </tr>
            </c:forEach>
            </table>
            </div> <%-- close col left --%>

         <div class="column right">
           <h3> Create new team</h3>
           <br/>
            <div id="form-container">
                <form action="createNewTeam">
                    <input type="text" name="name" placeholder="Team Name"><br/>
                    <br/>
                    <input type="submit" class="btn btn-md btn-success" value="Create Team">
                </form>
            </div>
            <br/><br/>
            </div> <%-- close col right --%>
          </div>     <%-- close row --%>
       </div>
   </body>
   </html>