<div class="mb-3">
    <label for="name" class="form-label">Name</label>
    <g:textField name="name" value="${student?.name}"
                 class="form-control ${hasErrors(bean: student, field: 'name', 'is-invalid')}"/>
    <g:hasErrors bean="${student}" field="name">
        <div class="invalid-feedback d-block"><g:fieldError bean="${student}" field="name"/></div>
    </g:hasErrors>
</div>
<div class="mb-3">
    <label for="email" class="form-label">Email</label>
    <g:textField name="email" value="${student?.email}"
                 class="form-control ${hasErrors(bean: student, field: 'email', 'is-invalid')}"/>
    <g:hasErrors bean="${student}" field="email">
        <div class="invalid-feedback d-block"><g:fieldError bean="${student}" field="email"/></div>
    </g:hasErrors>
</div>
<div class="mb-3">
    <label for="studentNumber" class="form-label">Student Number</label>
    <g:textField name="studentNumber" value="${student?.studentNumber}"
                 class="form-control ${hasErrors(bean: student, field: 'studentNumber', 'is-invalid')}"/>
    <g:hasErrors bean="${student}" field="studentNumber">
        <div class="invalid-feedback d-block"><g:fieldError bean="${student}" field="studentNumber"/></div>
    </g:hasErrors>
</div>
<div class="form-check mb-3">
    <g:checkBox name="active" value="${student?.active}" class="form-check-input"/>
    <label for="active" class="form-check-label">Active student</label>
</div>