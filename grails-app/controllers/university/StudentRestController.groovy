package university

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class StudentRestController extends ApiRestController<Student> {

    static responseFormats = ['json', 'html']

    StudentService studentService
    EnrollmentService enrollmentService

    StudentRestController() {
        super(Student)
    }

    @Override
    protected List<Student> listAllResources(Map params) {
        Student.createCriteria().list(max: params.max, offset: params.offset) {
            if (params.name) {
                ilike('name', "%${params.name}%".toString())
            }
            if (params.email) {
                ilike('email', "%${params.email}%".toString())
            }
            order('name')
        }
    }

    @Override
    def show() {
        Student student = studentService.get(params.long('id'))
        if (!student){
            notFound()
            return
        }

        Map result = [
            id           : student.id,
            name         : student.name,
            email        : student.email,
            studentNumber: student.studentNumber,
            active       : student.active,
            gpa          : enrollmentService.calculateGpa(student.id),
            courses      : coursesOf(student)
        ]

           render result as JSON
    }
    
    def courses() {
        Student student = studentService.get(params.long('id'))
        if (!student) {
            notFound()
            return
        }
        render coursesOf(student) as JSON
    }

    def gpa() {
        Student student = studentService.get(params.long('id'))
        if (!student) {
            notFound()
            return
        }
        render([
            studentId: student.id,
            name     : student.name,
            gpa      : enrollmentService.calculateGpa(student.id)
        ] as JSON)
    }

    List<Map> coursesOf(Student student) {
        student.enrollments.sort { it.course.title }.collect { e ->
            [
                id         : e.course.id,
                title      : e.course.title,
                code       : e.course.code,
                creditHours: e.course.creditHours,
                grade      : e.grade,
                enrolledAt : e.enrolledAt
            ]
        }
    }
}