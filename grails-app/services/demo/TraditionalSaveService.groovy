package demo

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@CompileStatic
class TraditionalSaveService implements BulkInsert {

    @Override
    @Transactional
    void save(int numberOfObjects, boolean flush, boolean validate) {
        (1..numberOfObjects).each { counter ->
            Person person = PersonFactory.create()
            person.save(flush: flush, validate: validate)
        }
    }
}