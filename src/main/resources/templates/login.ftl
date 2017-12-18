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

<#if failedLogin??><p style = "font-size:25px ; color: red">Login attempt failed, please try again</p></#if>

<form action="/todoo/login" method="post">
    Username</b>
    <input label="Username" type="text" name="username"/>
    Password</b>
    <input label="Password" type="password" name="password"/>
    <button type="submit">Submit</button>
</form>

<#if failedRegistration??><p style = "font-size:25px ; color: red">Registration failed, please try again</p></#if>

<form action="/todoo/register" method="post">
    Username</b>
    <input label="Username" type="text" name="username"/>
    Password</b>
    <input label="Password" type="password" name="password"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>