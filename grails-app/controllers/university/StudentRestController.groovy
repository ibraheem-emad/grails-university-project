   package university

   import grails.rest.RestfulController

   class StudentRestController extends RestfulController<Student>{
       static responseFormats = ['json', 'html']

       StudentRestController() {
           super(Student)
       }
   }