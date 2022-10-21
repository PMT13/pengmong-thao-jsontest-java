package net.yorksolutions.jsontest;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.json.JSONException;

@Service
public class JsonTestService {

    String ip;

    public HashMap getIp(){
        try {
            this.ip = InetAddress.getLocalHost() + "";
            this.ip = this.ip.split("/")[1];
            HashMap map = new HashMap();
            map.put("ip",this.ip);
            return map;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return new HashMap();
    }

   // https://www.baeldung.com/spring-rest-http-headers
    public HashMap getHeaders(@RequestHeader Map<String, String> headerInfo) {
        HashMap map = new HashMap();
        headerInfo.forEach((key, value) -> {
            map.put(key, value);
        });
        return map;
    }

    public HashMap getDate(){
        HashMap map = new HashMap();

        // https://mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
        DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/uuuu");
        LocalDate localDate = LocalDate.now();

        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();

        // https://stackoverflow.com/questions/13731218/how-do-i-get-milliseconds-from-epoch-1970-01-01-in-java
        long now = Instant.now().toEpochMilli();

        map.put("date",date.format(localDate));
        map.put("milliseconds", now);
        map.put("time",time.format(localTime));
        return map;
    }

    public HashMap echoJson(HttpServletRequest request){
        HashMap map = new HashMap();
        String url = request.getServletPath();
        String[] paths = url.split("/");
        System.out.println(paths);
        for(int i = 2; i < paths.length;i += 2){
            if(i + 1 < paths.length){
                map.put(paths[i],paths[i+1]);
            }else{
                map.put(paths[i],"");
            }
        }
        return map;
    }

    //https://www.baeldung.com/java-md5
    public HashMap md5(String inputText){
        HashMap map = new HashMap();
        try {
            String text = inputText;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toLowerCase();
            map.put("original", inputText);
            map.put("md5", myHash);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return map;
    }

    public String code(){
        this.getIp();
        this.getDate();
        String alerts = "alert(\" Your IP address is: " + this.ip + "\");";
        return alerts;
    }

    public HashMap jsonValidator(String jsonString){
        HashMap map = new HashMap();
        String type = "";
        int num = 0;
        try {
            boolean isEmpty = false;
            if(jsonString.charAt(0) == '{'){
                type = "object";
                JSONObject object = new JSONObject(jsonString);
                num = object.length();
                isEmpty = object.isEmpty();
            }
            if(jsonString.charAt(0) == '['){
                type = "array";
                JSONArray array = new JSONArray(jsonString);
                num = array.length();
                isEmpty = array.isEmpty();
            }
            map.put("valid",true);
            map.put("object_or_array",type);
            map.put("size",num);
            map.put("empty",isEmpty);
        } catch (JSONException e) {
            map.put("valid",false);
            map.put("object_or_array",type);
            map.put("error",e.getMessage());
            map.put("error_info","This error came from the org.json reference parser.");
        }
        return map;
    }

    public HashMap cookie(HttpServletResponse response){
        HashMap map = new HashMap();
        Cookie cookie = new Cookie("jsontestdotcom", "" + Instant.now().toEpochMilli());
        response.addCookie(cookie);
        map.put("cookie_status", "Cookie set with name jsontestdotcom");
        return map;
    }
}
