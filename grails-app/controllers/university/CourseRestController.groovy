   package university

   import grails.rest.RestfulController

   class CourseRestController extends ApiRestController<Course> {

       static responseFormats = ['json', 'html']

       CourseRestController() {
           super(Course)
       }
   }