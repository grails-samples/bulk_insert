package demo

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.StatelessSession
import org.hibernate.Transaction

@CompileStatic
class HibernateStatelessService implements BulkInsert {

    SessionFactory sessionFactory

    @Override
    @Transactional
    void save(int numberOfObjects, boolean flush, boolean validate) {
        StatelessSession session = sessionFactory.openStatelessSession()
        Transaction tx = session.beginTransaction()
        (1..numberOfObjects).each { counter ->
            Person person = PersonFactory.create()
            session.insert(person)
        }
        tx.commit()
        session.close()
    }
}