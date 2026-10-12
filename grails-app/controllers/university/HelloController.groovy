package university

class HelloController {

    // remove the slashes from the following line to show the render func text, put them back to see the view text
    def index() {
        //render "Hello from UBS Intern Training"
    }

    def showMessage(String name) {
        render "Hello, ${name}! Welcome to UBS Intern Training"
    }
}