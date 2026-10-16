<div class="card h-100 shadow-sm">
    <div class="card-body">
        <h5 class="card-title">${student.name}</h5>
        <p class="card-text text-muted mb-1">${student.email}</p>
        <p class="card-text small mb-0">No. ${student.studentNumber}</p>
    </div>
    <div class="card-footer bg-transparent">
        <g:link controller="student" action="show" id="${student.id}" class="btn btn-sm btn-outline-primary">View</g:link>
    </div>
</div>