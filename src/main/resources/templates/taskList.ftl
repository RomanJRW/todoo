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
    <input type="submit" value="Add" formaction="/task/add" formmethod="post">
</form>
<#if toDoList.tasks?size gt 5><p style = "font-size:20px ; color: red">Bloody hell, you're busy!</p></#if>
<ul>
    <#list toDoList.getTasks() as item>
        <form>
            <li>${item}</li>
            <button value=${item} formaction="/task/delete" name="task" formmethod="post">Remove</button>
        </form>
    </#list>
</ul>
<p>
    You have ${toDoList.getTasks()?size} tasks in your To Do list
</p>
<a href="/tasklist/csv" download="my_tasks.csv">Download</a>
</body>
</html>