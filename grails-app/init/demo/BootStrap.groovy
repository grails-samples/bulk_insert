package demo

import groovy.transform.CompileStatic
import org.grails.utils.Benchmark

@CompileStatic
class BootStrap implements  Benchmark {

    TraditionalSaveService traditionalSaveService

    CleanupGormSaveService cleanupGormSaveService

    BatchProcessingService batchProcessingService

    HibernateStatelessService hibernateStatelessService

    def init = { servletContext ->

        log.info('starting comparison')

        List<Integer> iterations = [10000, 20000, 30000, 40000, 50000, 100000]

        List<Map> attempts = [
                [processor: traditionalSaveService, flush: false, validate: true],
                [processor: traditionalSaveService, flush: false, validate: false],
                [processor: cleanupGormSaveService, flush: false, validate: true],
                [processor: cleanupGormSaveService, flush: false, validate: false],
                [processor: batchProcessingService, flush: false, validate: true],
                [processor: batchProcessingService, flush: false, validate: false],
                [processor: hibernateStatelessService, flush: false, validate: true]
        ] as List<Map>

        for ( Map m :  attempts ) {
            BulkInsert processor = m.processor as BulkInsert
            boolean flush = m.flush as Boolean
            boolean validate = m.validate as Boolean
            for ( Integer numberOfObjects : iterations ) {
                long duration = benchmark {
                    processor.save(numberOfObjects, flush, validate)
                }
                log.info("${processor.class}: $numberOfObjects flush: ${flush} validate: ${validate} => $duration")
            }
        }
    }
    def destroy = {
    }
}
