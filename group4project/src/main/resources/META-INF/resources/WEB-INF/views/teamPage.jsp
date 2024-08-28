<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Team Page</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
       <link rel="stylesheet" href="bootstrap/styles/index.css">

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>

   </head>
   <body >
       <div class="container" >
         <div class="row">
         <div class="column left">
           <h1>Welcome to  ${team.name}</h1>
          </div> <%-- close column left --%>

          <div class="column right"">
             <br/>
             <form action="http://localhost:8080/">
                 <input type="submit" value="Return to HomePage" class="btn btn-lg btn-info "/>
             </form>
             <br/>
          </div></div>  <%-- close col right and row --%>

          <div class="row">
            <div class="column left">
                <br/>
                <h4> Add New Retrospective:</h4>
                <br/>
                <div id="form-container">
                    <form action="createNewRetrospective">
                        <input type="hidden" name="teamId" value="${team.id}">
                        <input type="text"  name="name" placeholder="Retrospective Name"><br/>
                        <br/>
                        <input type="submit" class="btn btn-md btn-primary" value="Create Retrospective">
                    </form>
                </div> <%-- close form container --%>
                <br/>
            </div> <%-- close col left --%>
            <div class="column right">

               <br/><br/>
               <h4>Add New Team Member :</h4>
               <form action="viewEmployeeList" method="GET">
                <input type="hidden" name="id" value="${team.id}">
                <input type="submit" class="btn btn-md btn-success" value="Add Employee from Database">
               </form>

            </div> <%-- close col right --%>

            <div class="column left">

               <h3>Team Retrospectives:</h3>
               <br/>

               <table>
                   <tr>
                       <th hidden> Retrospective ID </th>
                       <th style="width: 300px;"> Retrospective Name </th>
                       <th style="width: 90px;"></th>
                       <th style="width: 90px;"></th>
                   </tr>
                   <c:forEach var= "retrospective"  items="${retrospectives}">
                   <tr>
                       <td hidden name="id"> ${retrospective.rid} </td>
                       <td> ${retrospective.name} </td>
                       <td>
                           <form action="showRetrospective" method="GET">
                               <input type="hidden" name="teamId" value="${team.id}">
                               <input type="hidden" name="retroId" value="${retrospective.rid}">
                               <input type="submit" class="btn btn-md btn-primary" value="View Items">
                           </form>
                       </td>
                       <td>
                           <form action="removeRetrospective" method="DELETE">
                               <input type="hidden" name="id" value="${team.id}">
                               <input type="hidden" name="retroId" value="${retrospective.rid}">
                               <input type="submit" class="btn btn-md btn-danger" value="Delete">
                           </form>
                       </td>
                   </tr>
                   </c:forEach>
               </table>

           </div> <%-- close column left --%>

            <div class="column right">

                <h3>Team Members:</h3>
                <br/>
                <table>
                    <c:forEach var= "tm"  items="${teamMembers}">
                      <tr>
                        <td hidden name="id"> ${tm.empId} </td>
                        <td  style="width: 200px;"> ${tm.name} </td>
                        <td  style="width: 90px;">
                            <form action="removeTeamMember" method="DELETE">
                                <input type="hidden" name="id" value="${team.id}">
                                <input type="hidden" name="empId" value="${tm.empId}">
                                <input type="submit" class="btn btn-md btn-danger" value="Remove">
                            </form>
                        </td>
                      </tr>
                    </c:forEach>
                </table>
               <br/>
            </div> <%-- close col right --%>

          </div>  <%-- close row --%>
          <br/><br/><br/><br/>
       </div>


   </body>
</html>