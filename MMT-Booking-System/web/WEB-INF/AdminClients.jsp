<%-- 
    Document   : AdminClients
    Created on : 28-Mar-2023, 12:03:17 PM
    Author     : Taburada
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Clients Page</title>

        <!--Stylesheet-->
        <link rel="stylesheet" type="text/css" href="Assets/Styles/AdminClients.css">
        <link rel="stylesheet" href="Assets/Styles/NavigationBar.css">
        <script src="Assets/Scripts/AdminClients.js"></script>
    </head>
    <body>
        <header>

            <!--Menu button stuff-->
            <input type="checkbox" id="active">
            <label for="active" class="menu-btn"><span></span></label>
            <label for="active" class="close"></label>
            <div class="wrapper">
                <ul>
                    <li><a href="admin">MAIN</a></li>
                    <li><a href="availability">AVAILABILITY</a></li>
                    <li><a href="clients">CLIENTS</a></li>
                    <li><a href="login">LOGOUT</a></li>
                </ul>
            </div>
        </header>
        <main>
            <div class="clientList">
                <div class="clientHead">
                    <h2 class="clients-head">Clients</h2>
                </div>
                <div class="list">
                    <c:forEach items="${accounts}" var="account">
                        
                        <!--Excludes any user that is an admin -->
                        <c:if test="${account.role.roleName eq 'client'}">
                            <a id="client-info" class="client" 
                                    href="<c:url value='/clients?action=display'>
                                             <c:param name='username' value='${account.username}'></c:param>
                                         </c:url>">${account.fullName}        
                            </a>
                        </c:if>
                    </c:forEach>   
                </div>
            </div>


            <div id="client-container" class="client-container">
                <h1>Client Information</h1>
                
                <c:if test="${display eq false}">
                    <h2>No client selected</h2>
                </c:if>
                    
                <div id="delete-message">${deletedMessage}</div>
             
                <c:if test="${display eq true}">
                    <div id="message">${message}</div>
                    <div id="error-message">${errorMessage}</div>
                    <form action="clients" method="post">
                        <div class="buttons">
                            <input type="hidden" name="username" value="${account.username}">
                            <input type="submit" class="saveButt" value="Save changes">
                            <input type="hidden" name="action" value="edit">
                            
                            <input type="submit" class="deleteButt" value="Remove client">
                            <input type="hidden" name="action" value="delete">
                        </div>

                        <input type="text" name="name" id="client_name" value="${account.fullName}">

                        <table class="info">
                            <tr><p id="subheading">Edit client information</p></tr>
                            <tr>
                                <td><p>Phone Number</p></td>
                                <td><input type="text" id="phone" name="phone" value="${account.phone}"></td>                  
                            </tr>
                            <tr>
                                <td><p>Email</p></td>
                                <td><input type="text" id="email" name="email" value="${account.email}"></td>
                            </tr>
                            <tr>
                                <td><p>Address</p></td>
                                <td><input type="text" id="address" name="address" value="${account.address}"></td>        
                            </tr>
                            <tr>
                                <td><p>Birthdate</p></td>
                                <td><input type="date" id="birthday" name="birthday" 
                                           value="<fmt:formatDate pattern='MM/dd/yyyy' value='${account.birthdate}' />">
                                </td>
                            </tr>
                        </table>
                        <h3 id="emergency">EMERGENCY CONTACT</h3>
                        <table class="emergency-contact">
                            <tr>
                                <td><p>Full Name</p></td>
                                <td><input readonly type="text" id="ec_name" value="${account.ecContact.ecName}"></td>
                            </tr>
                            <tr>
                                <td><p>Relationship</p></td>
                                <td><input readonly type="text" id="ec-relation" name="ec_relation" value="${account.ecContact.ecRelation}"></td>
                            </tr>
                            <tr>
                                <td><p>Email</p></td>
                                <td><input readonly type="text" id="ec-email" name="ec_email" value="${account.ecContact.ecEmail}"></td>
                            </tr>
                            <tr>
                                <td><p>Phone Number</p></td>
                                <td><input readonly type="text" id="ec-phone" name="ec_phone" value="${account.ecContact.ecPhone}"></td>
                            </tr>
                        </table>
                        <div class="medical-info">
                            <h3 id="medical-info">MEDICAL INFORMATION</h3>
                            <textarea id="med-info" name="med-info" class="textBig">${account.medicalInfo}</textarea>
                        </div>
                    </form>
                    <div class="appointments-container"
                        <h3 id="appointments">APPOINTMENTS HISTORY</h3>
                        <c:choose>
                            <c:when test="${empty appointments}">
                                <h2 id="empty-appointments">This client does not have any appointments.</h2>
                            </c:when>
                            <c:otherwise>
                                <table class="appointment-history">
                                    <tr>
                                        <th>Appointment Date</th>
                                        <th>Service</th>
                                        <th>Duration</th>
                                        <th>Status</th>
                                    </tr>
                                    <c:forEach items="${appointments}" var="appointment">
                                        <tr class="appointment-info">
                                            <td>
                                                <p><fmt:formatDate pattern="MMM. dd, yyyy h:mm a" timeZone="America/Denver" value="${appointment.appointmentDate}"/></p>
                                            </td>                                    
                                            <td>
                                                <p>${appointment.service.serviceType}</p>
                                            </td>                                  
                                            <td>
                                                <p>${appointment.duration} mins</p>
                                            </td> 
                                            <td>
                                                <p>
                                                    <c:choose>
                                                        <c:when test="${appointment.status == false}">Pending</c:when>
                                                        <c:otherwise>Confirmed</c:otherwise>
                                                    </c:choose> 
                                                </p>   
                                            </td>
                                        </tr>
                                    </c:forEach>  
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </div>
        </main>
    </body>
</html>