package university

class Enrollment {
    Double grade
    Date enrolledAt = new Date()

    static belongsTo = [student: Student, course: Course]

    static constraints = {
        grade nullable: true, min: 0d, max: 4d
    }
}