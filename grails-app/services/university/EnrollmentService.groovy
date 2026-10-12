package university

import grails.gorm.transactions.Transactional

class EnrollmentService {

    @Transactional(readOnly = true)
    List<Enrollment> listEnrollments() {
        log.info("listEnrollments called")
        Enrollment.list(sort: 'enrolledAt', order: 'desc')
    }

    @Transactional(readOnly = true)
    long countEnrollments() {
        log.info("countEnrollments called")
        Enrollment.count()
    }

    @Transactional
    Enrollment enroll(Long studentId, Long courseId, Double grade = null) {
        log.info("enroll called: studentId=${studentId}, courseId=${courseId}, grade=${grade}")
        try {
            Student student = Student.get(studentId)
            Course course = Course.get(courseId)
            if (!student || !course) {
                throw new IllegalArgumentException("Please choose a student and a course.")
            }
            if (Enrollment.findByStudentAndCourse(student, course)) {
                throw new IllegalStateException("${student.name} is already enrolled in ${course.title}.")
            }

            Enrollment enrollment = new Enrollment(grade: grade)
            student.addToEnrollments(enrollment)
            course.addToEnrollments(enrollment)

            if (!enrollment.validate()) {
                throw new IllegalArgumentException("Invalid enrollment. Grade must be between 0 and 4.")
            }
            enrollment.save(flush: true)
            return enrollment
        } catch (Exception e) {
            log.error("enroll failed: ${e.message}")
            throw e
        }
    }

    @Transactional
    void unenroll(Long enrollmentId) {
        log.info("unenroll called: enrollmentId=${enrollmentId}")
        try {
            Enrollment enrollment = Enrollment.get(enrollmentId)
            if (!enrollment) {
                throw new IllegalArgumentException("Enrollment not found.")
            }
            enrollment.student.removeFromEnrollments(enrollment)
            enrollment.course.removeFromEnrollments(enrollment)
            enrollment.delete(flush: true)
        } catch (Exception e) {
            log.error("unenroll failed: ${e.message}")
            throw e
        }
    }

    @Transactional(readOnly = true)
    double calculateGpa(Long studentId) {
        log.info("calculateGpa called: studentId=${studentId}")
        try {
            Student student = Student.get(studentId)
            if (!student) return 0.0d

            List<Double> grades = Enrollment.findAllByStudent(student)*.grade.findAll { it != null }
            if (!grades) return 0.0d

            return grades.sum() / grades.size()
        } catch (Exception e) {
            log.error("calculateGpa failed: ${e.message}")
            throw e
        }
    }

    @Transactional
    void rollbackTest(Long studentId, Long courseId) {
        log.info("rollbackTest called: studentId=${studentId}, courseId=${courseId}")
        try {
            enroll(studentId, courseId)
            throw new RuntimeException("Forced failure after saving, to test rollback")
        } catch (Exception e) {
            log.error("rollbackTest failed: ${e.message}")
            throw e
        }
    }
}