<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	<div class="container">
		<div class="h1">${type}配置管理</div>
		<div class="container">
			<div class="row">
				<form action="" method="post" class="form-inline" role="form">
					<input type="hidden" name="type" value="${type}"> <input
						type="text" name="name" placeholder="配置项名称" class="form-control">
					<input type="submit" value="新建" class="btn btn-primary">
				</form>
			</div>
		</div>
		<table class="table table-striped">
			<tr>
				<th>配置项</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${dicts}" var="dict">
				<tr>
					<td>${dict.name}</td>
					<td><a class="dictDelete btn btn-primary" target="_self"
						data-type="${dict.dictType.toInt()}" data-id="${dict.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
