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

<#list toDoLists as toDoList>

    <#if toDoList.getName()??>
    <h2>
    ${toDoList.getName()}
    </h2>
    </#if>

    <#if toDoList.tasks?size gt 5><p style = "font-size:20px ; color: red">Bloody hell, you're busy!</p></#if>
    <ul>
        <#list toDoList.getTasks() as item>
            <form>
                <li>${item.getDescription()}</li>
                <button formaction="/task/complete/${item.getId()}" name="taskId" formmethod="post">Complete</button>
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
    <a href="/tasklist/${toDoList.getId()}/csv" download="${toDoList.getName()}.csv">Download</a>
    <form>
        <button formaction="/tasklist/delete/${toDoList.getId()}" name="taskListId" formmethod="post">Delete Task List</button>
    </form>
    <br>
    <br>

</#list>

<p>
    <form action="/tasklist/add" method="post">
        New Task List Name:<br>
        <input type="text" name="name"/>
        <button type="submit">Submit</button>
    </form>
</p>

<p>
    <br>
    <br>
    <form>
        <button formaction="/todoo/logout" formmethod="post">Sign out</button>
    </form>
</p>

</body>
</html>