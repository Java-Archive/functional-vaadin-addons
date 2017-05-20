# functional-vaadin-addons
Functional Addons for Vaadin and Vaadin ProTools


maybe modules for different ProTools (dependencies)


## ToDo

* remove BrowserDriverFunctions.initSystemProperties()


## Testbench Addons
If you are working with Testbench, mostly you are running 
in a few traps again and again.

### we need Docker 

#### Docker for Mac
  docker run -d -v /var/run/docker.sock:/var/run/docker.sock -p 127.0.0.1:1234:1234 bobrik/socat TCP-LISTEN:1234,fork UNIX-CONNECT:/var/run/docker.sock



#### Portainer on CLI
#### Portainer and IntelliJ


### Setting up Testdriver
One word  before: The PhantomJS Driver is
promoted since a long time. But if I am informed right, 
the main maintainer of this project just stopped working for this project.

So you have to thing about other ways.

#### Testbench and SeleniumGrid
You can run a Selenium Grid on your machine, 
or provide it via Docker on a Server of your choice.
The prepared Docker Images are from Selenium 
itself and maintained on a regular base.

You could have this in a few different flavours
* standalone (headles/debug)
* Grid (headless/debug)




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
