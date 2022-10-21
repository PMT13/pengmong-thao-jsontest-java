package net.yorksolutions.jsontest;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/echo/**")
    public HashMap echoJson(HttpServletRequest request){
        return this.jsonTestService.echoJson(request);
    }

    @GetMapping("/md5")
    public HashMap echoJson(@RequestParam String text){
        return this.jsonTestService.md5(text);
    }

    @GetMapping("/code")
    public String code(){
        return this.jsonTestService.code();
    }

    @GetMapping("/validate")
    public HashMap jsonValidator(@RequestParam String json){
        return this.jsonTestService.jsonValidator(json);
    }

    @GetMapping("/cookie")
    public HashMap cookie(HttpServletResponse response){
        return this.jsonTestService.cookie(response);
    }
}
