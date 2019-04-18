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
								密码
							</td>
							<td>
								Email
							</td>
							<td>
								奖励等级
							</td>
							<td>
								操作
							</td>
						</tr>
						<c:forEach items="${users }" var="user">
							<tr>
								<td>${user.id }</td>
								<td>${user.name }</td>
								<td>${user.password }</td>
								<td>${user.email }</td>
								<td>${user.prize_id }</td>
								<td>
								<a href="delete.do?id=${user.id }" onclick="return confirm('是否确认删除${user.name}?');">删除</a>&nbsp;
								</td>
							</tr>
						</c:forEach>
					</table>
					<p>
						<input type="button" class="button" value="抽奖" onclick="location='choujiang.do'"/>
						<input type="button" class="button" value="奖品信息表" onclick="location='handleListAward.do'"/>
						<input type="button" class="button" value="设置奖品" onclick="location='handleChangeAward.do'"/>
					</p>
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
