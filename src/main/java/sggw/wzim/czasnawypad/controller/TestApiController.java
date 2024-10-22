package sggw.wzim.czasnawypad.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestApiController {

    @RequestMapping("/test")
    public String helloWorld() {
        return "Hello World from Api Controller! \uD83D\uDC31";
    }

}