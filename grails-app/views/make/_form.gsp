<%@ page import="mycars.Make" %>



<div class="fieldcontain ${hasErrors(bean: makeInstance, field: 'make', 'error')} required">
	<label for="make">
		<g:message code="make.make.label" default="Make" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="make" required="" value="${makeInstance?.make}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: makeInstance, field: 'models', 'error')} ">
	<label for="models">
		<g:message code="make.models.label" default="Models" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${makeInstance?.models?}" var="m">
    <li><g:link controller="model" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="model" action="create" params="['make.id': makeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'model.label', default: 'Model')])}</g:link>
</li>
</ul>

</div>

