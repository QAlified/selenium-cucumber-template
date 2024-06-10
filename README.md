# Selenium con Cucumber BDD -  Template

El proyecto consiste en un framework de automatización de pruebas basado en Selenium WebDriver con Cucumber BDD para la especificacion de escenarios de prueba,  que sirve como punto de partida en cualquier proyecto de automatización de pruebas web UI.

Este framework provee todas las funcionalidades necesarias para abordar la automatización de pruebas web con Selenium y Cucumber de una forma estándar, reduciendo tiempos de configuración y construcción, minimizando la curva de aprendizaje y el nivel técnico requerido para su uso.

A su vez, encapsula lógica en común y brinda un marco de trabajo fácilmente configurable y extendible según la necesidad de cada proyecto.

## Comenzando 🚀

Estas instrucciones te permitirán obtener una copia local del proyecto en funcionamiento para propósitos de desarrollo y pruebas.

### Pre-requisitos 📋

Requisitos necesarios para el correcto funcionamiento del template y cómo instalarlos.

* Java 
* Maven (Opcional ya que se puede importar en el IDE)
* IDE de preferencia como por ejemplo, [Eclipse IDE](https://www.eclipse.org/) o [IntelliJ IDEA] (https://www.jetbrains.com/).
* [TestNG](https://testng.org/doc/download.html), disponible también desde el  _Marketplace_  de Eclipse.


### Instalación 🔧

A continuación se describen los pasos para descargar e instalar el template en tu IDE de preferencia.

1. Descargar una copia o clonar el código del repositorio desde GitHub.
2. Importar el proyecto en el IDE, recordar hacerlo como proyecto de tipo “Maven project”.
3. Al finalizar la importación, el proyecta está listo para usarse.

## Diseño de pruebas ⚙️

A continuación de presenta información y ejemplos que detallan cómo comenzar a crear las pruebas automatizadas usando las clases del template.

Si ya estás familiarizad@ con los conceptos de Java, Selenium, Cucumber y POO, te invitamos a analizar y comprender la estructura de clases y packetes del template.


### Primeros pasos

#### Creando la Feature.
Primero agregamos la Feature que contendra los escenarios, dentro de  "src > test > java > features".
Dentro del template se indica un ejemplo de como se genera tanto la Feature como los Scenarios dentro de ella, con sus respectivas acciones (Given,When,Then,And).
Cada Feature puede tener una o mas tags para ser identificadas por el Runner a la hora de correr las pruebas. Por ejemplo _@example_.

#### Generando Steps
Luego se deben generar los steps vinculados a cada Scenario dentro de "src > test > java > stepsDefinitions".
Comenzamos creando nuestra primer clase de pruebas en dicha carpeta, utilizando la opción  New > Class_. Esta clase de pruebas contendrá los distintos steps que se corresponden con la feature creada anteriormente.

 
```java
public class exampleSteps {

}

```

Para crear nuestra primer prueba automatizada, basta con crear un método público dentro de la clase del step con la anotación de la accion, por ejemplo  _@Given_ .


```java
public class exampleSteps {

	@Given("Texto que identifica la accion")
	public void accionDelGiven {

		//Acá ocurrirá la magia

	}

	}
```

Utilizando el objeto  _WebAutomator_  que se utiliza en cada Step podremos acceder a las principales funcionalidades de navegación del template.
En el siguiente ejemplo se muestra el código para acceder a una determinada dirección URL, navegar hacia atrás y adelante, refrescar la página y cerrar el navegador.

```java

import org.testng.annotations.Test;

public class exampleSteps {

	@Given("Texto que identifica la accion")
	public void accionDelGiven {
		String testUrl = "https://qalified.com/";
		automator.goTo(testUrl); // Permite navegar a una URL
		automator.back(); // Permite navegar hacia atras
		automator.forward(); // Permite navegar hacia adelante
		automator.refresh(); // Permite recargar la página
		automator.closeBrowser(); // Permite cerrar el navegador
	}

	}
```

### Interactuando con elementos de la web

Utilizando la clase  _WebAutomator_  y  _UIElement_  es posible simular interacciones con los distintos elementos de la interfaz gráfica Web. Para ello, primero se debe crear el objeto UIElement utilizando el método   _find_ de  _WebAutomator_ . 

```java
	@Given("Texto que identifica la accion")
	public void accionDelGiven {
		String testUrl = "https://qalified.com/";
		automator.goTo(testUrl);
		automator.maximizeWindows();
		
		UIElement link_contacto = automator.find(By.linkText("CONTACTO"));
		link_contacto.click();

		UIElement input_nombre = automator.find(By.name("txtName"));
		input_nombre.setText("James Bond");

	}

```

Para conocer más en detalle sobre las clases  _WebAutomator_  y  _UIElement_  y sus métodos, visitar el apartado **Especificación**

### Verificaciones  _(Assertions)_ 

Para incorporar las verificaciones, es necesario utilizar los metodos provistos por  _TestNG_ , importando el package  _org.testng.Assert.*_   a la clase de pruebas y utilizando los distintos metodos  _assert..._   que provee. 

En el siguiente ejemplo se utiliza el método  _assertEquals()_  para verificar el texto ingresado en el campo de nombre

```java
	@Given("Texto que identifica la accion")
    public void accionDelGiven {
        String testUrl = "https://qalified.com/";
        automator.goTo(testUrl);
        automator.maximizeWindows();
        
        UIElement link_contacto = automator.find(By.linkText("CONTACTO"));
        link_contacto.click();
        
        UIElement input_nombre = automator.find(By.name("txtName"));
        input_nombre.setText("James Bond");
        
        assertEquals(input_nombre.getValue(), "James Bond", "El texto ingresado no coincide!");

	}
```

### Ejecutar nuestra prueba

#### Runners
Los runners son los encargados de seleccionar las features (o escenarios) a ejecutar. En el template se agrega un Runner de ejemplo que se encarga 
de ejecutar las features que se corresponden con ciertos tags.
La ubicacion de los Runners es en "src > test > java > runners"

Cada runner debe ser llamado desde testng.xml.


```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Suite Cucumber All Feature">
    <test name="Test Cucumber All Feature">
        <classes>
            <class name="runners.exampleRunner"/>
        </classes>
    </test>
</suite>

```

### Especificación

**DriverFactory**
La DriverFactory es quien brinda instancias del WebAutomator cuando se ejecutan las pruebas. La misma se encarga de utilizar la misma instancia durante todo el ciclo de tests.

**WebAutomator**
La clase  _WebAutomator_  encapsula y extiende toda la funcionalidad de la clase  _WebDriver_  de Selenium y brinda mecanismos más eficientes para la interacción con el navegador y la web. A su vez, resuelve automaticamente los tiempos de espera aplicando  _ExplicitWaits_  al momento de interactuar con los elementos HTML.

| Método        | Descripción           |
| ------------- |---------------|
| public WebDriver `getDriver()`      | Retorna el objeto  _WebDriver_ de Selenium |
| public void `maximizeWindows()`     | Maximiza la ventana del navegador      |
| public void `back()`                | Navega hacia atras en el navegador      |
| public void `forward()`             | Navega hacia adeante en el navegador |
| public void `refresh()`             | Refresca la página del navegador |
| public void `goTo(String url)`      | Navega hacia la url determinada |
| public void `closeBrowser()`        | Cerrar el navegador |
| public void `closeCurrentTab()`     | Cerrar la pestaña de navegación |
| public String `getCurrentUrl()`     | Retorna la URL actual de la pestaña activa |
| public UIElement `find(By by)`      | Retorna el objeto  _UIElement_  que coincide con el selector  _By_ recibido por parámetro |
| public UIElement `findChild(By parent, By child)` | Analiza el objeto _UIElement_ (parent) y retorna el primer objeto  _UIElement_ hijo que coincide con el selector _By_ (child) |
| public UIElement `waitUntilVisible(By by)` | Retorna el objeto  _UIElement_  cuando el mismo sea visible en pantalla |
| public UIElement `waitUntilClickable(By by)` | Retorna el objeto  _UIElement_  cuando el mismo sea cliqueable en pantalla |
| public void `deleteAllCookies()` | Elimina todas las cookies en la sesión del navegador |
| public Set<Cookie> `getAllCookies()` | Retorna todas las cookies del navegador |
| public Cookie `getCookie(String cookie)` | Retorna la cookie cuyo nombre coincida con el recibido por parámetro |
| public void `addCookie(Cookie cookie)` | Agrega una nueva cookie en el navegador |
| public void `takeScreenshot()` | Realiza una captura de pantalla y la almacena en formato .png |

**UIElement**
La clase  _UIElement_  sustituye a la clase  _WebElement_  de Selenium encapsula toda la funcionalidad sobre la interacción de los elementos de la UI web. 

| Método        | Descripción           |
| ------------- |---------------|
| public WebElement `getWebElement()`        | Retorna el objeto  _WebElement_ de Selenium |
| public void `setText(String text)`         | Ingresa el texto recibido por parámetros en el elemento      |
| public void `clear()`                      | Limpia el contenido del elemento                  |
| public void `clearAndSetText(String text)` | Limpia el contenido del elemento e ingresa el texto recibido por parámetro |
| public String `getLink()`                  | Retorna el valor del atributo "href" del elemento web |
| public void `submit()`                     | Confirma el envio de información |
| public void `click()`                      | Simula la acción de clic en el elemento |
| public boolean `isSelected()`              | Retorna verdadero si el elemento web está seleccionado  |
| public boolean `isDisplayed()`             | Retorna verdadero si el elemento web es visible en pantalla  |
| public boolean `isEnabled()`               | Retorna verdadero si el elemento web está habilitado    |
| public String `getText()`                  | Retorna el texto visible de un elemento web |
| public String `getValue()`                 | Retorna el valor de un elemento |
| public UIElement `findElementBy(By by)`    | Retorna un nuevo  _UIElement_  interno al elemento web y que coincida con el selector  _By_ recibido por parámetros |
| public List<UIElement> `findElementsBy(By by)` |  Retorna una lista de  _UIElement_  internos al elemento web y que coincidan con el selector  _By_ recibido por parámetros|
| public void `setTextWithActions(String text)` | Ingresa el texto recibido por parámetros en el elemento utilizando la clase _Actions_  de Selenium |
| public void `selectByValue(String value)` | Selecciona una opcion de la lista basado en el atributo _value_ de la misma  |
| public void `selectByIndex(Integer index)` | Selecciona una opcion de la lista basado en el indice numerico de la misma  |
| public void `selectByVisibleText(String text)` | Selecciona una opcion de la lista basado en el texto visible de la misma  |
| public void `moveToElement()`              | Mueve la UI web hasta hacer visible el elemento en pantalla  |

**Otras funcionalidades**
 * _VisualTesting_ : Screenshots y comparaciones visuales.
 * _AutoIT_ : Automatizacion de ventanas fuera del navegador.

## Construido con 🛠️
* [Selenium WebDriver](https://www.selenium.dev/) - El framework de automatización.
* [Cucumber](https://cucumber.io/) - Especificacion de Tests.
* [TestNG](https://testng.org/doc/) - Framework de pruebas.
* [Maven](https://maven.apache.org/) - Herramienta de construcción de proyecto y gestión de dependencias.

## Autores ✒️

* [**QAlified**](https://qalified.com/)

## Contacto 📢

info@qalified.com

---
⌨️ con ❤️ por QAlified
