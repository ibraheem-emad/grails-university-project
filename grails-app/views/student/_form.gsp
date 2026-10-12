   <div class="mb-3">
       <label for="name" class="form-label">Name</label>
       <g:textField name="name" value="${student?.name}" class="form-control"/>
   </div>
   <div class="mb-3">
       <label for="email" class="form-label">Email</label>
       <g:textField name="email" value="${student?.email}" class="form-control"/>
   </div>
   <div class="mb-3">
       <label for="studentNumber" class="form-label">Student Number</label>
       <g:textField name="studentNumber" value="${student?.studentNumber}" class="form-control"/>
   </div>
   <div class="form-check mb-3">
       <g:checkBox name="active" value="${student?.active}" class="form-check-input"/>
       <label for="active" class="form-check-label">Active student</label>
   </div>