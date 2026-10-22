package university

import grails.converters.JSON
import grails.rest.RestfulController
import grails.gorm.PagedResultList

abstract class ApiRestController<T> extends RestfulController<T> {

    ApiRestController(Class<T> resource) {
        super(resource)
    }

    @Override
    def index(Integer max) {
        int pageSize = Math.min(max ?: 10, 100)
        int offset = params.int('offset') ?: 0
        params.max = pageSize
        params.offset = offset

        List<T> items = listAllResources(params)
        long total = (items instanceof PagedResultList) ? items.totalCount : countResources()

        render([
            total: total,
            page : offset.intdiv(pageSize) + 1,
            max  : pageSize,
            data : items
        ] as JSON)
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