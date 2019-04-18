<%@page import="entity.User"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>listUsers</title>
		<!-- <meta content="utf-8"> 等价 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">与下面那句，新版本支持的-->
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<div id="header">
					<div id="rightheader">
						<p>
							<fmt:formatDate value="${day }" pattern="YYYY-MM-dd"/>
							<br />
						</p>
					</div>
					<div id="topheader">
						<h1 id="title">
							<a href="login-form.do">退出</a>
						</h1>
					</div>
					<div id="navigation">
					</div>
				</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						新年快乐!
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								ID
							</td>
							<td>
								用户名
							</td>
							<td>
								Email
							</td>
							<td>
								奖励等级
							</td>
						</tr>
						<c:forEach items="${users }" var="user">
							<tr>
								<td>${user.id }</td>
								<td>${user.name }</td>
								<td>${user.email }</td>
								<td>${user.prize_id }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
				ABC@126.com
				</div>
			</div>
		</div>
	</body>
</html>
