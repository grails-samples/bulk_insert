package demo

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import org.hibernate.Session
import org.hibernate.SessionFactory

@CompileStatic
class CleanupGormSaveService implements BulkInsert {

    SessionFactory sessionFactory

    @Override
    @Transactional
    void save(int numberOfObjects, boolean flush, boolean validate) {
        (1..numberOfObjects).each { counter ->
            Person person = PersonFactory.create()
            person.save(flush: flush, validate: validate)
            if(counter.mod(100)==0) {
                cleanUpGorm()
            }
        }
    }

    void cleanUpGorm() {
        Session session = sessionFactory.currentSession
        session.flush()
        session.clear()
    }
}