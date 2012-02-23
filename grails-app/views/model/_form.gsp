<%@ page import="mycars.Model" %>



<div class="fieldcontain ${hasErrors(bean: modelInstance, field: 'model', 'error')} required">
	<label for="model">
		<g:message code="model.model.label" default="Model" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="model" required="" value="${modelInstance?.model}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: modelInstance, field: 'make', 'error')} required">
	<label for="make">
		<g:message code="model.make.label" default="Make" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="make" name="make.id" from="${mycars.Make.list()}" optionKey="id" required="" value="${modelInstance?.make?.id}" class="many-to-one"/>
</div>

