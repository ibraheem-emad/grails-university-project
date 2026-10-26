package university

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

@Secured('isAuthenticated()')
class StudentController {

    StudentService studentService
    EnrollmentService enrollmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.offset = params.int('offset') ?: 0

        def studentList
        def studentCount

        if (params.search) {
            def pattern = "%${params.search}%"
            studentList = Student.findAllByNameIlike(pattern, [max: params.max, offset: params.offset])
            studentCount = Student.countByNameIlike(pattern)
        } else {
            studentList = Student.list(max: params.max, offset: params.offset)
            studentCount = Student.count()
        }

        [studentList: studentList, studentCount: studentCount]
    }    

    def show(Long id) {
        respond studentService.get(id), model: [gpa: enrollmentService.calculateGpa(id)]
    }

    def create() {
        respond new Student(params)
    }

    def save(Student student) {
        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*' { respond student, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond studentService.get(id)
    }

    def update(Student student) {
        if (student == null) {
            notFound()
            return
        }

        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile photo = request.getFile('profilePhoto')
            if (photo && !photo.empty) {
                if (!photo.contentType?.startsWith('image/')) {
                    student.errors.rejectValue('profilePhoto', 'student.profilePhoto.type', 'Only image files are allowed.')
                } else if (photo.size >= 2 * 1024 * 1024) {
                    student.errors.rejectValue('profilePhoto', 'student.profilePhoto.size', 'The photo must be smaller than 2 MB.')
                }
                if (student.errors.hasFieldErrors('profilePhoto')) {
                    respond student.errors, view: 'edit'
                    return
                }
            }
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            respond student.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*'{ respond student, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        studentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'student.label', default: 'Student'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
