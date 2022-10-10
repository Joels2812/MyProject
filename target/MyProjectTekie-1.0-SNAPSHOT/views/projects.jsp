<%-- 
    Document   : projects
    Created on : Oct 9, 2022, 2:16:51 PM
    Author     : march
--%>

<%@page import="model.ProjectType"%>
<%@page import="model.Person"%>
<%@page import="model.IPerson"%>
<%@page import="java.sql.SQLException"%>
<%@page import="model.Project"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.IProject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
<div style="padding: 20px">
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" id="btnPopTemporaryProject">        
    Add Temporary Project
    </button>
     <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" id="btnPopPermanentProject">        
    Add Permanent Project
    </button>
</div>    
<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Code</th>
      <th scope="col">Name</th>
      <th scope="col">Initial cost</th>
      <th scope="col">Created</th>
      <th scope="col">Acumulated Income</th>
      <th scope="col">Manager</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <%
      ArrayList<IProject> projects = new ArrayList<>();;
      String error="";
      try {
              projects = Project.getAllProjects();
          } catch (SQLException e) {
            error = e.getMessage();
          }finally{
  %>
  <tbody>
      <%for(IProject project : projects){%>
    <tr>
        <td><a href="ProjectController?action=open&proId=<%=project.getCode()%>&type=<%=project.type().getName()%>"><%= project.getCode()%></a></td>
      <td><%= project.getName()%></td>
      <td><%= project.getInitialCost()%></td>
      <td><%= project.getStartDate().toString()%></td>
      <td><%= project.acumulatedIncome()%></td>
      <td><%= project.getManager().getFullname()%></td>
      <td><a href="ProjectController?action=delete&code=<%=project.getCode()%>" ><span class="badge bg-danger" style="color: white">Delete</span></a></td>
    </tr>
    <%}}%>
  </tbody>
 </table>
  
  <div class="modal fade" id="temporaryProjectModal" tabindex="-1" aria-labelledby="temporaryProjectModal" aria-hidden="true">
  
</div>
<div class="modal fade" id="popupTemporaryProject" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New Temporary Project</h5>
                      </div>
                      <div class="modal-body">
                          <form action="ProjectController" method="post">
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label">Name</label>
                                  <input name="txtName" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Project name" >
                              </div>
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label">Initial cost</label>
                                  <input name="txtInitialCost" type="number" class="form-control" id="exampleFormControlInput1" placeholder="0.00" min="0" value="0" step="0.01">
                              </div>
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label">Country</label>
                                  <input name="txtCountry" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Project country" >
                              </div>
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label"> Dollar Exchange</label>
                                  <input name="txtDolarExchange" type="number" class="form-control" id="exampleFormControlInput1" placeholder="0" min="0" value="0" step="0.01">
                              </div>
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label">Date limit</label>
                                  <input name="txtFinishDate" type="date" class="form-control" id="exampleFormControlInput1" placeholder="Finish date project">
                              </div>
                                  <select name="item" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                                      <%
                                      ArrayList<IPerson> persons = new ArrayList<>();
                                          try {
                                                  persons = Person.getPersons();
                                              } catch (SQLException e) {
                                                error = e.getMessage();
                                              }finally{
                                      %>
                                      <%for(IPerson person : persons){%>
                                      <option value="<%=person.getId()%>" class="dropdown-item"><%=person.getFullname()%></option>
                                      <%}}%>
                                  </select>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                <input type="submit" class="btn btn-primary" value="Save">
                              </div>
                          </form>
                      </div>
                    </div>
                  </div>
</div>
                         
<div class="modal fade" id="popupPermanentProject" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New Permanent Project</h5>
                      </div>
                      <div class="modal-body">
                          <form action="ProjectController" method="post">
                              <input id="txtProyectType" name="txtProyectType" type="hidden" value="<%=ProjectType.PERMANET.getName()%>">
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label">Name</label>
                                  <input name="txtNameP" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Project name" >
                              </div>
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label">Initial cost</label>
                                  <input name="txtInitialCostP" type="number" class="form-control" id="exampleFormControlInput1" placeholder="0.00" min="0" value="0" step="0.01">
                              </div>
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label">Fixed anual cost</label>
                                  <input name="txtFixedAnualCostP" type="number" class="form-control" id="exampleFormControlInput1" placeholder="Project country" placeholder="0.00" min="0" value="0" step="0.01">
                              </div>
                              <div class="mb-3">
                                  <label for="exampleFormControlInput1" class="form-label">Inflation percentage</label>
                                  <input name="txtInflationP" type="number" class="form-control" id="exampleFormControlInput1" placeholder="Project country" placeholder="0.00" min="0" value="0" step="0.01">
                              </div>
                              
                                  <select name="itemP" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">

                                      <%
                                      //ArrayList<IPerson> persons = new ArrayList<>();
                                          try {
                                                  persons = Person.getPersons();
                                              } catch (SQLException e) {
                                                error = e.getMessage();
                                              }finally{
                                      %>
                                      <%for(IPerson person : persons){%>
                                      <option value="<%=person.getId()%>" class="dropdown-item"><%=person.getFullname()%></option>
                                      <%}}%>
                                  </select>
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
  <script type="text/javascript">
      $('#btnPopTemporaryProject').on('click',function(){
           $('#popupTemporaryProject').modal('show');
      });
       $('#btnPopPermanentProject').on('click',function(){
           $('#popupPermanentProject').modal('show');
      });
  </script>
</html>
