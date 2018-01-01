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

<#if logout??><p style = "font-size:25px ; color: red">Successfully signed out</p></#if>

<#if failedLogin??><p style = "font-size:25px ; color: red">Login attempt failed, please try again</p></#if>

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
    Username<br>
    <input label="Email Address" type="text" name="username"/>
    <br>
    Password<br>
    <input label="Password" type="password" name="password"/>
    <br>
    <button type="submit">Register</button>
</form>
</body>
</html>