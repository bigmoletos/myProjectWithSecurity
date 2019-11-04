package com.wildcodeschool.myProjectWithSecurity.context;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.wildcodeschool.myProjectWithSecurity.MyProjectWithSecurityApplication;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MyProjectWithSecurityApplication.class);
	}

}
