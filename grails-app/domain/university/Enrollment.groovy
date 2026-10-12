package university

class Enrollment {
    String grade
    Date enrolledAt = new Date()

    static belongsTo = [student: Student, course: Course]

    static constraints = {
    }
}