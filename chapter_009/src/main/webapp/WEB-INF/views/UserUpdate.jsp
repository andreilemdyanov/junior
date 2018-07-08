<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Page</title>
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
            if ($("select[name='country']").val() != null && $("select[name='city']").val() != null) {
                result = true;
            }
            if (!result) {
                alert("Please choose country and city ");
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

        form.update input[type="text"], form.update input[type="password"] {
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

        form.update input[type="submit"] {
            width: 100%;
            font-size: 14px;
            text-transform: uppercase;
            font-weight: 500;
            margin-top: 16px;
            outline: 0;
            cursor: pointer;
            letter-spacing: 1px;
        }

        form.update input[type="submit"]:hover {
            transition: background-color 0.5s ease;
        }

        form.update select {
            background: transparent;
        }

        option {
            background: transparent;
        }

        form.update {
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

    </style>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<div class="wrap">
    <p class="form-title">
        Update User</p>
    <form action="${pageContext.servletContext.contextPath}/editJSP" method="post" onsubmit="return validate()"
          class="update">
        <input type="text" name="name" placeholder="New Name"><br>
        <input type="text" name="login" placeholder="New Login"><br>
        <input type="text" name="password" placeholder="New Password"><br>
        <input type="text" name="email" placeholder="New Email"><br>
        <select name="country">
            <option value="" disabled selected hidden>Choose country</option>
            <option value="Russia">Russia</option>
            <option value="USA">USA</option>
        </select>
        <select name="city">
            <option value="" disabled selected hidden>Choose city</option>
        </select>
        <button type="submit" name="id" value="${param.id}">Submit</button>
    </form>
</div>
</body>
</html>
