<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
    <title>定时网页抓取任务创建</title>
    <%@include file="../../commons/header.jsp" %>
</head>
<%@include file="../../commons/head.jsp" %>
<body>
<div class="container">
    <div class="row">
        <form action="${pageContext.request.contextPath}/panel/commons/createQuartz" method="post"
              id="quartzForm">
            <div class="form-group">
                <label for="spiderInfoId">爬虫模板ID</label>
                <input type="text" class="" id="spiderInfoId" name="spiderInfoId" placeholder="输入已注册的爬虫模板ID"
                       value="${spiderInfoId}">
            </div>
            <div class="form-group">
                <label for="minutesInterval">循环间隔分钟数</label>
                <input type="number" class="" id="minutesInterval" name="minutesInterval" placeholder="输入循环间隔分钟数">
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-secondary">提交</button>
            </div>
        </form>
    </div>
</div>
<%@include file="../../commons/allScript.jsp" %>
<script>
    $().ready(function () {
        $("#quartzForm").validate({
            rules: {
                spiderInfoId: {
                    required: true,
                    remote: "${pageContext.request.contextPath}/commons/spider/checkQuartzJob"
                },
                minutesInterval: {
                    required: true
                }
            },
            highlight: function (element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement: function (error, element) {
                element.parent('div').append(error);
            }
        });
    });
</script>
</body>

</html>