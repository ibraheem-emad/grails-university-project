<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title><g:layoutTitle default="University"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body class="d-flex flex-column min-vh-100">

<header>
    <nav class="navbar navbar-expand-md bg-primary" data-bs-theme="dark">
        <div class="container">
            <g:link class="navbar-brand fw-semibold" uri="/">University</g:link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav"
                    aria-controls="mainNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mainNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><g:link class="nav-link ${controllerName == 'student' ? 'active' : ''}" controller="student">Students</g:link></li>
                    <li class="nav-item"><g:link class="nav-link ${controllerName == 'course' ? 'active' : ''}" controller="course">Courses</g:link></li>
                    <li class="nav-item"><g:link class="nav-link ${controllerName == 'enrollment' ? 'active' : ''}" controller="enrollment">Enrollments</g:link></li>
                    <li class="nav-item"><g:link class="nav-link ${controllerName == 'hello' ? 'active' : ''}" controller="hello">Hello</g:link></li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<main class="flex-grow-1 py-4">
    <div class="container">
        <g:layoutBody/>
    </div>
</main>

<footer class="bg-light border-top py-3 mt-auto">
    <div class="container text-center text-muted small">
        UBS Intern Training, University App
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<asset:javascript src="application.js"/>
</body>
</html>