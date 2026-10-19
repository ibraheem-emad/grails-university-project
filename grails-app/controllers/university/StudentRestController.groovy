package university

import grails.converters.JSON

class StudentRestController extends ApiRestController<Course> {

    static responseFormats = ['json', 'html']

    StudentService studentService
    EnrollmentService enrollmentService

    StudentRestController() {
        super(Student)
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

    private List<Map> coursesOf(Student student) {
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