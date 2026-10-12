package university

class Student {

    String name
    String email
    String studentNumber
    boolean active = true
    
    static hasMany = [enrollments: Enrollment]

    static constraints = {
        name blank: false
        email email: true, unique: true
    }
}
