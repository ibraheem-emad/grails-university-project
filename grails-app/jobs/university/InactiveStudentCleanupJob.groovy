package university

import java.time.Instant
import java.time.temporal.ChronoUnit
import grails.core.GrailsApplication

class InactiveStudentCleanupJob {
    GrailsApplication grailsApplication
    static triggers = {
      cron name: 'inactiveStudentCleanupTrigger', cronExpression: '0 * * * * ?' //'0 * * * * ?' run every minute, '0 0 1 * * ?' correct value
    }

    static concurrent = false

    def execute() {
        boolean dryRun = grailsApplication.config.getProperty('university.cleanup.dryRun', Boolean, true)
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
            if(dryRun) {
                log.info "[DRY RUN] Would delete: id=${s.id}, name=${s.name}"
            }else {
                Enrollment.executeUpdate('delete from Enrollment e where e.student = :s', [s: s])
                s.delete()
                log.info "Deleted: id=${s.id}, name=${s.name}"
            }
            }
        }
    }
}
