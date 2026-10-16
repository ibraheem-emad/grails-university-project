<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Dashboard</title>
</head>
<body>
<h1 class="mb-4">Dashboard</h1>

<div class="row g-3">
    <div class="col-12 col-md-4">
        <div class="card text-bg-primary h-100">
            <div class="card-body">
                <h5 class="card-title">Students</h5>
                <p class="display-6 mb-0">${studentCount}</p>
            </div>
            <div class="card-footer bg-transparent border-0">
                <g:link controller="student" class="text-white">View all</g:link>
            </div>
        </div>
    </div>
    <div class="col-12 col-md-4">
        <div class="card text-bg-success h-100">
            <div class="card-body">
                <h5 class="card-title">Courses</h5>
                <p class="display-6 mb-0">${courseCount}</p>
            </div>
            <div class="card-footer bg-transparent border-0">
                <g:link controller="course" class="text-white">View all</g:link>
            </div>
        </div>
    </div>
    <div class="col-12 col-md-4">
        <div class="card text-bg-warning h-100">
            <div class="card-body">
                <h5 class="card-title">Enrollments</h5>
                <p class="display-6 mb-0">${enrollmentCount}</p>
            </div>
            <div class="card-footer bg-transparent border-0">
                <g:link controller="enrollment" class="text-dark">View all</g:link>
            </div>
        </div>
    </div>
</div>
<h2 class="h4 mt-5 mb-3">Newest Students</h2>
<div class="row g-3">
    <g:each in="${recentStudents}" var="s">
        <div class="col-12 col-sm-6 col-lg-3">
            <g:render template="studentCard" model="[student: s]"/>
        </div>
    </g:each>
</div>
</body>
</html>