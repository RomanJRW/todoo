<!DOCTYPE html>
<html>
<head>
    <title>
        ToDoo - Task Lists
    </title>
</head>
<body>

<h1>
    Your Task Lists
</h1>

<#if toDoList.getName()??>
    <h2>
        ${toDoList.getName()}
    </h2>
<#else>
    <h2>
        My First Task List
    </h2>
</#if>

<#if toDoList.getName()?has_content>

    <#if toDoList.tasks?size gt 5><p style = "font-size:20px ; color: red">Bloody hell, you're busy!</p></#if>
    <ul>
    <#list toDoList.getTasks() as item>
        <form>
            <li>${item.getDescription()}</li>
            <button value=${item.getId()} formaction="/task/delete" name="taskId" formmethod="post">Done</button>
        </form>
    </#list>
    </ul>

    <p>
        You have ${toDoList.getTasks()?size} tasks in your To Do list
    </p>
    <form action="/task/add/${toDoList.getId()}" method="post">
        New Task:<br>
        <input type="text" name="description"/>
        <button type="submit">Submit</button>
    </form>
<#else>
    <form action="/tasklist/add" method="post">
        New Task List Name:<br>
        <input type="text" name="name"/>
        <button type="submit">Submit</button>
    </form>
</#if>



    <a href="/tasklist/${toDoList.getId()}/csv" download="my_tasks.csv">Download</a>

</body>
</html>