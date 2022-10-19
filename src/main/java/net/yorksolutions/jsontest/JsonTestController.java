package net.yorksolutions.jsontest;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/echo/{param1}/{param2}")
    public HashMap echoJson(@PathVariable String param1,@PathVariable String param2){
        return this.jsonTestService.echoJson(param1,param2);
    }

    @GetMapping("/md5/{text}")
    public HashMap echoJson(@PathVariable String text){
        return this.jsonTestService.md5(text);
    }

    @GetMapping("/code")
    public String code(){
        return this.jsonTestService.code();
    }
}
