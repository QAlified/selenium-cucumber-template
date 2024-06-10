@login
Feature: WebDriver University - Login
  Background:
    Given Se ingresa en la pagina Web Driver Test Login "https://webdriveruniversity.com/"
    When  Entro en Login Portal

  Scenario: Ingreso a la pagina y loguea
    And Agrego user "webdriver"
    And Agrego password "webdriver123"
    Then Cliqueo en Login y verifico que entra

  Scenario: Ingreso a la pagina y no loguea
    And Agrego user "testQA"
    And Agrego password "testQA"
    Then Cliqueo en Login y verifico que no entra
