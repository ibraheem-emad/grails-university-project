Grails 7 application for managing students, courses and enrollments, built as part of the UBS Software Developer Intern Training Plan.

### Students

| Method | URL | Params | Response |
|---|---|---|---|
| GET | `/api/students` | Query: `name` (contains, case-insensitive), `email` (contains, case-insensitive), `max` (page size, default 10, max 100), `offset` (rows to skip, default 0) | `{ total, page, max, data: [student] }` |
| GET | `/api/students/{id}` | Path: `id` | Student with `gpa` and `courses` list |
| POST | `/api/students` | Body: `name` (required), `email` (required, valid, unique), `studentNumber` (required), `active` (true/false) | The created student |
| PUT | `/api/students/{id}` | Path: `id`. Body: any student field to change | The updated student |
| DELETE | `/api/students/{id}` | Path: `id` | Empty. Also deletes the student's enrollments |
| GET | `/api/students/{id}/courses` | Path: `id` | `[ { id, title, code, creditHours, grade, enrolledAt } ]` |
| GET | `/api/students/{id}/gpa` | Path: `id` | `{ studentId, name, gpa }` |

### Courses

| Method | URL | Params | Response |
|---|---|---|---|
| GET | `/api/courses` | Query: `max` (default 10, max 100), `offset` (default 0) | `{ total, page, max, data: [course] }` |
| GET | `/api/courses/{id}` | Path: `id` | The course |
| POST | `/api/courses` | Body: `title` (required), `code` (required, unique), `creditHours` (required, 1 to 6) | The created course |
| PUT | `/api/courses/{id}` | Path: `id`. Body: any course field to change | The updated course |
| DELETE | `/api/courses/{id}` | Path: `id` | Empty. Also deletes the course's enrollments |

### Enrollments

| Method | URL | Params | Response |
|---|---|---|---|
| GET | `/api/enrollments` | Query: `max` (default 10, max 100), `offset` (default 0) | `{ total, page, max, data: [enrollment] }` |
| GET | `/api/enrollments/{id}` | Path: `id` | The enrollment |
| POST | `/api/enrollments` | Body: `studentId` (required), `courseId` (required), `grade` (optional, 0 to 4) | The created enrollment |
| PUT | `/api/enrollments/{id}` | Path: `id`. Body: `grade` (0 to 4) | The updated enrollment |
| DELETE | `/api/enrollments/{id}` | Path: `id` | Empty |

All requests, with saved example responses, are in `postman/University_API.postman_collection.json`.