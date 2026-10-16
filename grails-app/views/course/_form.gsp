<div class="mb-3">
    <label for="title" class="form-label">Title</label>
    <g:textField name="title" value="${course?.title}"
                 class="form-control ${hasErrors(bean: course, field: 'title', 'is-invalid')}"/>
    <g:hasErrors bean="${course}" field="title">
        <div class="invalid-feedback d-block"><g:fieldError bean="${course}" field="title"/></div>
    </g:hasErrors>
</div>
<div class="mb-3">
    <label for="code" class="form-label">Code</label>
    <g:textField name="code" value="${course?.code}"
                 class="form-control ${hasErrors(bean: course, field: 'code', 'is-invalid')}"/>
    <g:hasErrors bean="${course}" field="code">
        <div class="invalid-feedback d-block"><g:fieldError bean="${course}" field="code"/></div>
    </g:hasErrors>
</div>
<div class="mb-3">
    <label for="creditHours" class="form-label">Credit Hours</label>
    <g:select name="creditHours" from="${1..6}" value="${course?.creditHours}"
              noSelection="['': '-- Choose --']"
              class="form-select ${hasErrors(bean: course, field: 'creditHours', 'is-invalid')}"/>
    <g:hasErrors bean="${course}" field="creditHours">
        <div class="invalid-feedback d-block"><g:fieldError bean="${course}" field="creditHours"/></div>
    </g:hasErrors>
</div>