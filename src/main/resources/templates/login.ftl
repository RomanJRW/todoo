<!DOCTYPE html>
<html>
<head>
    <title>
        ToDoo - the ultimate task manager
    </title>
    <style>
        * {
            font-family: Arial;
        }
        body {
            background: #62619d;
            margin: 0px;
        }
        #mainContainer {
            width: 100%;
        }
        .header h1 {
            background: #7185b5;
            font-size: 30px;
            margin: 0px;
        }
        .loginForms {
            background: #7185b5;
            width: 480px;
            margin: 40px auto 20px auto;
            padding: 20px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div id="mainContainer">
        <div class="header">
            <h1>
                ToDoo - the ultimate task manager
            </h1>
        </div>

        <#if logout??><p style = "font-size:25px ; color: red">Successfully signed out</p></#if>

        <#if failedLogin??><p style = "font-size:25px ; color: red">Login attempt failed, please try again</p></#if>

        <div class="loginForms">
            <form action="/todoo/login" method="post">
                Username<br>
                <input label="Email Address" type="text" name="username"/>
                <br>
                Password<br>
                <input label="Password" type="password" name="password"/>
                <br>
                <button type="submit">Log in</button>
            </form>

            <br>
            <br>

            <#if failedRegistration??><p style = "font-size:25px ; color: red">Registration failed, please try again</p></#if>

            <form action="/todoo/register" method="post">
                First name<br>
                <input label="First Name" type="text" name="firstName"/>
                <br>
                Surname<br>
                <input label="Surname" type="text" name="lastName"/>
                <br>
                Username<br>
                <input label="Email Address" type="text" name="username"/>
                <br>
                Password<br>
                <input label="Password" type="password" name="password"/>
                <br>
                <button type="submit">Register</button>
            </form>
        </div>
    </div>
</body>
</html>