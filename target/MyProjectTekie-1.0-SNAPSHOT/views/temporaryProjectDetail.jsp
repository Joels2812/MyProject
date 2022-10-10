<%-- 
    Document   : temporaryProjectDetail
    Created on : Oct 9, 2022, 7:31:34 PM
    Author     : march
--%>

<%@page import="dao.ProjectDAO"%>
<%@page import="model.Person"%>
<%@page import="java.sql.SQLException"%>
<%@page import="model.IPerson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.IProject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <jsp:include page="header.jsp"/>
    <body>
        <%IProject project = (IProject)request.getAttribute("project");%>
<table class="table">  
    <thead>
        <tr>
          <th scope="col">Code</th>
          <th scope="col">Name</th>
          <th scope="col">Initial cost</th>
          <th scope="col">Created</th>
          <th scope="col">Acumulated Income</th>
          <th scope="col">Manager</th>
          <th scope="col">Inflation</th>
          <th scope="col">Balance</th>
          
        </tr>
      </thead>
      <tbody>
        <tr>
          <td><%=project.getCode()%></td>
          <td><%= project.getName()%></td>
          <td><%= project.getInitialCost()%></td>
          <td><%= project.getStartDate().toString()%></td>
          <td><%= project.acumulatedIncome()%></td>
          <td><%= project.getManager().getFullname()%></td>
          <td><%= project.actualCost()%></td>
          <td><%= project.getBalance()%></td>
        </tr>
      </tbody>
</table>
        
        <div class="d-inline" style="width:50%; display:inline-block;"><h2>Personas</h2>
        <table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Surname</th>
      <th scope="col">Lastname</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <%
      ArrayList<IPerson> persons = new ArrayList<>();;
      String error="";
      try {
              persons = Person.getPersons();
          } catch (SQLException e) {
            error = e.getMessage();
          }finally{
  %>
  <tbody>
      <%for(IPerson person : persons){%>
      <%if(person.getId()==project.getManager().getId())continue;%>
    <tr>
      <td><%= person.getId()%></td>
      <td><%= person.getSurname()%></td>
      <td><%= person.getLastname()%></td>

      <td><a href="TemporaryProjectController?action=addMember&id=<%=person.getId()%>" ><span class="badge bg-success" style="color: white">Add</span></a></td>
    </tr>
    <%}}%>
  </tbody>
 </table>
        </div>
        <div class="d-inline"><h2>Equipo de proyecto</h2>
        <table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Surname</th>
      <th scope="col">Lastname</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <%
      ArrayList<IPerson> teamMember = new ArrayList<>();
      try {
              persons = ProjectDAO.getTeamMembersByProject(project);
          } catch (SQLException e) {
            error = e.getMessage();
          }finally{
  %>
  <tbody>
      <%for(IPerson person : project.getTeamMembers()){%>
      <%if(person.getId()==project.getManager().getId())continue;%>
    <tr>
      <td><%= person.getId()%></td>
      <td><%= person.getSurname()%></td>
      <td><%= person.getLastname()%></td>

    </tr>
    <%}}%>
  </tbody>
 </table>
  <%request.getSession().setAttribute("project",project);%>
        </div>
</body>
</html>
