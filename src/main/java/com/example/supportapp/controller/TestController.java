/* package com.example.supportapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String testEndpoint() {
        return "Test successful";
    }
}
 */
package com.example.supportapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.OPTIONS)
    public void testCors() {
        // Método vacío para manejar el preflight request de CORS
    }
}
