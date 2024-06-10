@all
Feature: WebDriver University - Contacto
  Scenario: Ingresando datos validos de contacto seteados
    Given Se ingresa en la pagina Web Driver Test "https://webdriveruniversity.com/"
    When  Entro en Contact US
    And Agrego primer nombre "testQA"
    And Agrego primer apellido "testQA"
    And Agrego email "test@test.com"
    And Agrego comentario "testQACucumber"
    And Cliqueo en Submit
    Then Se agregaron los datos correctamente