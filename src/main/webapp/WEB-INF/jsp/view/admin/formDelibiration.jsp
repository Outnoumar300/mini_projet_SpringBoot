<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/adminHeader.jsp" />


<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">

			<jsp:include page="../fragments/menu.jsp" />

		</div>
	</nav>
	
<div>
		<h3>Delibirations Form</h3>
	</div>
	
	<div>
	
	<f:form action="${pageContext.request.contextPath}/admin/exportDelibiration" method="POST" modelAttribute="delibirationModel">

			


			<div class="row">

				<div class="col">
					<label>Please select a level of study</label>

					<f:select path="idNiveau" multiple="false" size="1"
						class="form-control">
						<f:options items="${niveauxList}" itemValue="idNiveau" itemLabel="titre" />
					</f:select>


				</div>
				</div>
				
				
				<div class="row">
				<div class="col">
					<label>Please select a year</label>

			
               <input type="number" name="annee" min="2018" value="<c:out value="${param.annee}"/>" />
               
					</div>


				</div>
			
			<div style="text-align: right">
				<button type="submit" class="btn btn-primary">Generate file</button>
				<button type="reset" class="btn btn-secondary">Cancel</button>
			</div>

		</f:form>
		</div>
		
	<jsp:include page="../fragments/adminfooter.jsp" />
