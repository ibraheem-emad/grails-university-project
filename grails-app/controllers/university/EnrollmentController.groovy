package university

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
            flash.message = ex.message
            redirect action: 'create'
        }
    }

    def delete(Long id) {
        try {
            enrollmentService.unenroll(id)
            flash.message = "Enrollment removed."
        } catch (Exception ex) {
            log.error("delete failed: ${ex.message}")
            flash.message = ex.message
        }
        redirect action: 'index'
    }

    // Step 9: manual GPA test
    def gpaTest(Long id) {
        double gpa = enrollmentService.calculateGpa(id)
        render "GPA for student ${id}: ${gpa}"
    }

    // Step 3: rollback test
    def rollbackTest(Long studentId, Long courseId) {
        long before = enrollmentService.countEnrollments()
        try {
            enrollmentService.rollbackTest(studentId, courseId)
        } catch (Exception ex) {
            log.error("rollbackTest failed: ${ex.message}")
        }
        long after = enrollmentService.countEnrollments()
        render "Before: ${before}, after: ${after}. " + (before == after ? "Rolled back correctly." : "NOT rolled back!")
    }
}