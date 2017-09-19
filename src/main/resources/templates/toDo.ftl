<!DOCTYPE html>
<html>
<head>
    <title>
        ToDoo - ToDo List!
    </title>
</head>
<body>
<h1>
    Your ToDo List
</h1>
<#if listTotal gt 5><p style = "font-size:20px ; color: red">Bloody hell, you're busy!</p></#if>
<ul>
    <#list toDoList as item>
        <li>${item}</li>
    </#list>
</ul>
<p>
    You have ${listTotal} tasks in your To Do list
</p>
</body>
</html>