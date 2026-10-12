   <div class="mb-3">
       <label for="title" class="form-label">Title</label>
       <g:textField name="title" value="${course?.title}" class="form-control"/>
   </div>
   <div class="mb-3">
       <label for="code" class="form-label">Code</label>
       <g:textField name="code" value="${course?.code}" class="form-control"/>
   </div>
   <div class="mb-3">
       <label for="creditHours" class="form-label">Credit Hours</label>
       <g:select name="creditHours" from="${1..6}" value="${course?.creditHours}"
                 noSelection="['': '-- Choose --']" class="form-select"/>
   </div>