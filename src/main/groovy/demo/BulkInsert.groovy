package demo

import groovy.transform.CompileStatic

@CompileStatic
interface BulkInsert {
    void save(int numberOfObjects, boolean flush, boolean validate)
}
