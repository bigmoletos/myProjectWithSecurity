/**
 * 
 *
 */
package com.wildcodeschool.myProjectWithSecurity.context;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.wildcodeschool.myProjectWithSecurity.config.MySecurityConfig;

/**
 * @author franck Desmedt github/bigmoletos
 *
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecurityWebApplicationInitializer() {

	}

	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MySecurityConfig.class };
	}
}
