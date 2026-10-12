package university

import grails.gorm.transactions.Transactional

class EnrollmentController {

    static allowedMethods = [save: "POST"]

    def index() {
        [enrollmentList: Enrollment.list(sort: 'enrolledAt', order: 'desc')]
    }

    def create() {
        [students: Student.list(sort: 'name'), courses: Course.list(sort: 'title')]
    }

    @Transactional
    def save() {
        def student = Student.get(params.long('student'))
        def course = Course.get(params.long('course'))

        if (!student || !course) {
            flash.message = "Please choose a student and a course."
            redirect action: 'create'
            return
        }

        if (Enrollment.findByStudentAndCourse(student, course)) {
            flash.message = "${student.name} is already enrolled in ${course.title}."
            redirect action: 'create'
            return
        }

        def enrollment = new Enrollment(student: student, course: course, grade: params.grade)
        if (!enrollment.save(flush: true)) {
            flash.message = "Could not save the enrollment."
            redirect action: 'create'
            return
        }

        flash.message = "Enrolled ${student.name} in ${course.title}."
        redirect action: 'index'
    }
    @Transactional
    def delete(Long id) {   
        def enrollment = Enrollment.get(id)
        if (enrollment) {
            enrollment.student.removeFromEnrollments(enrollment)
            enrollment.course.removeFromEnrollments(enrollment)
            enrollment.delete(flush: true)
            flash.message = "Enrollment removed."
        }
        redirect action: 'index'
    }
}