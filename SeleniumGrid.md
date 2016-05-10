=======================

**Selenium Grid Setup**

=======================

Selenium Grid is a part of the Selenium Suite that specializes on running 
multiple tests across different browsers, operating systems, and machines in parallel.

In order to run the tests on Jenkins, the automation tests needs to connect to Selenium Hub which distributes the tests across the matching nodes with selenium 
desired capabilities.

**Tools & Infrastructure**
*Selenium Grid Extras
*xRDP
*Jenkins
*Selenium Hub
*Selenium Node

***PLEASE NOTE*** Selenium Grid creates all the Json configuration so please DO NOT manually create the extra Json files. 
TYPE SUDO FOR ANY CHANGES TO SELENIUM HUB IN TERMINAL


**Selenium-HUB**
Remote Desktop to Selenium Hub
192.168.218.5 or selenium-hub 
Use Windows LDAP
Since this is a RedHat Linux box, user can connect it  via xRDP
 
Selenium Grid Extras Installed at : /opt/softwares with role of hub



URL to access : -
192.168.218.5:3000 (Selenium Grid Extras)
http://192.168.218.5:4444/ (Hub without Extra information
 
**Selenium-Node**
Remote Desktop to Selenium Node
192.168.218.6 or selenium-ie11
Use Windows LDAP
This is Windows server 2012 server 
Selenium Grid Extras Installed at : C:\softwares with role of node

URL to access : -
192.168.218.6:3000 (Selenium Grid Extras)
http://192.168.218.6:5555/wd/hub/static/resource/hub.html (Node without Extra information)

Update the Windows HOST file for Application or Environment Under Test 


**Jenkins**
Configure Test Automation Build Jobs or Properties file within Test Automation source code to point to the new Selenium Hub server NOT the node



