package demo

import groovy.transform.CompileStatic

@CompileStatic
class PersonFactory {

    static Person create() {
        Person person = new Person()
        person.with {
            firstName      = 'Jone'
            lastName       = 'Roy'
            address        = 'New york'
        }
        person
    }
}
