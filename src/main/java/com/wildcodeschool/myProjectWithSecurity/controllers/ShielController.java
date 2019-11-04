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
public class ShielController {
	@GetMapping("/")
	public String shield() {
		return "Welcome to the SHIELD";
	}

	@GetMapping("/avengers/assemble")
	public String champions() {
		return "Avengers... Assemble";
	}

	@GetMapping("/secret-bases")
	public String[] director() {
		String[] listeCampus = { "Biarritz", "Bordeaux", "La Loupe", "Lille", "Lyon", "Marseille", "Nantes", "Orléans",
				"Paris", "Reims", "Strasbourg", "Toulouse", "Tours" };
//		String result = null;
//		for (String campus : listeCampus) {
//			System.out.println(campus);
//		}																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																
		return listeCampus;
	}

}
