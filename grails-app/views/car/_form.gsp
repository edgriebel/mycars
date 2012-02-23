<%@ page import="mycars.Car" %>



<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'color', 'error')} ">
	<label for="color">
		<g:message code="car.color.label" default="Color" />
		
	</label>
	<g:textField name="color" value="${carInstance?.color}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'make', 'error')} required">
	<label for="make">
		<g:message code="car.make.label" default="Make" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="make" name="make.id" from="${mycars.Make.list()}" optionKey="id" required="" value="${carInstance?.make?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'model', 'error')} required">
	<label for="model">
		<g:message code="car.model.label" default="Model" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="model" name="model.id" from="${mycars.Model.list()}" optionKey="id" required="" value="${carInstance?.model?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'vin', 'error')} ">
	<label for="vin">
		<g:message code="car.vin.label" default="Vin" />
		
	</label>
	<g:textField name="vin" value="${carInstance?.vin}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'year', 'error')} required">
	<label for="year">
		<g:message code="car.year.label" default="Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="year" required="" value="${fieldValue(bean: carInstance, field: 'year')}"/>
</div>

