package interceptors

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class LogInterceptorSpec extends Specification implements InterceptorUnitTest<LogInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test log interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"log")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
