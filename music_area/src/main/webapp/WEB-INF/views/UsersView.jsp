<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $.ajax('./jsonRole', {
                method: 'get',
                contentType: 'application/json',
                complete: function (result) {
                    var roles = JSON.parse(result.responseText);
                    $("select[name='role']").empty();
                    $("select[name='role']").append($("<option value='" + "' disabled selected hidden>Choose role</option>"));
                    for (var i = 0; i != roles.length; i++) {
                        $("select[name='role']").append($("<option value='" + roles[i] + "'>" + roles[i] + "</option>"))
                    }
                }
            });
        });
    </script>
    <script>
        function validate() {
            var result = false;
            if ($("input[name='login']").val() != null && $("input[name='password']").val() != null && $("select[name='role']").val() != null) {
                result = true;
            }
            if (!result) {
                alert("Please choose login, password and role");
            }
            return result;
        }

        $(function () {
            var inp = $('input[name="interest"]');
            inp.on('click', function () {
                $('button[class="but"]')
                    .attr('disabled', $('input[name="interest"]:checked').length ? false : true);
            });
        });
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
        <c:if test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'MODERATOR'}">
            <div class="wrap">
                <p class="form-title">
                    Create User</p>
                <form action="${pageContext.servletContext.contextPath}/" method="post" onsubmit="return validate()"
                      class="create">
                    <input type="text" name="login" placeholder="Login"><br>
                    <input type="password" name="password" placeholder="Password"><br>
                    <input type="text" name="firstName" placeholder="First Name"><br>
                    <input type="text" name="lastName" placeholder="Last Name">
                    <select name="role">
                        <option value="" disabled selected hidden>Choose role</option>
                    </select><br>
                    <input type="text" name="country" placeholder="Country">
                    <input type="text" name="city" placeholder="City">
                    <input type="text" name="street" placeholder="Street">
                    <fieldset>
                        <legend>Choose your music interests</legend>
                        <div>
                            <input type="checkbox" id="rap" name="interest" value="RAP">
                            <label for="RAP">RAP</label>
                        </div>
                        <div>
                            <input type="checkbox" id="rock" name="interest" value="ROCK">
                            <label for="ROCK">ROCK</label>
                        </div>
                        <div>
                            <input type="checkbox" id="pop" name="interest" value="POP">
                            <label for="POP">POP</label>
                        </div>
                        <div>
                            <input type="checkbox" id="jazz" name="interest" value="JAZZ">
                            <label for="JAZZ">JAZZ</label>
                        </div>
                    </fieldset>

                    <button type='submit' name='create' class="but" disabled="disabled">Create User</button>
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
                <th>login</th>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <th>password</th>
                </c:if>
                <th>firstName</th>
                <th>lastName</th>
                <th>timeCreate</th>
                <th>role</th>
                <th>country</th>
                <th>city</th>
                <th>street</th>
                <th>music types</th>
            </tr>
            <c:forEach items="${users}" var="entry">
                <tr>
                    <td>
                        <c:out value="${entry.key.id}"></c:out>
                    </td>
                    <td>
                        <c:out value="${entry.key.login}"></c:out>
                    </td>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <td>
                            <c:out value="${entry.key.password}"></c:out>
                        </td>
                    </c:if>
                    <td>
                        <c:out value="${entry.key.firstName}"></c:out>
                    </td>
                    <td>
                        <c:out value="${entry.key.lastName}"></c:out>
                    </td>
                    <td>
                        <c:out value="${entry.key.timeCreate.getTime()}"></c:out>
                    </td>
                    <td>
                        <c:out value="${entry.value[0].name}"></c:out>
                    </td>
                    <td>
                        <c:out value="${entry.value[1].country}"></c:out>
                    </td>
                    <td>
                        <c:out value="${entry.value[1].city}"></c:out>
                    </td>
                    <td>
                        <c:out value="${entry.value[1].street}"></c:out>
                    </td>
                    <td>
                        <c:forEach items="${entry.value[2]}" var="music">
                            <c:out value="${music.name}"></c:out>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="tableRow">
        <p class="form-title">
            Find User On Address</p>
        <form action="${pageContext.servletContext.contextPath}/adressFind"
              class="create">
            <input type="text" name="country" placeholder="Country"><br>
            <input type="text" name="city" placeholder="City"><br>
            <input type="text" name="street" placeholder="Street">
            <button type="submit" name="findOnAddress">Find</button>
        </form>
    </div>

    <div class="tableRow">
        <p class="form-title">
            Find User On Role</p>
        <form action="${pageContext.servletContext.contextPath}/roleFind"
              class="create">
            <input type="text" name="role" placeholder="Role"><br>
            <button type="submit" name="findOnRole">Find</button>
        </form>
    </div>

    <div class="tableRow">
        <p class="form-title">
            Find User On Music Type</p>
        <form action="${pageContext.servletContext.contextPath}/musicFind"
              class="create">
            <input type="text" name="music" placeholder="Music Type"><br>
            <button type="submit" name="findOnMusicType">Find</button>
        </form>
    </div>


    <c:if test="${sessionScope.role == 'ADMIN'}">
        <div class="tableRow">
            <p class="form-title">
                Admin Table</p>
            <table style="border: 1px solid white" cellpadding="1" cellspacing="1" border="1">
                <tr>
                    <th>Id</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>

                <c:forEach items="${users}" var="entry">
                    <tr>
                        <td>
                            <c:out value="${entry.key.id}"></c:out>
                        </td>
                        <td style="text-align: center">
                            <form action="${pageContext.servletContext.contextPath}/edit">
                                <button type="submit" value="${entry.key.id}" name="id">Update</button>
                            </form>
                        </td>
                        <td style="text-align: center">
                            <form action="${pageContext.servletContext.contextPath}/delete" method="post">
                                <button type="submit" value="${entry.key.id}" name="id">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <div class="tableRow">
        <form action="${pageContext.servletContext.contextPath}/signout" method="post">
            <input type="submit" value="Sign Out">
        </form>
    </div>
</div>

</body>
</html>
