# Cucumber Appium Engine 
Moteur de test mobile développé en Java, et basé sur Cucumber & Appium

Cucumber Appium Engine est une librairie permettant d'effectuer des tests mobiles automatisés, cross platform (Android/iOS) d'une manière très simple.
Elle permet aux testeurs d'utiliser le [langage gherkin](https://cucumber.io/docs/gherkin/reference/) pour créer et maintenir les tests.

Voici un exemple de test simple avec cette librairie :
```java
  Scenario: Parcours Web
    Given Je lance l'application "CHROME" sur "S8"
    When Je lance url "https://www.google.fr"
    Then la page "https://www.google.fr" s'affiche
```

## Outillage

### Environnement de développement
L'IDE officiel PF est STS (Spring Tool Suite), une version d'Eclipse agrémentée de bibliothèques & dépendances déjà intégrées. <br/>Suivre le billet Confluence de TS pour son installation ==> [Installation Environnement Développement](https://confluence.group.echonet/display/AP11236TS/PF+Dev+-+Java+developer+environment+setup)  

### Ferme de périphériques mobiles
Le moteur utilise actuellement les ressources présentes sur notre infra : **UFT Mobile Center**. Les périphériques sont accessibles, depuis le moteur appium, mais aussi par interface web, afin de réaliser des tests manuels, ou surtout, suivre la déroulement du test pendant son exécution ==> [UFT Mobile Center](https://uftm2021.group.echonet/integration/#/login)
<br/>**Pour toute demande d'accès, contacter Emmanuel Badiola du COE Testing** [Mail to Emmanuel Badiola](mailto:emmanuel.badiola@bnpparibas-pf.com) 

### Inspecteur d'élément
Afin de capturer les locator des differents objets avec lesquels on va interagir tout au long du test, contrairement aux tests Selenium, qui profitent d'une extension de navigateur, "Selenium IDE", pour réaliser ces travaux, nous allons ici utiliser une application desktop, Appium Inspector, disponible sur le digital working store ==> [Appium Inspector](softwarecenter:SoftwareID=ScopeId_3929D9DE-5EA1-4D0B-8B94-A03784F1ED91/Application_2830378a-0ae0-4a94-88b7-251d09e07c9d)

## Démarrage rapide

Pour commencer à tester, utiliser le [PROJET QUICK START](https://gitlab-dogen.group.echonet/market-place/ap27627/outils-it/pf-france-testing/automation-templates/cucumber-appium-quickstart) et suivre les étapes dans le readme.
## Utilisation
### Bases
Un scénario est défini de cette manière :
- 1. Choisir une application (déclarée dans le moteur), un périphérique mobile, ou un OS (ex: Android, iOS)
- 2. Accomplir un 1er ensemble d'actions (ex: Connexion)
- 3. Accomplir un 2nd ensemble d'actions (ex: remplissage formulaire)
- 4. Accomplir un Xème ensemble d'actions
- 5. Faire un vérification finale sur un élément attestant de la réussite du Parcours

### Fonctionnalités moteur
Vous retrouverez les différentes fonctionnalités (phrases gherkin, actions, méthodes java, etc..) du moteur, dans le fichier excel suivant :

WIP

### appiumengine.properties
Un des 4 types de fichiers principaux, est le fichier **src/test/resources/appiumengine.properties**.
On y retrouve :
    - Les identifiants (ou credentials) de connexion à la ferme de périphériques mobiles (actuellement Mobile Center)
    - Les noms de package de l'application testée, pour iOS et Android
    - Adresse et port du serveur proxy

### [ApplicationPage].json
Le 2ème type de fichiers de configuration important, est le fichier .json, placé dans **src/test/resources/LocatorByJSON/[ApplicationPage].json**.
Ces fichiers, correspondant en général à une page de l'application testée, contiennent les "locator". Ce sont les critères d'identification d'un élément de la page, tel qu'un champ texte, un bouton, ou autre... Il y a plusieurs façons de définir les locator (par exemple xpath, id, nom, classe CSS, etc...)
Ces fichiers JSON contiendront, pour un même élément, les méthodes d'identification d'un objet, pour Android et iOS, ce qui permettra à partir d'un même script de test, de le faire fonctionner sur les 2 systèmes.

Ces fichiers doivent avoir le format suivant :
```json
{
  "Apport" : {
		"android" : "text=Apport",
		"ios" : "name=Apport"
	},

  "Lancer la simulation" : {
		"android" : "id=com.bnpp.pf.fr.tarifeur.dev:id/submitBtn",
		"ios" : "name=Apport"
	}
}
```
On retrouve le nom de l'objet (ex: "Apport"), le système utilisé (ex: "android"), le type de locator (ex: "text"), et enfin le locator (ex: "Apport"). Pour trouver ces locator, on utilise l'application "Appium Inspector", disponible sur le Digital Working Store :
[Appium Inspector](softwarecenter:SoftwareID=ScopeId_3929D9DE-5EA1-4D0B-8B94-A03784F1ED91/Application_2830378a-0ae0-4a94-88b7-251d09e07c9d)

### CustomStepDefinitions.java
Dans ce 3ème fichier, nous retrouverons des méthodes Java spécifiques à l'application testée. Elles seront à la charge du testeur. Le moteur intègre déjà la majorité des méthodes les plus utilisées, correspondant aux actions les plus fréquentes (appui sur un bouton, capture d'une zone de texte, attente de l'apparition d'un élément, insertion de texte dans un champs, prise d'un screenshot, etc...). Toutes ces méthodes sont comprises dans le moteur, et ne peuvent être modifiées par le testeur utilisateur. Cependant, grâce au fichier **CustomStepDefinitions.java**, le testeur peut ajouter de nouvelles actions/méthodes en Java.
Exemple :
```java
    @When("J accede aux informations du credit")
    public void infoCredit() throws IOException, InterruptedException {
        engine.i_wait_seconds(15);
        engine.deviceUtils.swipeDown();
        engine.the_element_is_present("infoCredit");
        engine.i_click_on_the_element("infoCredit");
        engine.TakeScreenshot("InfoCredit");
        engine.i_click_on_the_element("fermer");
        engine.the_element_is_present("infoCredit");
    }
```
Nous avons ici un exemple de méthode java **infoCredit()**, déclarée dans le fichier **CustomStepDefinitions.java**, qui sera accessible par la phrase Gherkin **When J accede aux informations du credit** depuis le fichier **test.feature**

### test.feature
C'est ici que l'on retrouve, les étapes de tests énoncées en phrases Gherkin
Exemple :
```java
  Scenario: Parcours Web
    Given Je lance l'application "CHROME" sur "S8"
    When Je lance url "https://www.google.fr"
    Then la page "https://www.google.fr" s'affiche
```
On y retrouve le nom du scénario de test (Scenario: xxx), l'initialisation (Given xxx), les différentes étapes et actions du test (When xxx), et enfin la vérification finale (Then xxx). Ces phrases Gherkin sont soit, déjà incluses dans le moteur, soit, comme on l'a vu plus haut, reliées à des custom steps déclarées par le testeur dans le fichier **CustomStepDefinitions.java**.
<br/>
## Liste des méthodes Java
### Generic
| Fonction | Description |
| --- | --- |
|TakeScreenshot(String filename)|**capture d'écran**<br/>**1 paramètre** : le nom de la capture d'écran |
|randomMail(String prefix, String mailbox)|**génération d'une adresse mail aléatoire**<br/>**2 paramètres** : Le préfixe de l'adresse (qui sera suivi de la date & de l'heure), partie située avant le @. Puis la boîte mail désirée (ex: yopmail.com) |
|ImportDatatable(String path)|**chargement d'un JDD** sous forme de hashmap (objet Java contenant pour chaque entrée, une clé et une valeur<br/>**1 paramètre** : chemin absolu vers le fichier excel |
|printCapabilities()|**affichage de liste des capabilities du driver chargé** (exemple de capability : type de device mobile, nom du package de l'application testée, identifiants de connexion à la ferme de mobiles, etc etc..|

### Device specific
| Méthode | Description | Android | iOS |
| --- | --- | :-: | :-:|
|swipe\[LEFT-RIGHT-UP-DOWN\]()|**faire glisser l'écran** dans le sens souhaité| X | X |
<br/>

## Liste des steps Gherkin
### Given
| Step Gherkin| Fonction Java| Description | Example |
| --- | --- | --- | ---|
|Je lance l'application **{string}** sur **{string}**|startup(String app, String device)|Prise de contrôle d'un mobile, et lancement de l'application<br/>**2 paramètres** : le nom de l'application testée, et périphérique mobile utilisé|```Je lance l'application "CHROME" sur "S8"```|

### When
| Step Gherkin| Fonction Java| Description | Example |
| --- | --- | --- | ---|
|Je remplis le champ {string} avec le texte {string}|i_fill_the_field_with(String locator, String value)|**Insertion d'une valeur** dans un champ de l'application<br/>**2 paramètres** : le locator du champ, et la valeur à insérer|```Je remplis le champ "login" avec le texte "John Doe"```|
|Je remplis le champ {string} avec le texte {string} puis appuie sur entrée|i_fill_the_field_with_then_press_enter(String locator, String value)|**Insertion d'une valeur** dans un champ de l'application et appui sur entrée<br/>**2 paramètres** : le locator du champ, et la valeur à insérer|```Je remplis le champ "login" avec le texte "John Doe" puis appuie sur entrée```|
|Je clique sur l'element {string}|i_click_on_the_element(String locator)|**tap sur un élément** de l'application<br/>**1 paramètre** : le locator de l'élément|```Je clique sur l'element "login"```|
|L'element {string} est présent|the_element_is_present(String locator)|**j'attends la présence d'un élément** à l'écran<br/>**1 paramètre** : le locator de l'élément|```L'element "login" est présent```|
|L'element {string} est présent avant {int} secondes|the_element_is_present_before_X_seconds(String locator, int waitTime)|**j'attends la présence d'un élément à l'écran pendant X secondes**<br/>**2 paramètres** : le locator de l'élément et le temps d'attente|```L'element "login" est présent avant 5 secondes```|
|J'attends {int} secondes|i_wait_X_seconds(int waitTime)|**j'attends pendant X secondes**<br/>**1 paramètre** : le temps d'attente|```J'attends 15 secondes```|
|Je tape avec les coordonnées x:{int} et y:{int}|i_click_on_the_coordinates(int x, int y)|**Je tape à l'écran avec les coordonnées x / y**<br/>**2 paramètres** : les valeurs de l'abscisse et de l'ordonnée|```Je tape avec les coordonnées x:350 et y:100```|
|La checkbox {string} est cochée|i_check_that_the_checkbox_is_checked(String locator)|Je vérifie qu'une **checkbox soit bien cochée**<br/>**1 paramètre** : le locator de la checkbox|```La checkbox "femme" est bien cochée```|
|La checkbox {string} est décochée|i_check_that_the_checkbox_is_unchecked(String locator)|Je vérifie qu'une **checkbox soit bien décochée**<br/>**1 paramètre** : le locator de la checkbox|```La checkbox "homme" est bien décochée```|

### Then
| Step Gherkin| Fonction Java| Description | Example |
| --- | --- | --- | ---|
|L'élément {string} a la valeur {string}|i_check_that_the_element_has_value(String locator, String value)|Je vérifie qu'un **élément contienne une valeur**<br/>**2 paramètres** : le locator de l'élément et sa valeur|```L'élément taux a la valeur 20%```|
