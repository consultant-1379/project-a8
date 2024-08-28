<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Items Page</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
       <link rel="stylesheet" href="bootstrap/styles/index.css">

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>

   </head>
   <body style="background-color: beige;">
       <div class="container">
       <br/>
        <div class="row">
           <div class="column left">
           <h1>Retrospective Item Database</h1>
           <br/>

           </div>
           <div class="column right">
             <br/>
           <form action="http://localhost:8080/">
               <input type="submit" value="Return to HomePage" class="btn btn-lg btn-info "/>
           </form>
           <br/>
             <h3> Filter Database </h3>
            <form action="filterItems">
                <input type="text" name="teamName" placeholder="by team name"><br/>
                <select id="category" name="category">
                    <option value="Glad">Glad</option>
                    <option value="Sad">Sad</option>
                    <option value="Mad">Mad</option>
                    <option value="All Categories">All Categories</option>
                </select><br/>
                <input type="submit" value="Filter Items" class="btn btn-md btn-success" />
            </form>
           </div> <%-- close col right --%>

        </div> <%-- close row --%>
           <h3> All Retrospective Items: </h3>
           <br/>
           <table>
            <tr>
                <th hidden> Item ID </th>
                <th> Team Name </th>
                <th> Retrospective Name </th>
                <th> Item Category</th>
                <th> Item Title</th>
                <th> Item Description</th>
                <th></th>
            </tr>
            <c:forEach var= "item"  items="${allItems}">
               <tr>
                <td hidden name="itemId"> ${item.itemId} </td>
                <td> ${item.teamName} </td>
                <td> ${item.retroName} </td>
                <td> ${item.category} </td>
                <td> ${item.title} </td>
                <td> ${item.description} </td>
                <td>
                    <form action="showItem" method="GET">
                        <input type="hidden" name="itemId" value="${item.itemId}">
                        <input type="hidden" name="retroId" value="${item.retroId}">
                        <input type="hidden" name="teamId" value="${item.teamId}">
                        <input type="submit" class="btn btn-md btn-primary" value="View Item">
                    </form>
                </td>
             </tr>
            </c:forEach>
            </table>
       </div>
   </body>
   </html>