package com.bezkoder.springjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import static com.bezkoder.springjwt.controllers.TestController.allow_function;
//import static com.bezkoder.springjwt.controllers.TestController.list_normal_person;

@SpringBootApplication
public class SpringBootSecurityJwtApplication {

    public static void main(String[] args) throws FileNotFoundException {
        SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
//
//        readFile.readFile1();

    }
}

//class readFile {
//    protected static void readFile1() throws FileNotFoundException {
//        Scanner s = new Scanner(new File("file.txt"));
//        while (s.hasNext()) {
//            list_normal_person.add(s.next());
//        }
//        for (int i = 0; i < list_normal_person.size(); i++) {
//            System.out.println(list_normal_person.get(i));
//        }
//        s.close();
//    }
//}
