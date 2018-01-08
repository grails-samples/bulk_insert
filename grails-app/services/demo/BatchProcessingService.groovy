package demo

import groovy.transform.CompileStatic
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction

@CompileStatic
class BatchProcessingService implements BulkInsert {

    SessionFactory sessionFactory

    @Override
    void save(int numberOfObjects, boolean flush, boolean validate) {
        Session session = sessionFactory.openSession()
        Transaction tx = session.beginTransaction()
        (1..numberOfObjects).each { counter ->
            Person person = PersonFactory.create()
            person.save(flush: flush, validate: false)
            if ( counter % 100 == 0 ) {
                //clear session and save records after every 100 records
                session.flush()
                session.clear()
            }
        }
        tx.commit()
        session.close()
    }
}