
<%@ page import="mycars.Model" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'model.label', default: 'Model')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-model" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-model" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list model">
			
				<g:if test="${modelInstance?.model}">
				<li class="fieldcontain">
					<span id="model-label" class="property-label"><g:message code="model.model.label" default="Model" /></span>
					
						<span class="property-value" aria-labelledby="model-label"><g:fieldValue bean="${modelInstance}" field="model"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${modelInstance?.make}">
				<li class="fieldcontain">
					<span id="make-label" class="property-label"><g:message code="model.make.label" default="Make" /></span>
					
						<span class="property-value" aria-labelledby="make-label"><g:link controller="make" action="show" id="${modelInstance?.make?.id}">${modelInstance?.make?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${modelInstance?.id}" />
					<g:link class="edit" action="edit" id="${modelInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
