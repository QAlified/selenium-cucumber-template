@LoginValidation
Feature: WebDriver University - Login
  Background:
    Given Se ingresa en la pagina Web Driver Test Login "https://webdriveruniversity.com/"
    When  Entro en Login Portal
  Scenario Outline: Ingreso a la pagina y loguea
    And Agrego user <user>
    And Agrego password <pass>
    Then Cliqueo en Login y verifico <validation>
    Examples:
      | user | pass |validation |
      |"webdriver"| "webdriver123"|"validation succedeed"|
      |"test"| "test123"|"validation failed"|