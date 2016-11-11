package io.github.vdubois.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vdubois on 08/11/16.
 */
@RestController
public class RecommendationsRestController {

    @HystrixCommand(fallbackMethod = "defaultMethod")
    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello";
    }

    public String defaultMethod() {
        return "Erreur";
    }
}
