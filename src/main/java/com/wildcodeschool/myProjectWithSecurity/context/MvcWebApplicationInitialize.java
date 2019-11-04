/**
 * 
 *
 */
package com.wildcodeschool.myProjectWithSecurity.context;

import com.wildcodeschool.myProjectWithSecurity.config.MySecurityConfig;

/**
 * @author franck Desmedt github/bigmoletos
 *
 */
//that this initializer is not necessary if we're using a Spring Boot application. 
//Have a look at our article on Spring Boot security auto-configuration 
//for more details on how the security configuration is loaded in Spring Boot.
public class MvcWebApplicationInitialize {
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MySecurityConfig.class };
	}
}
