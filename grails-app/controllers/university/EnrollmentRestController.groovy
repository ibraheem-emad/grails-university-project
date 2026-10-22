package university

import static org.springframework.http.HttpStatus.*

class EnrollmentRestController extends ApiRestController<Enrollment> {

    static responseFormats = ['json', 'html']

    EnrollmentService enrollmentService

    EnrollmentRestController() {
        super(Enrollment)
    }

    @Override
    def save() {
        try {
            def body = request.JSON
            Enrollment e = enrollmentService.enroll(body.studentId as Long, body.courseId as Long, body.grade as Double)
            respond e, [status: CREATED]
        } catch (IllegalStateException ex) {
            respond([error: ex.message], status: CONFLICT)
        } catch (Exception ex) {
            respond([error: ex.message], status: BAD_REQUEST)
        }
    }

    @Override
    def delete() {
        try {
            enrollmentService.unenroll(params.long('id'))
            render status: NO_CONTENT
        } catch (Exception ex) {
            respond([error: ex.message], status: NOT_FOUND)
        }
    }
}