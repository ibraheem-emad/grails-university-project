package university
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class DashboardController {

    StudentService studentService
    CourseService courseService
    EnrollmentService enrollmentService

    def index() {
        [studentCount   : studentService.count(),
        courseCount    : courseService.count(),
        enrollmentCount: enrollmentService.countEnrollments(),
        recentStudents : studentService.list(max: 4, sort: 'id', order: 'desc')]
    }
}