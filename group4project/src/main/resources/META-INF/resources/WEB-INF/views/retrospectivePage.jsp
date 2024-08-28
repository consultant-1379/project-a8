<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Retrospective</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
       <link rel="stylesheet" href="bootstrap/styles/index.css">

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>
   </head>
   <body>
       <div class="container ">
         <div class="row">
            <div class="column left">
           <h1>Retrospective:   ${retrospective.name} </h1>
           <br/><br/>

           <h3>Retrospective Items - Select Item to View All Details and Add Comments</h3>
           </div> <%-- close col left --%>

           <div class="column right">
           <div style="display:inline;">
           <br/>
             <form action="showTeam" style="display:inline;">
                 <input type="hidden" name="id" value="${retrospective.teamid}">
                 <input type="submit" value="View Team Page" style="display:inline;" class="btn btn-md btn-primary"/>
             </form> &nbsp;
             <form action="http://localhost:8080/" style="display:inline;">
                 <input type="submit" value="Return to HomePage"  style="display:inline;" class="btn btn-md btn-info"/>
             </form>
            </div>
           </div> <%-- close col right --%>
         </div> <%-- close row --%>
         <div class="row"><div class="column left">
           <table>
            <tr>
                <th hidden> Item ID </th>
                <th> Category </th>
                <th> Title </th>
                <th> Description </th>
                <th> </th>
                <th> </th>
            </tr>
            <c:forEach var= "item"  items="${items}">
               <tr>
                <td hidden name="itemId"> ${item.itemId} </td>
                <td> ${item.category} </td>
                <td> ${item.title} </td>
                <td> ${item.description} </td>
                <td>
                    <form action="showItem" method="GET">
                        <input type="hidden" name="itemId" value="${item.itemId}">
                        <input type="hidden" name="retroId" value="${retrospective.rid}">
                        <input type="hidden" name="teamId" value="${retrospective.teamid}">
                        <input type="submit" class="btn btn-md btn-primary" value="Select">
                    </form>

                </td>
                <td>
                    <form action="removeItem" method="DELETE">
                        <input type="hidden" name="itemId" value="${item.itemId}">
                        <input type="hidden" name="retroId" value="${retrospective.rid}">
                        <input type="hidden" name="teamId" value="${retrospective.teamid}">
                        <input type="submit" class="btn btn-md btn-danger" value="Delete">
                    </form>
                </td>
             </tr>
            </c:forEach>
            </table>
            <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
            </div> <%-- close col left --%>

            <div class="column middle"></div>

            <div class="column right">
                <div style="padding-left: 60px;">
                    <h4> Create New Item</h4>


                    <form action="createNewItem">
                        <input type="hidden" name="retroId" value="${retrospective.rid}"><br/>
                        <input type="hidden" name="teamId" value="${retrospective.teamid}"><br/>
                        <select id="category" name="category">
                            <option value="Glad">Glad</option>
                            <option value="Sad">Sad</option>
                            <option value="Mad">Mad</option>
                        </select><br/>
                        <input type="text" name="title" placeholder="Title"><br/>
                        <textarea rows="5" cols="30" name="description">Description</textarea>
                        <br/>
                        <input type="submit" class="btn btn-md btn-success" value="Create Item">
                    </form>
                </div> <%-- close form-container --%>
            </div> <%-- close col right --%>

          </div> <%-- close row --%>

       </div> <%-- close container --%>
   </body>
   </html>