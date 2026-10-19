package university

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/enrollments"(resources: 'enrollmentRest')
        "/api/courses"(resources: 'courseRest')
        "/api/students"(resources: 'studentRest')
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
