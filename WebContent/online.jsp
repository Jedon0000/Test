<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="32" align="center" class="word_orange ">
			欢迎来到聊天室！
		</td>
	</tr>
	<tr>
		<td height="23" align="center">
			<a href="#" onclick="set('所有人')">所有人</a>
		</td>
	</tr>
	<c:forEach var="entry" items="${userMap}">
		<tr>
			<td height="23" align="center">
				<a href="#" onclick="set('${ entry.key.userName }')">${ entry.key.userName }</a>
				<c:if test="${ exitUser.type == 'admin' and entry.key.type != 'admin' }">			
				<a href="chat?method=kick&id=${entry.key.id}">踢</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table> 