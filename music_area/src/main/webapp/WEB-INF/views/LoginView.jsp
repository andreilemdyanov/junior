<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <style>
        body {
            background: url('http://farm3.staticflickr.com/2832/12303719364_c25cecdc28_b.jpg') fixed;
            background-size: cover;
            padding: 0;
            margin: 0;
        }

        .wrap {
            width: 100%;
            height: 100%;
            min-height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            z-index: 99;
        }

        p.form-title {
            font-family: 'Open Sans', sans-serif;
            font-size: 20px;
            font-weight: 600;
            text-align: center;
            color: #FFFFFF;
            margin-top: 5%;
            text-transform: uppercase;
            letter-spacing: 4px;
        }

        form {
            width: 250px;
            margin: 0 auto;
        }

        form.login input[type="text"], form.login input[type="password"] {
            width: 100%;
            margin: 0;
            padding: 5px 10px;
            background: 0;
            border: 0;
            border-bottom: 1px solid #FFFFFF;
            outline: 0;
            font-style: italic;
            font-size: 12px;
            font-weight: 400;
            letter-spacing: 1px;
            margin-bottom: 5px;
            color: #40ee40;
        }

        form.login input[type="submit"] {
            width: 100%;
            font-size: 14px;
            text-transform: uppercase;
            font-weight: 500;
            margin-top: 16px;
            outline: 0;
            cursor: pointer;
            letter-spacing: 1px;
        }

        form.login input[type="submit"]:hover {
            transition: background-color 0.5s ease;
        }

        input::-webkit-input-placeholder {
            color: #dbe3ff;
            font-size: 80%;
        }

        input::-moz-placeholder {
            color: #dbe3ff;
            font-size: 80%;

        }
    </style>
</head>
<body>
<div class="container">
    <div class="wrap">
        <p class="form-title">
            Sign In</p>
        <form action="${pageContext.servletContext.contextPath}/signin" method="post" class="login">
            <input type="text" name="login" placeholder="Username"/>
            <input type="password" name="password" placeholder="Password"/>
            <input type="submit" value="Sign In" name="create"/>
        </form>
    </div>
</div>

<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>

</body>
</html>
