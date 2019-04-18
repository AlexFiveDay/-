<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>add User</title>
		<link rel="stylesheet" type="text/css"
			href="css/style.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								<fmt:formatDate value="${day }" pattern="YYYY-MM-dd"/>
								<%
									
								%>
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								<a href="list.do">返回列表页</a>
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						设置奖品信息:
					</h1>
					<form action="saveChangeAward.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									    
								</td>
								<td valign="middle" align="left">
									<p>奖品名称</p>
								</td>
								<td valign="middle" align="left">
									<p>奖品数量</p>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									一等奖:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_name1" />
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_amount1" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									二等奖:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_name2" />
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_amount2" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									三等奖:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_name3" />
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_amount3" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									四等奖:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_name4" />
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_amount4" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									五等奖:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_name5" />
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="prize_amount5" />
								</td>
							</tr>
							
						</table>
						<p>
							<input type="submit" class="button" value="提交" />
						</p>
					</form>
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