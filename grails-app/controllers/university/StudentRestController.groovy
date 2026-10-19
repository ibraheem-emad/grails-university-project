package university

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class StudentRestController extends RestfulController<Student> {

    static responseFormats = ['json', 'html']

    StudentService studentService
    EnrollmentService enrollmentService

    StudentRestController() {
        super(Student)
    }

    @Override
    def show() {
        Student student = studentService.get(params.long('id'))
        if (!student) {
            respond([error: "Student ${params.id} not found."], status: NOT_FOUND)
            return
        }

        Map result = [
            id           : student.id,
            name         : student.name,
            email        : student.email,
            studentNumber: student.studentNumber,
            active       : student.active,
            gpa          : enrollmentService.calculateGpa(student.id),
            courses      : student.enrollments.sort { it.course.title }.collect { e ->
                [
                    id         : e.course.id,
                    title      : e.course.title,
                    code       : e.course.code,
                    creditHours: e.course.creditHours,
                    grade      : e.grade,
                    enrolledAt : e.enrolledAt
                ]
            }
        ]

           render result as JSON
    }
}