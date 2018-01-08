package org.grails.utils

trait Benchmark {
    long benchmark(Closure cls) {
        long start = System.currentTimeMillis()
        cls.call()
        long now = System.currentTimeMillis()
        now - start
    }
}