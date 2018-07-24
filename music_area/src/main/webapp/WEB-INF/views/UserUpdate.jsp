<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update Page</title>
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

        $(function () {
            var inp = $('input[name="interest"]');
            inp.on('click', function () {
                $('button[class="but"]')
                    .attr('disabled', $('input[name="interest"]:checked').length ? false : true);
            });
        });
    </script>
    <script>
        function validate() {
            var result = false;
            console.log($("input[name='interest']").val());
            console.log($("interest:checked"));
            if ($("input[name='login']").val() != null && $("input[name='password']").val() != null && $("select[name='role']").val() != null && $("input[name='interest']").val() != null) {
                result = true;
            }
            if (!result) {
                alert("Please choose login, password, role and music type ");
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
        <input type="text" name="login" placeholder="New Login"><br>
        <input type="text" name="password" placeholder="New Password"><br>
        <input type="text" name="firstName" placeholder="New First Name"><br>
        <input type="text" name="lastName" placeholder="New Last Name"><br>
        <select name="role">
            <option value="" disabled selected hidden>Choose new role</option>
        </select><br>
        <input type="text" name="country" placeholder="New Country">
        <input type="text" name="city" placeholder="New City">
        <input type="text" name="street" placeholder="New Street">
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
        <button type="submit" name="id" class="but" disabled="disabled" value="${param.id}">Submit</button>
    </form>
</div>
</body>
</html>
