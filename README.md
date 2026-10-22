Grails 7 application for managing students, courses and enrollments, built as part of the UBS Software Developer Intern Training Plan.

### Students

| Method | URL | Params | Response | Status codes |
|---|---|---|---|---|
| GET | `/api/students` | Query: `name` (contains, case-insensitive), `email` (contains, case-insensitive), `max` (page size, default 10, max 100), `offset` (rows to skip, default 0) | `{ total, page, max, data: [student] }` | 200 |
| GET | `/api/students/{id}` | Path: `id` | Student with `gpa` and `courses` list | 200, 404 |
| POST | `/api/students` | Body: `name` (required), `email` (required, valid, unique), `studentNumber` (required), `active` (true/false) | The created student | 201, 422 |
| PUT | `/api/students/{id}` | Path: `id`. Body: any student field to change | The updated student | 200, 404, 422 |
| DELETE | `/api/students/{id}` | Path: `id` | Empty. Also deletes the student's enrollments | 204, 404 |
| GET | `/api/students/{id}/courses` | Path: `id` | `[ { id, title, code, creditHours, grade, enrolledAt } ]` | 200, 404 |
| GET | `/api/students/{id}/gpa` | Path: `id` | `{ studentId, name, gpa }` | 200, 404 |

### Courses

| Method | URL | Params | Response | Status codes |
|---|---|---|---|---|
| GET | `/api/courses` | Query: `max` (default 10, max 100), `offset` (default 0) | `{ total, page, max, data: [course] }` | 200 |
| GET | `/api/courses/{id}` | Path: `id` | The course | 200, 404 |
| POST | `/api/courses` | Body: `title` (required), `code` (required, unique), `creditHours` (required, 1 to 6) | The created course | 201, 422 |
| PUT | `/api/courses/{id}` | Path: `id`. Body: any course field to change | The updated course | 200, 404, 422 |
| DELETE | `/api/courses/{id}` | Path: `id` | Empty. Also deletes the course's enrollments | 204, 404 |

### Enrollments

| Method | URL | Params | Response | Status codes |
|---|---|---|---|---|
| GET | `/api/enrollments` | Query: `max` (default 10, max 100), `offset` (default 0) | `{ total, page, max, data: [enrollment] }` | 200 |
| GET | `/api/enrollments/{id}` | Path: `id` | The enrollment | 200, 404 |
| POST | `/api/enrollments` | Body: `studentId` (required), `courseId` (required), `grade` (optional, 0 to 4) | The created enrollment | 201, 400, 409 |
| PUT | `/api/enrollments/{id}` | Path: `id`. Body: `grade` (0 to 4) | The updated enrollment | 200, 404, 422 |
| DELETE | `/api/enrollments/{id}` | Path: `id` | Empty | 204, 404 |



All requests, with saved example responses, are in `postman/University_API.postman_collection.json`.