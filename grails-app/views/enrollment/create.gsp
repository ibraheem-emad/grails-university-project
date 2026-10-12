<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>New Enrollment</title>
</head>
<body>
<div class="container">
    <h1>New Enrollment</h1>
    <g:if test="${flash.message}">
        <div class="alert alert-warning">${flash.message}</div>
    </g:if>
    <g:form action="save">
        <div class="mb-3">
            <label>Student</label>
            <g:select name="student" from="${students}" optionKey="id" optionValue="name"
                      noSelection="['': '-- Choose a student --']" class="form-select"/>
        </div>
        <div class="mb-3">
            <label>Course</label>
            <g:select name="course" from="${courses}" optionKey="id" optionValue="title"
                      noSelection="['': '-- Choose a course --']" class="form-select"/>
        </div>
        <div class="mb-3">
            <label>Grade (optional)</label>
            <g:textField name="grade" class="form-control"/>
        </div>
        <g:submitButton name="enroll" value="Enroll" class="btn btn-primary"/>
        <g:link action="index" class="btn btn-secondary">Back to list</g:link>
    </g:form>
</div>
</body>
</html>