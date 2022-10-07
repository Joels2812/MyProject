<%-- 
    Document   : listPersons
    Created on : Oct 7, 2022, 10:50:58 AM
    Author     : march
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Person"%>
<%@page import="model.IPerson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>

<body>
    <div style="padding: 20px">
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" id="btnPopupPerson">
        Add Person
    </button>
</div>
  <table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Surname</th>
      <th scope="col">Lastname</th>
      <th scope="col">Email</th>
      <th scope="col">Phone</th>
      <th scope="col">Borndate</th>
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
    <tr>
      <td><%= person.getId()%></td>
      <td><%= person.getSurname()%></td>
      <td><%= person.getLastname()%></td>
      <td><%= person.getEmail()%></td>
      <td><%= person.getPhoneNumber()%></td>
      <td><%= person.getBorndate().toString()%></td>
      <td><a href="PersonController?action=delete&id=<%=person.getId()%>" ><span class="badge bg-danger" style="color: white">Delete</span></a></td>
    </tr>
    <%}}%>
  </tbody>
 </table>
  

  <!-- Modal -->
<div class="modal fade" id="popupPerson" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New Person</h5>
      </div>
      <div class="modal-body">
          <form action="PersonController" method="post">
              <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">Identification Number</label>
                  <input name="txtId" type="text" class="form-control" id="exampleFormControlInput1" placeholder="88888888" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="9">
              </div>
              <div class="input-group">
                <span class="input-group-text">Sur and last name</span>
                <input type="text" aria-label="First name" class="form-control" name="txtSurname">
                <input type="text" aria-label="Last name" class="form-control" name="txtLastname">
              </div>
              <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">Email address</label>
                  <input name="txtEmail" type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
              </div>
              <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">Phone Number</label>
                  <input name="txtNumber" type="text" class="form-control" id="exampleFormControlInput1" placeholder="88888888" onkeypress="return event.charCode >= 48 && event.charCode <= 57" maxlength="8">
              </div>
              <div class="mb-3">
                  <label for="exampleFormControlInput1" class="form-label">Born date</label>
                  <input name="txtBornDate" type="date" class="form-control" id="exampleFormControlInput1" placeholder="88888888" onkeypress="return event.charCode >= 48 && event.charCode <= 57">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                <input type="submit" class="btn btn-primary" value="Save">
              </div>
          </form>
      </div>
    </div>
  </div>
</div>
  
  
</body>
</html>
