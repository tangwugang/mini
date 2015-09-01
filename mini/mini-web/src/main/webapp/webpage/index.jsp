<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
</head>
<body>
<h2>代码生成</h2>

<form action="loginController.do?save" method="post">
    数据库表名：<input type="text" name="tableName"/></br>
    业务模块包名：<input type="text" name="bussPackage"/></br>
    业务实体包名：<input type="text" name="entityPackage"/></br>
    业务实体描述：<input type="text" name="entityDescription"/></br>
    生成Entity<input type="checkbox" name="template" value="entityTemplate"/>&nbsp;存放路径：<input type="text" name="path" value="${path}" style="width: 250px"/></br>

    生成Controller<input type="checkbox" name="template" value="controllerTemplate"/>&nbsp;存放路径：<input type="text" name="path" value="${path}" style="width: 250px"/></br>
    生成Service<input type="checkbox" name="template" value="serviceTemplate"/>&nbsp;存放路径：<input type="text" name="path" value="${path}" style="width: 250px"/></br>
    生成ServiceImpl<input type="checkbox" name="template" value="serviceImplTemplate"/>&nbsp;存放路径：<input type="text" name="path" value="${path}" style="width: 250px"/></br>
    生成Dao<input type="checkbox" name="template" value="daoTemplate"/>&nbsp;存放路径：<input type="text" name="path" value="${path}" style="width: 250px"/></br>
    生成Mapper<input type="checkbox" name="template" value="mapperTemplate"/>&nbsp;存放路径：<input type="text" name="path" value="${path}" style="width: 250px"/></br>
    </br>
    <input type="submit" value=" 点击生成 "/></br>
</form>
</body>
</html>
