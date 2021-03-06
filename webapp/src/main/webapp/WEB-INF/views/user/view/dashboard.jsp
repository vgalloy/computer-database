<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<!DOCTYPE html>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"/>
	<body>
		<mylib:commonHead colorMenu="user-color"/>
	    <section id="main">
	        <div class="container">
	            <h1 id="homeTitle"> ${page.getUserList().size()} <spring:message code="user.found"/> </h1>
	            <div id="actions" class="form-horizontal">
	                <div class="pull-right">
	                    <a class="btn btn-success" id="addComputer" href="#addUser"><spring:message code="add.user"/></a> 
	                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="button.delete"/></a>
	                </div>
	            </div>
	        </div>
	        <form:form id="deleteForm" action="/webapp/user/crud/delete" method="POST">
	            <input type="hidden" name="selection" value="">
	        </form:form>
	        <div class="container" style="margin-top: 10px;">
	            <table class="table table-striped ">
	                <thead>
	                    <tr>
							<th class="editMode" style="width: 60px; height: 22px;">
	                            <input type="checkbox" id="selectall" /> 
	                            <span style="vertical-align: top;">
	                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
	                                        <i class="fa fa-trash-o fa-lg"></i>
	                                    </a>
	                            </span>
	                        </th>
	                        <th><spring:message code="username"/></th>
	                        <th><spring:message code="reset.password"/></th>
	                        <th>SUPER_ADMIN</th>
	                        <th>ADMIN</th>
							<th>USER</th>
	                    </tr>
	                </thead>
	                <tbody id="list">
	                	<c:forEach var="i" begin="0" end="${page.getUserList().size() - 1}">
		                    <tr>
								<td class="editMode"><input type="checkbox" name="cb" class="cb" value="${page.getUserList().get(i).getUserName()}"></td>
		                        <td>${page.getUserList().get(i).getUserName()}</td>
								<c:set var="nameForInput" value="${page.getUserList().get(i).getUserName()}_1548664" />
								<td class="hidden"><input id="${nameForInput}" value="${page.getUserList().get(i).getUserName()}"/></td>
		                        <td><a class="btn btn-danger" href="#resetPassword" onclick="save(${nameForInput})"><spring:message code="reset.password"/></a></td>
								<td>
									<c:set var="name" value="${\"ruleForm\"}${page.getUserList().get(i).getUserName()}0" />
									<form:form modelAttribute="ruleDto" id="${name}" action="/webapp/user/crud/edit" method="POST">
										<form:input path="username" class="hidden" value="${page.getUserList().get(i).getUserName()}"/>
										<form:input path="role" class="hidden" value="ROLE_SUPER_ADMIN"/>
										<c:if test="${page.getUserList().get(i).getRoles().contains(\"ROLE_SUPER_ADMIN\")==true}">
											<form:checkbox path="authorized" checked="checked" onchange="${name}.submit()"/>
										</c:if>
										<c:if test="${page.getUserList().get(i).getRoles().contains(\"ROLE_SUPER_ADMIN\")==false}">
											<form:checkbox path="authorized" onchange="${name}.submit()"/>
										</c:if>											
									</form:form>
								</td>
								<td>
									<c:set var="name" value="${\"ruleForm\"}${page.getUserList().get(i).getUserName()}1" />
									<form:form modelAttribute="ruleDto" id="${name}" action="/webapp/user/crud/edit" method="POST">
										<form:input path="username" class="hidden" value="${page.getUserList().get(i).getUserName()}"/>
										<form:input path="role" class="hidden" value="ROLE_ADMIN"/>
										<c:if test="${page.getUserList().get(i).getRoles().contains(\"ROLE_ADMIN\")==true}">
											<form:checkbox path="authorized" checked="checked" onchange="${name}.submit()"/>
										</c:if>
										<c:if test="${page.getUserList().get(i).getRoles().contains(\"ROLE_ADMIN\")==false}">
											<form:checkbox path="authorized" onchange="${name}.submit()"/>
										</c:if>											
									</form:form>
								</td>
								<td>
									<c:set var="name" value="${\"ruleForm\"}${page.getUserList().get(i).getUserName()}2" />
									<form:form modelAttribute="ruleDto" id="${name}" action="/webapp/user/crud/edit" method="POST">
										<form:input path="username" class="hidden" value="${page.getUserList().get(i).getUserName()}"/>
										<form:input path="role" class="hidden" value="ROLE_USER"/>
										<c:if test="${page.getUserList().get(i).getRoles().contains(\"ROLE_USER\")==true}">
											<form:checkbox path="authorized" checked="checked" onchange="${name}.submit()"/>
										</c:if>
										<c:if test="${page.getUserList().get(i).getRoles().contains(\"ROLE_USER\")==false}">
											<form:checkbox path="authorized" onchange="${name}.submit()"/>
										</c:if>											
									</form:form>
								</td>
		                    </tr>
						</c:forEach>               
	                </tbody>
	            </table>
	            <c:if test="${page.getErrorMessage() != null}">
	             	<div id="loginError" class="alert alert-danger" role="alert">
						${page.getErrorMessage()}
					</div>  
				</c:if>
	        </div> 
	    </section>
	    <!-- Popup -->
	   	<jsp:include page="/WEB-INF/views/user/popup/addUser.jsp"/>
	   	<jsp:include page="/WEB-INF/views/user/popup/resetPassword.jsp"/>
	   	<!-- Javascript -->
		<jsp:include page="/WEB-INF/views/import/common_js_import.jsp"/>
		<script src="${pageContext.request.contextPath}/js/user.js"></script>
		<script type="text/javascript">
			var button_view = "<spring:message code='button.cancel'/>";
			var button_edit = "<spring:message code='button.delete'/>";
			var alert_message = "<spring:message code='delete.user.message'/>";
		</script>
		<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
	</body>
</html>
