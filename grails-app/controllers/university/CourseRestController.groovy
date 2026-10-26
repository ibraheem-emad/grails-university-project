package university

import grails.rest.RestfulController
import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class CourseRestController extends ApiRestController<Course> {

    static responseFormats = ['json', 'html']

    CourseRestController() {
        super(Course)
    }
}