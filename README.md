Spring Security

Aujourd'hui, tu vas découvrir un sous-projet de Spring : Spring Security ! Ce framework va te permettre d'intégrer des mécanismes d'authentification et d'autorisation dans tes projets.
Objectifs
•
S'initier au fonctionnement de Spring Security
•
Configurer le framework
•
Gérer les utilisateurs et leurs autorisations
Challenge ️️
Pour valider cette quête tu devras résoudre le challenge: Stratégie, Habileté, Intervention, Exécution et Logistique Défensive. Le principe du challenge est détaillé dans l’onglet Challenge.
voir le challenge
 
Spring Initializr
Ce n'est pas ton premier rodéo, tu sais comment tout ça va commencer : rends-toi sur https://start.spring.io/


Crée un nouveau projet avec les informations suivantes :
•
"Group" : com.wildcodeschool
•
"Artefact" : myProjectWithSecurity
•
"Dependencies" : Web et Security
Télécharge le zip généré, extrais-le et importe-le dans ton IDE.
Afin d'avoir au moins une route testable, tu peux mettre en place un point d'entrée avec le contrôleur suivant dans le package com.wildcodeschool.myProjectWithSecurity.controllers :
package com.wildcodeschool.myProjectWithSecurity.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {


    @GetMapping("/")
    public String hello() {
        return "Hello World!!!";
    }
}


Java
Lance maintenant ton projet avec la commande mvn spring-boot:run, et ouvre l'url suivante dans ton navigateur :
http://localhost:8080/
Là, ce n'est pas comme d'habitude... Au lieu de voir le message "Hello World!!!", tu devrais voir maintenant un formulaire te demandant un nom d'utilisateur et un mot de passe. Tu noteras en passant que tu as été redirigé vers cette url :
http://localhost:8080/login
Fichtre, la porte est verrouillée... Si tu regardes attentivement le flot de texte dans ta console, tu devrais trouver une ligne qui ressemble à celle-ci :
Using generated security password: #######-####-####-####-############


Shell
Essaie de rentrer ce mot de passe généré avec le nom d'utilisateur user dans le formulaire de connexion : cela t'ouvre la porte et te ramène sur la page d'accueil. Par défaut, Spring Security te crée donc un utilisateur user avec un mot de passe généré au lancement de l'application.
Ouvre maintenant l'url suivante pour te déconnecter :
http://localhost:8080/logout
C'est bien gentil tout ça, mais quand je veux développer une API, je ne peux pas passer par un formulaire de connexion...
C'est juste, mais comme la vie est bien faite, tu peux intégrer les identifiants directement dans une requête : tu vas le vérifier avec Postman. D'abord, lance une requête GET sur http://localhost:8080/ : tu auras comme réponse une erreur 401 -- accès non autorisé.
Va maintenant dans l'onglet "Authorization", choisis comme type "Basic Auth" et renseigne les champs "Username" et "Password".


Sauf que, pour l'instant, tu as un nom d'utilisateur et un mot de passe imposés par Spring : tu vas devoir bidouiller un peu la configuration pour aller plus loin.
 
Configurer Spring Security
Pour configurer Spring Security selon tes envies, tu as besoin d'un objet de configuration qui active la sécurité. Bref, une classe avec l'annotation  @EnableWebSecurity : cet objet va décrire la configuration pour la sécurité de ton projet.
Pour démarrer, tu peux utiliser une configuration "clé en main" en partant de la classe WebSecurityConfigurerAdapter. Tu vas l'étendre afin d'en surcharger les méthodes -- avec l'annotation @Override -- pour te faire une configuration sur mesure dans la suite de cette quête. Crée un package com.wildcodeschool.myProjectWithSecurity.config dans ton projet, et déclare ton extension de WebSecurityConfigurerAdapter :
package com.wildcodeschool.myProjectWithSecurity.config;


import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


}


Java
La classe WebSecurityConfigurerAdapter contient plusieurs méthodes appelées configure que tu peux redéfinir -- jette un œil à la documentation dans les ressources. Commençons avec celle qui prend en paramètre une instance de type HttpSecurity. En fouillant dans la documentation officielle -- regarde dans les ressources, tu trouveras son implémentation par défaut :
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .and()
        .httpBasic();
}


Java
L'instance http te permet d'ajouter des blocs de paramètres à ta configuration, chaque bloc suivant étant ajouté avec la méthode and(). L'implémentation par défaut est donc à lire comme ceci :
•
Sur la configuration pour les requêtes http, j'ajoute :
un bloc authorizeRequests() pour activer la sécurité ; je précise que :pour anyRequest() -- n'importe quelle requête,je veux que l'utilisateur soit authenticated() -- authentifié,
•
and() -- un autre bloc :
pour préciser que l'authentification passe par un formLogin() -- un formulaire de connexion,
•
and() -- un autre bloc :
pour configurer httpBasic() -- l'authentification basique HTTP.
Un test que tu peux faire pour rendre cela plus concret est d'enlever tout le bloc authorizeRequests :
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .formLogin()
            .and()
        .httpBasic();
}


Java
Relance ton application, et ouvre d'abord l'url :
http://localhost:8080/logout
Maintenant que tu es sûr d'être déconnecté, ouvre l'url :
http://localhost:8080/
Tu peux y accéder sans connexion parce que tu as enlevé un bloc de la configuration par défaut . C'était le bloc qui précisait que l'accès sur chaque requête devait être authentifié :
authorizeRequests().anyRequest().authenticated()


Java
Tu peux faire des tests en enlevant/rajoutant des blocs, et chercher d'autres options. Rétablis la configuration par défaut pour la suite :
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .and()
        .httpBasic();
}


Java
 
Gérer les utilisateurs
Pour l'instant, tu es bloqué avec ton utilisateur user et son mot de passe généré. Il est temps de redéfinir une autre méthode configure -- mais attends la suite avant de la tester :
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("password").roles("");
}


Java
Cette déclaration permet de déclarer des utilisateurs "en mémoire" -- c'est-à-dire sans base de données. Avec ce code, tu es en train d'associer l'identifiant user au mot de passe password. Ce n'est pas un mot de passe très recommandé, mais ça nous ira bien pour tester. Tu peux relancer ton application, et aller au formulaire de login... mais password ne marchera pas !
Avec Spring 4, il était possible de stocker des mots de passe en clair. Avec Spring 5, les choses sont un peu plus subtiles : un décodeur par défaut est utilisé. Avant de tester, complète la méthode en utilisant un encodeur par défaut :
PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
auth.inMemoryAuthentication().withUser("user").password(encoder.encode("password")).roles("");


Java
Note que tu es obligé d'associer des rôles à ton utilisateur : pour l'instant, une chaîne de caractères vide fera l'affaire.
Relance, va à la page de login, et connecte-toi :)
Default Password Encoder in Spring Security 5
https://www.baeldung.com/spring-security-5-default-password-encoder
HttpSecurity
https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html#jc-httpsecurity
WebSecurityConfigurerAdapter
https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.html
 
Gérer les autorisations
Que dirais-tu de démarrer un petit back office ? Rajoute une route à ton contrôleur :
@GetMapping("/admin")
public String admin() {
    return "Hello Admin!!!";
}


Java
Le back office ne devrait être accessible qu'à un administrateur. Ajoute-le dans les utilisateurs déclarés :
auth.inMemoryAuthentication()
    .withUser("user")
        .password(encoder.encode("password"))
        .roles("")
        .and()
    .withUser("admin")
        .password(encoder.encode("12345678"))
        .roles("ADMIN");


Java
Reste maintenant à adapter la gestion des requêtes HTTP -- ton configure(HttpSecurity http). Je pourrais t'expliquer comment faire, mais, puisque tu es arrivé aussi loin, j'ai mieux à te proposer. Tu trouveras ci-dessous une ressource qui te permettra de finir seul cette partie. Vois cela comme un test avant de te recruter pour une mission un peu "sensible". Je t'en dirai plus sur cette mission dans la partie challenge.
Authorize Requests
https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html#jc-authorize-requests
Challenge ️️
Pour valider cette quête tu devras résoudre le challenge: Stratégie, Habileté, Intervention, Exécution et Logistique Défensive. Le principe du challenge est détaillé dans l’onglet Challenge.
voir le challenge


Challenge 
Stratégie, Habileté, Intervention, Exécution et Logistique Défensive
 
Énoncé :



Bravo, je savais que tu étais la personne de la situation. Je peux maintenant te parler de ta mission. Le SHIELD a découvert des accès mal sécurisés sur sa plateforme web. Le directeur veut refondre tout le projet, et m'a demandé de confier la réalisation d'un POC à quelqu'un de confiance. Tu vas créer des routes selon les critères suivants :
•
/ : la route est accessible par tout le monde, sans authentification, et retourne le message "Welcome to the SHIELD".
•
/avengers/assemble : la route est accessible uniquement aux champions du SHIELD, et retourne le message "Avengers... Assemble".
•
/secret-bases : cette page n'est accessible que par le directeur du SHIELD, et retourne la liste de toutes les villes où il y a une Wild Code School (regarde cette page pour t'aider).
Ton projet doit créer des identifiants pour les utilisateurs suivants :
Nom Steve Nick
Mot de passe  motdepasse
Rôle
CHAMPION
flerken
DIRECTOR

































