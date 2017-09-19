<!DOCTYPE html>
<html>
<head>
    <title>
        ToDoo - Task List
    </title>
</head>
<body>
<h1>
    Your ToDo List
</h1>
<form>
    Task Description:<br>
    <input name="task"<br>
    <input type="submit" value="Add" formaction="/tasks/add" formmethod="post">
</form>
<#if toDoList.getTasks()?size gt 5><p style = "font-size:20px ; color: red">Bloody hell, you're busy!</p></#if>
<ul>
    <#list toDoList.getTasks() as item>
        <form>
            <li>${item}</li>
            <button value=${item} formaction="/tasks/remove" name="task" formmethod="post">Remove</button>
        </form>
    </#list>
</ul>
<p>
    You have ${toDoList.getTasks()?size} tasks in your To Do list
</p>
<form>
    <input type="submit" value="Download" method="get" action="ADD ACTION IN HERE FOR DOWNLOAD FILE">
</form>
</body>
</html>