package university

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class EnrollmentController {

    EnrollmentService enrollmentService
    StudentService studentService
    CourseService courseService

    static allowedMethods = [save: "POST"]

    def index() {
        [enrollmentList: enrollmentService.listEnrollments()]
    }

    def create() {
        [students: studentService.list(sort: 'name'), courses: courseService.list(sort: 'title')]
    }

    def save() {
        try {
            def e = enrollmentService.enroll(params.long('student'), params.long('course'), params.double('grade'))
            flash.message = "Enrolled ${e.student.name} in ${e.course.title}."
            redirect action: 'index'
        } catch (Exception ex) {
            log.error("save failed: ${ex.message}")
            flash.error = ex.message
            redirect action: 'create'
        }
    }

    def delete(Long id) {
        try {
            enrollmentService.unenroll(id)
            flash.message = "Enrollment removed."
        } catch (Exception ex) {
            log.error("delete failed: ${ex.message}")
            flash.error = ex.message
        }
        redirect action: 'index'
    }

    def calculateGpa(Long id) {
        double gpa = enrollmentService.calculateGpa(id)
        render "GPA for student ${id}: ${gpa}"
    }

    def rollbackTest(Long studentId, Long courseId) {
    if (enrollmentService.isEnrolled(studentId, courseId)) {
        render "Already enrolled. Pick a student and course that are not enrolled together, otherwise nothing is saved and there is nothing to roll back."
        return
    }
    long before = enrollmentService.countEnrollments()
    String reason = ""
    try {
        enrollmentService.rollbackTest(studentId, courseId)
    } catch (Exception ex) {
        log.error("rollbackTest failed: ${ex.message}")
        reason = ex.message
    }
    long after = enrollmentService.countEnrollments()
    boolean stillEnrolled = enrollmentService.isEnrolled(studentId, courseId)
    render "Before: ${before}, after: ${after}, enrolled after test: ${stillEnrolled}. Reason: ${reason}"
    }
}