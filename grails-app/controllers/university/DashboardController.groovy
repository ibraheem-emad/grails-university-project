package university

class DashboardController {

    StudentService studentService
    CourseService courseService
    EnrollmentService enrollmentService

    def index() {
        [studentCount   : studentService.count(),
         courseCount    : courseService.count(),
         enrollmentCount: enrollmentService.countEnrollments()]
    }
}