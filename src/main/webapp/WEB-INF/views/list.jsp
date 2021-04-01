<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>员工列表</title>
    <base href="http://localhost:8080/ssm_crud/"/>
    <%--  引入jquery  --%>
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <%--  引入样式  --%>
    <link href="static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <%--    标题行    --%>
    <div class="row">
        <div class="col-md-12"><h1>SSM-CRUD</h1></div>
    </div>
    <%--    按钮        --%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button type="button" class="btn btn-primary">新增</button>
            <button type="button" class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--    表格        --%>
    <div class="row"></div>
    <table class="table table-hover">
        <tr>
            <th>#</th>
            <th>empName</th>
            <th>gender</th>
            <th>email</th>
            <th>depName</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${pageInfo.list}" var="emp">
            <tr>
                <td>${emp.empId}</td>
                <td>${emp.empName}</td>
                <td>${emp.gender == "M"?"男":"女"}</td>
                <td>${emp.email}</td>
                <td>${emp.department.deptName}</td>
                <td>
                    <button type="button" class="btn btn-primary">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        编辑
                    </button>
                    <button type="button" class="btn btn-danger">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                        删除
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <%--    页码        --%>
    <div class="row">
        <%--   分页文字信息     --%>
        <div class="col-md-6">
            当前${pageInfo.pageNum}页，总共${pageInfo.pages}页，总共${pageInfo.total}条数据
        </div>
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="emps?pn=1">首页</a></li>
                    <%--            上一页            --%>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="emps?pn=${pageInfo.pageNum - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                        <c:if test="${page_num == pageInfo.pageNum}">
                            <li class="active"><span>${page_num}</span></li>
                        </c:if>
                        <c:if test="${page_num != pageInfo.pageNum}">
                            <li><a href="emps?pn=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
                    <%--            下一页            --%>
                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="emps?pn=${pageInfo.pageNum + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <li><a href="emps?pn=${pageInfo.pages}">末页</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>

</body>
</html>
