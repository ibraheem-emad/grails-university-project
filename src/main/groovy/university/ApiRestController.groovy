package university

import grails.converters.JSON
import grails.rest.RestfulController

abstract class ApiRestController<T> extends RestfulController<T> {

    ApiRestController(Class<T> resource) {
        super(resource)
    }

    @Override
    def show() {
        T instance = queryForResource(params.id)
        if (instance == null) {
            notFound()
            return
        }
        respond instance
    }

    @Override
    protected void notFound() {
        String message = "${resource.simpleName} ${params.id} not found."
        render(status: 404, contentType: 'application/json', text: ([error: message] as JSON).toString())
    }
}