# functional-vaadin-addons
Functional Addons for Vaadin and Vaadin ProTools


maybe modules for different ProTools (dependencies)


## ToDo

* add MicroService ( as optional )


## Framework AddOns
In the module "framework addons" are additional 
features that you could use in your daily work. 

Every Vaadin App will start with one Servlet. Here we
could init a lot of things. 

### VaadinServlet






### building a MicroService
To work easy during development, 
and later make a fat jar for easy deployments I´m introducing
the Open Source project RapidPM MicroService.
It is available under [https://github.com/JavaMicroService/rapidpm-microservice](https://github.com/JavaMicroService/rapidpm-microservice)

It is extending the Undertow with a few convenience features.
To add it to your project use the following maven dependency

```xml
    <dependency>
      <groupId>org.rapidpm.microservice</groupId>
      <artifactId>rapidpm-microservice-modules-core</artifactId>
      <version>VERSION-NUMBER</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.rapidpm.microservice</groupId>
      <artifactId>rapidpm-microservice-modules-core-testutils</artifactId>
      <version>VERSION-NUMBER</version>
      <scope>test</scope>
    </dependency>
```

If you want to use it in production as well, remove the scope - test.







## Testbench Addons
If you are working with Testbench, mostly you are running 
in a few traps again and again.

### online or offline
If you want to use TestBench, you need so called WebDriver to 
give TestBench the possibility to remote control a browser for the defined tests. 

Mostly you are able to work online. With an stable internet connection
you can use the defined profile ( vaadin_pro-tool_testbench )
Here we are using the maven plugin that will download the defined 
webdriver for you. The drivers are stored in the target folder.
To define location and driver you need, have a look at the 
file config/driver.xml. 

But, sometimes you are offline. During a flight or working in a high-sec environment.
For this you can use the local stored drivers and define them in the file 
config/testbench.properties. for every driver you have to define the key/value pair
of 
* key == name of the property , for example webdriver.chrome.driver
* value == the path where the binary is stored.

You could use the binaries you downloaded once with the plugin.
Make sure the binaries are at a place that will not cleaned during mvn clean.

### Basic Approaches for testing with TestBench
The basic idea is a running Servlet with the UI you want to test. Testbench 
will create a WebDriver and use the defined Browser to navigate to the UI.

During this the Asserts are tested one after an other.
Now we could start the Servlet and run all tests in parallel with different 
Browsers on different Operation Systems. The management of this infrastructure is huge amount of work.

But we could do a few things.

### Local development
For local development, we need a fast possibility to start a single test to see the result and work on it.
Here we have to optimize the compile, restart, browser reload cycle. 
During the time a component is developed, a reload of the class and refresh of the ui could 
do all you need. But if you are working on an application, mostly hot class reloading is
not the most efficient thing.

For the application you need for example a reset of the database, login process if not deactivated and a 
partly navigation to the point you want to test.

The scope of this module is the usage of Testbench. Here we are setting the focus on the 
variance of Browser/OS combinations.

### Local Integration Tests
For local Integration Tests we can install all browsers on the machine and define the WebDriver.
The Servlet will be started , and Testbench will run all tests against it.

For the first version this is ok and you should use this as a first test. But have in mind that 
this will lead to the need to fix the version of the browser on your machine. Or accept that the 
version is changing and use this for the first test against the newest browser version.

### remote Integration Tests
If you need to fix the version of the browser, it make life easier to use a virtual machine 
that will not change. So we a talking about immutable infrastructure.

#### we need Docker 
You can use the virtual environment of your choice. Docker itself is light and well documented.
So I am using it here. Install Docker in the way it is recommended on the orig website.

Why we need something like Docker? The answer is easy and short. In this environment 
we are installing the Selenium Hub so that we could use this for our Integration Tests locally.

The basic usage is the following:
The Servlet is running on your local machine. The Selenium Hub is running in your local Docker.
The local WebDriver is configured to use the Selenium Hub that is running in your Docker installation.

The installation of the Selenium hub is really easy.
The prepared Docker Images are from Selenium itself and maintained on a regular base.

You could have this in a few different flavours
* standalone (headles/debug)
* Grid (headless/debug)

```bash
docker pull selenium/hub
docker run -d -p 4444:4444 --name selenium-hub selenium/hub:latest
docker run -d --link selenium-hub:hub selenium/node-chrome:latest
docker run -d --link selenium-hub:hub selenium/node-firefox:latest
docker run -d --link selenium-hub:hub selenium/node-phantomjs:latest
```

After the installation is done,you could try to access the Selenium Hub via web.

```
http://localhost:4444/grid/console
```

If you are running the Docker instance on an other machine, replace the ip with the remote ip.



#### Docker for Mac
  docker run -d -v /var/run/docker.sock:/var/run/docker.sock -p 127.0.0.1:1234:1234 bobrik/socat TCP-LISTEN:1234,fork UNIX-CONNECT:/var/run/docker.sock

#### Portainer on CLI
TBD
#### Portainer and IntelliJ
TBD

### Setting up Testdriver
One word  before: The PhantomJS Driver is
promoted since a long time. But if I am informed right, 
the main maintainer of this project just stopped working for this project.

So you have to thing about other ways.


#### Testbench and SeleniumGrid
You can run a Selenium Grid on your machine, 
or provide it via Docker on a Server of your choice.





#### Testbench and JavaFX Browser

#### Testbench and Google Chrome Headless
http://www.thefriendlytester.co.uk/2017/04/new-headless-chrome-with-selenium.html

#### Testbench and Firefox
Just give it up ;-)

#### Testbench and Windows Browsers
Your Customer is using a Windows Browser, but you want to develop 
with Linux? No problem, Vagrant is your friend.

https://developer.microsoft.com/en-us/microsoft-edge/tools/vms/
Login Information (for Windows Vista, 7, 8, 10 VMs): 
***IEUser, Passw0rd! ***

## Docker and jUnit
https://github.com/testcontainers/testcontainers-java
