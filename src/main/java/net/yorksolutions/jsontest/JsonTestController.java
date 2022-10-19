package net.yorksolutions.jsontest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JsonTestController {

    private JsonTestService jsonTestService;

    public JsonTestController(JsonTestService jsonTestService){
        this.jsonTestService = jsonTestService;
    }

    @GetMapping("/ip")
    public HashMap getIp(){
        return this.jsonTestService.getIp();
    }

    @GetMapping("/headers")
    public HashMap getHeaders(@RequestHeader Map<String, String> headerInfo){
        return this.jsonTestService.getHeaders(headerInfo);
    }

    @GetMapping("/date")
    public HashMap getDate(){
        return this.jsonTestService.getDate();
    }

    @GetMapping("/{param1}/{param2}")
    public HashMap echoJson(@PathVariable String param1,@PathVariable String param2){
        return this.jsonTestService.echoJson(param1,param2);
    }
}
