package university

import java.time.Instant
import java.time.temporal.ChronoUnit

class InactiveStudentCleanupJob {
    static triggers = {
      cron name: 'inactiveStudentCleanupTrigger', cronExpression: '0 0 1 * * ?'
    }

    static concurrent = false

    def execute() {
        Date cutoff = Date.from(Instant.now().minus(30, ChronoUnit.DAYS))

        Student.withTransaction {
            List<Student> inactive = Student.executeQuery('''
                SELECT s FROM Student s
                WHERE NOT EXISTS (
                    SELECT e.id FROM Enrollment e
                    WHERE e.student = s AND e.enrolledAt >= :cutoff
                )
            ''', [cutoff: cutoff])

            log.info "Found ${inactive.size()} students with no enrollments since ${cutoff}"
            inactive.each { s ->
                log.info "Inactive: id=${s.id}, name=${s.name}"
            }
        }
    }
}
