<!DOCTYPE html>
<html>
<head>
    <title>
        ToDoo - Task Lists
    </title>
</head>
<body>

<h1>
    Welcome to Todoo
</h1>

<#if failedLogin??><p style = "font-size:10px ; color: red">Login attempt failed, please try again</p></#if>

<form action="/todoo/login/submit" method="post">
    Username</b>
    <input label="Username" type="text" name="username"/>
    Password</b>
    <input label="Password" type="text" name="password"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>