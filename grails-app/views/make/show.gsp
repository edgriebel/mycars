
<%@ page import="mycars.Make" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'make.label', default: 'Make')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-make" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-make" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list make">
			
				<g:if test="${makeInstance?.make}">
				<li class="fieldcontain">
					<span id="make-label" class="property-label"><g:message code="make.make.label" default="Make" /></span>
					
						<span class="property-value" aria-labelledby="make-label"><g:fieldValue bean="${makeInstance}" field="make"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${makeInstance?.models}">
				<li class="fieldcontain">
					<span id="models-label" class="property-label"><g:message code="make.models.label" default="Models" /></span>
					
						<g:each in="${makeInstance.models}" var="m">
						<span class="property-value" aria-labelledby="models-label"><g:link controller="model" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${makeInstance?.id}" />
					<g:link class="edit" action="edit" id="${makeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
