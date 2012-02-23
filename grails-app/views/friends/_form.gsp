<%@ page import="mycars.Friends" %>



<div class="fieldcontain ${hasErrors(bean: friendsInstance, field: 'friendList', 'error')} ">
	<label for="friendList">
		<g:message code="friends.friendList.label" default="Friend List" />
		
	</label>
	<g:select name="friendList" from="${mycars.User.list()}" multiple="multiple" optionKey="id" size="5" value="${friendsInstance?.friendList*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: friendsInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="friends.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${mycars.User.list()}" optionKey="id" required="" value="${friendsInstance?.user?.id}" class="many-to-one"/>
</div>

