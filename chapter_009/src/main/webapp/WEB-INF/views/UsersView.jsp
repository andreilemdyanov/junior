<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("select[name='country']").on("change", function () {
                $.ajax('./json', {
                    method: 'get',
                    data: {country: $("select[name='country']").val()},
                    contentType: 'application/json',
                    complete: function (result) {
                        var cities = JSON.parse(result.responseText);
                        $("select[name='city']").empty();
                        for (var i = 0; i != cities.length; i++) {
                            $("select[name='city']").append($("<option value='" + cities[i] + "'>" + cities[i] + "</option>"))
                        }
                    }
                });
            });
        });
    </script>
    <script>
        function validate() {
            var result = false;
            console.log($("select[name='country']").val());
            if ($("select[name='country']").val() != null && $("select[name='city']").val() != null && $("select[name='role']").val() != null) {
                result = true;
            }
            if (!result) {
                alert("Please choose role, country and city ");
            }
            return result;
        }
    </script>
    <style>
        body {
            background: url('http://farm3.staticflickr.com/2832/12303719364_c25cecdc28_b.jpg') fixed;
            background-size: cover;
            padding: 0;
            margin: 0;
        }

        div#tableContainer {
            display: table;
            border-spacing: 20px;
        }

        div.tableRow {
            display: table-row;
        }

        .wrap {
            width: 100%;
            height: 100%;
            min-height: 100%;
            position: relative;
            top: 0;
            left: 0;
        }

        p.form-title {
            font-family: 'Open Sans', sans-serif;
            font-size: 20px;
            font-weight: 600;
            text-align: center;
            color: #FFFFFF;
            margin-top: 2%;
            text-transform: uppercase;
            letter-spacing: 4px;
        }

        form {
            width: 250px;
            margin: 0 auto;
        }

        form.create input[type="text"], form.create input[type="password"] {
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

        form.create input[type="submit"] {
            width: 100%;
            font-size: 14px;
            text-transform: uppercase;
            font-weight: 500;
            margin-top: 16px;
            outline: 0;
            cursor: pointer;
            letter-spacing: 1px;
        }

        form.create input[type="submit"]:hover {
            transition: background-color 0.5s ease;
        }

        form.create select {
            background: transparent;
        }

        option {
            background: transparent;
        }

        form.create {
            font-family: 'Open Sans', sans-serif;
            font-style: italic;
            color: #40ee40
        }

        select[name="country"], select[name="role"], select[name="city"] {
            width: 150px;
            font-family: 'Open Sans', sans-serif;
            font-style: italic;
            color: #40ee40;
            padding: 5px 10px;
            margin-bottom: 5px;
        }

        input::-webkit-input-placeholder {
            color: #dbe3ff;
            font-size: 80%;
        }

        input::-moz-placeholder {
            color: #dbe3ff;
            font-size: 80%;
        }

        table {
            color: white;
            border-spacing: 0px;
        }
    </style>
</head>
<body>
<div id="tableContainer">
    <c:if test="${error != ''}">
        <div style="background-color: red">
            <c:out value="${error}"/>
        </div>
    </c:if>

    <div class="tableRow">
        <c:if test="${sessionScope.role == 'ADMIN'}">
            <div class="wrap">
                <p class="form-title">
                    Create User</p>
                <form action="${pageContext.servletContext.contextPath}/" method="post" onsubmit="return validate()"
                      class="create">
                    <input type="text" name="name" placeholder="Name"><br>
                    <input type="text" name="login" placeholder="Login"><br>
                    <input type="password" name="password" placeholder="Password"><br>
                    <input type="text" name="email" placeholder="Email"><br>
                    <select name="role">
                        <option value="" disabled selected hidden>Choose role</option>
                        <option value="1">ADMIN</option>
                        <option value="2">DEFAULT</option>
                    </select><br>
                    <select name="country">
                        <option value="" disabled selected hidden>Choose country</option>
                        <option value="Russia">Russia</option>
                        <option value="USA">USA</option>
                    </select><br>
                    <select name="city">
                        <option value="" disabled selected hidden>Choose city</option>
                    </select><br>
                    <button type='submit' name='create'>Create User</button>
                </form>
            </div>
        </c:if>
    </div>
    <div class="tableRow">
        <p class="form-title">
            Users Table</p>
        <table style="border: 1px solid white" cellpadding="1" cellspacing="1" border="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>login</th>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <th>password</th>
                </c:if>
                <th>email</th>
                <th>timeCreate</th>
                <th>role</th>
                <th>country</th>
                <th>city</th>
                <th colspan="3">actions</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>
                        <c:out value="${user.id}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.name}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.login}"></c:out>
                    </td>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <td>
                            <c:out value="${user.password}"></c:out>
                        </td>
                    </c:if>
                    <td>
                        <c:out value="${user.email}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.createDate.getTime()}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.role}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.country}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.city}"></c:out>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${sessionScope.login == user.login || sessionScope.role == 'ADMIN'}">
                            <form action="${pageContext.servletContext.contextPath}/editJSP">
                                <button type="submit" value="${user.id}" name="id">Update</button>
                            </form>
                        </c:if>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${sessionScope.login == user.login || sessionScope.role == 'ADMIN'}">
                            <form action="${pageContext.servletContext.contextPath}/deleteJSP" method="post">
                                <button type="submit" value="${user.id}" name="id">Delete</button>
                            </form>
                        </c:if>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${sessionScope.role == 'ADMIN'}">
                            <form action="${pageContext.servletContext.contextPath}/updateRole" method="post">
                                <select name="select">
                                    <option value="1">ADMIN</option>
                                    <option value="2">DEFAULT</option>
                                </select>
                                <button type="submit" value="${user.id}" name="id">Change role</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="tableRow">
        <form action="${pageContext.servletContext.contextPath}/signout" method="post">
            <input type="submit" value="Sign Out">
        </form>
    </div>
</div>

</body>
</html>
