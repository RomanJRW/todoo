<!DOCTYPE html>
<html>
<head>
    <title>
        ToDoo - Task Lists
    </title>
</head>
<body>
<h1>
    ${toDoList.name}
</h1>

<#if toDoList.tasks?size gt 5><p style = "font-size:20px ; color: red">Bloody hell, you're busy!</p></#if>
<ul>
<#list toDoList.getTasks() as item>
    <form>
        <li>${item}</li>
        <button value=${item.identifier} formaction="/task/delete" name="task" formmethod="post">Done</button>
    </form>
</#list>
</ul>

<p>
    You have ${toDoList.getTasks()?size} tasks in your To Do list
</p>

<form action="/task/add" method="post">
    New Task:<br>
    <input type="text" name="description"/>
    <button type="submit">Submit</button>
</form>

<a href="/tasklist/csv" download="my_tasks.csv">Download</a>
</body>
</html>