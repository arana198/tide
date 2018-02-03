package com.tide

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TideApplicationTest extends Specification {

    @Autowired
    private ApplicationContext context

    def "it should boot Spring application successfully"() {
        expect: "application context exists"
        context != null
    }
}