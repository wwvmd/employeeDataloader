package hello.foo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ash_g on 06/03/2017.
 */
//@Component
public class FooBar {

    public static void main(String[] args) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'");

        System.out.println(dateFormatter.format(new Date()));
    }



}
