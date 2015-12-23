This is java implementation using JAX-RS -Jersey implementation.

The generated war from the latest code in the repository is also checked in , If you have Tomcat installed , you can download the war and go to Step 9.

or 

You can create the War file by following the steps.


Install Eclipse Java IDE for Java EE development like http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplerr

1) Create a project -File => New => Dynamic Web Project- Name it as you want or EventRestService, defaults with runtime Apache tomcat 7

2) if you don’t see web.xml (deployment descriptor) under \WebContent\WEB-INF\ then follow  go to the project –> RightClick –> Java EE Tools –> Generate Deployment Descriptor Stub.

3)Convert Project to Maven Project to add required .jar files as dependencies.

    Right click on project -> Configure -> Convert to Maven Project.

4) Replace the Pom.xml content with the projects/EventRestservices/pom.xml content.

5) Replace the web.cml with projects/EventRestservices/WebContent/WEB-INF/web.xml

6) Copy the source folder projects/EventRestservices/src to the project.

7)clean eclipse workspace and build project.

    Project -> Right click - Run As -> Maven Build (option 5) - > " clean install " - > Run

    This generates EventService.war under target folder beneath your project in your workspace.

8) Deploy the war file in the Tomcat server. you can deploy through Eclipse or simply place the war under webapps folder(you would find in Tomcat installation directory).

9) Start the tomcat server either through eclipse or invoking the startup script in the Tomcat installation directory.


YOu can test the functionalities through CURL 
( I tried to remove the context "/EventService" through Maven plugin.. wasn't working ) but can do that changing the <Context > in the conf/Server.xml as 
<Context docBase= \\your webapp path" path="" reloadable="false"/>


curl -X POST "http://localhost:8080/EventService/events" -H "Content-Type: application/json" -d '{"data":"NAME is now at LATITUDE/LONGITUDE"}'
curl "http://localhost:8080/EventService/events"
curl -X DELETE "http://localhost:8080/EventService/events"
curl "http://localhost:8080/EventService/events/2"
curl -X PUT "http://localhost:8080/EventService/events/2" -H "Content-Type: application/json" -d '{"data":"NAME is now at LATITUDE/LONGITUDE"}'
curl -X DELETE "http://localhost:8080/EventService/events/2"
