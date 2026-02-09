package org.sungsung.youthpolicy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("org.sungsung.youthpolicy.mapper") //
public class YouthPolicyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouthPolicyApiApplication.class, args);
	}

}
