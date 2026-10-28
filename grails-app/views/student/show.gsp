<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#show-student" class="visually-hidden-focusable" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <ul class="navbar-nav container-fluid">
                    <li class="nav-item"><a class="nav-link btn" aria-label="Home" href="${createLink(uri: '/')}">
                        <i class="bi-house"></i> <g:message code="default.home.label"/></a>
                    </li>
                    <li class="nav-item"><g:link class="nav-link btn" aria-label="List" action="index">
                        <i class="bi-database"></i> <g:message code="default.list.label" args="[entityName]" /></g:link>
                    </li>
                    <li class="nav-item me-lg-auto">
                        <g:link class="nav-link btn" aria-label="List" action="create"><i class="bi-database-add"></i> <g:message code="default.new.label" args="[entityName]" /></g:link>
                    </li>
                </ul>
            </nav>
        </section>
        <section class="row">
            <div id="show-student" class="col-12 content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="alert alert-primary" role="alert"><i class="bi-info-circle"></i> ${flash.message}</div>
                </g:if>
                <g:if test="${student.profilePhoto}">
                    <img src="${createLink(controller: 'student', action: 'renderPhoto', id: student.id)}"
                        alt="Photo of ${student.name}" class="img-thumbnail mb-3" style="max-width: 200px;"/>
                </g:if>
                <g:else>
                    <p class="text-muted">No photo uploaded.</p>
                </g:else>
                <f:display bean="student" listClass="container" listItemClass="row mb-3" labelClass="form-label col-sm-3 text-sm-end" valueClass="col-sm-9" except="['profilePhoto', 'contentType', 'enrollments']" />
                <p class="mt-3"><strong>GPA:</strong> <g:formatNumber number="${gpa}" format="0.00"/></p>
                <h3 class="mt-4">Enrolled Courses</h3>
                <g:if test="${student.enrollments}">
                    <div class="table-responsive">
                        <table class="table table-sm">
                            <tr><th>Course</th><th>Code</th><th>Grade</th></tr>
                            <g:each in="${student.enrollments.sort { it.course.title }}" var="e">
                                <tr>
                                    <td><g:link controller="course" action="show" id="${e.course.id}">${e.course.title}</g:link></td>
                                    <td>${e.course.code}</td>
                                    <td>${e.grade ?: '-'}</td>
                                </tr>
                            </g:each>
                            </table>
                        </div>
                </g:if>
                <g:else>
                    <p>Not enrolled in any courses yet.</p>
                </g:else>
                <g:form resource="${this.student}" controller="${controllerName}" method="DELETE">
                    <fieldset class="bg-body-tertiary">
                        <g:link class="btn btn-outline-primary" action="edit" resource="${this.student}" controller="${controllerName}">
                            <i class="bi-pencil-square"></i> <g:message code="default.button.edit.label" default="Edit" />
                        </g:link>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                            <button class="btn btn-outline-primary" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                                <i class="bi-trash"></i> ${message(code: 'default.button.delete.label', default: 'Delete')}
                            </button>
                        </sec:ifAnyGranted>
                    </fieldset>
                </g:form>
            </div>
        </section>
    </div>
</div>
</body>
</html>
