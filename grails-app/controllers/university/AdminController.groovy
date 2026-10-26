package university

class AdminController {

    def index() {
        [userCount: User.count()]
    }
}