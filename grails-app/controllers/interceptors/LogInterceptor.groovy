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
        log.info("OUTGOING RESPONSE: ${params.toString()}")
        true
    }

    void afterView() {
        // no-op
    }
}
