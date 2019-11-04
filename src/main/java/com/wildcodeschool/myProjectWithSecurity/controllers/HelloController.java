/**
 * 
 *
 */
package com.wildcodeschool.myProjectWithSecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author franck Desmedt github/bigmoletos
 *
 */
@RestController
public class HelloController {
//	@GetMapping("/")
//	public String hello() {
//		return "Hello World!!!";
//	}

	@GetMapping("/admin")
	public String admin() {
		return "Hello Admin!!!";
	}
}
