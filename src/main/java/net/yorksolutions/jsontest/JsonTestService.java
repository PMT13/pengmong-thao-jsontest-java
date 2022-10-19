package net.yorksolutions.jsontest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

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

    public HashMap echoJson(String param1, String param2){
        HashMap map = new HashMap();
        map.put(param1,param2);
        map.put("key","value");
        return map;
    }
}
