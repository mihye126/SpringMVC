<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty u}">
{"msg" : "no"}
</c:if>

<c:if test="${!empty u}">
{"msg" : "yes"}
</c:if>