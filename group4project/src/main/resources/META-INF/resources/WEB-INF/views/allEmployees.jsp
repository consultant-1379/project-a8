<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
            <meta name="viewport" content="width=device-width, initial-scale=1"></meta>

            <title>Employee Page</title>

       <link rel="stylesheet" href="bootstrap/css/bootstrap.css">

       <script src="jquery/jquery-1.12.4.js"></script>
       <script src="bootstrap/js/bootstrap.js"></script>

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
   </head>
   <body>
       <div style="background-color: beige;">
       <br/>
        <div class="row">
           <div class="column left">
           <h1>Employee Database</h1>
           <br/>
           </div>
           <div class="column right">
             <br/>
            <form action="showTeam">
                <input type="hidden" name="id" value="${currentTeamId}">
                <input type="submit" value="Cancel" class="btn btn-lg btn-danger" />
            </form>
           </div>
        </div>
           <h3> Select a new team member from the employee database: </h3>
           <br/>

           <table>
            <tr>
                <th hidden> Employee ID </th>
                <th style="width:200px; font-size:16px;"> Employee Name </th>
                <th style="width:90px;"></th>

            </tr>
            <c:forEach var= "employee"  items="${allEmployees}">
               <tr>
                <td hidden name="empId"> ${employee.empId} </td>
                <td> ${employee.name} </td>

                <td>
                    <form action="addNewMember" method="POST">
                        <input type="hidden" name="empId" value="${employee.empId}">
                        <input type="hidden" name="teamId" value="${currentTeamId}">
                        <input type="submit" value="Select" class="btn btn-md btn-primary">
                    </form>
                </td>
             </tr>
            </c:forEach>
            </table>
       </div>
   </body>
   </html>