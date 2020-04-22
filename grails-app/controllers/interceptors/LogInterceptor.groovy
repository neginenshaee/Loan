package interceptors


class LogInterceptor {

    LogInterceptor() {
        matchAll()
    }

    boolean before() {
        log.info("INCOMING REQUEST: ${params.toString()}")
        true
    }

    boolean after() {
        log.info("OUTCOMING REQUEST: ${params.toString()}")
        true
    }

    void afterView() {
        // no-op
    }
}
