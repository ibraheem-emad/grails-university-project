<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Enrollments</title>
</head>
<body>
<div class="container">
    <h1>Enrollments</h1>
    <g:if test="${flash.message}">
        <div class="alert alert-primary">${flash.message}</div>
    </g:if>
    <g:link action="create" class="btn btn-primary mb-3">New Enrollment</g:link>
    <table class="table table-striped table-sm">
        <tr><th>Student</th><th>Course</th><th>Grade</th><th>Enrolled</th><th></th></tr>
        <g:each in="${enrollmentList}" var="e">
            <tr>
                <td>${e.student.name}</td>
                <td>${e.course.title}</td>
                <td>${e.grade}</td>
                <td><g:formatDate date="${e.enrolledAt}" format="yyyy-MM-dd"/></td>
                <td>
                    <g:link action="delete" id="${e.id}" class="text-danger"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                        Delete
                    </g:link>
                </td>
            </tr>
        </g:each>
    </table>
</div>
</body>
</html>