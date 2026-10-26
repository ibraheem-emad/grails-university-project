package university

class Student {

    String name
    String email
    String studentNumber
    boolean active = true
    byte[] profilePhoto
    String contentType
    
    static hasMany = [enrollments: Enrollment]

    static constraints = {
        name blank: false
        email email: true, unique: true
        profilePhoto nullable: true, maxSize: 2 * 1024 * 1024, bindable: false
        contentType nullable: true, bindable: false
    }

    static mapping = {
    profilePhoto sqlType: 'longblob'
    }
}
