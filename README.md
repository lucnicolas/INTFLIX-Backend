# SeriesManagement

##### A Java web application inside a Tomcat server container

You can use Docker to run a Tomcat server and deploy your Java web applications. This tutorial describes how to create a simple Java web application, build a deployable web application resource (WAR) file, and then deploy it inside a Tomcat server running as a Docker container.
Before you begin, make sure that Docker integration is properly configured.

###### Create a Java web app
1. From the main menu, select File | New | Project.
2. In the New Project dialog, select Java Enterprise. For this tutorial, use Java 1.8 as the project SDKs and leave other settings by default: Maven as the build tool and JUnit as the test runner. Select Java as your project language and click Next.
<img src="https://www.jetbrains.com/help/img/idea/2020.2/rest_ws_glassfish_create_project_step_1.png"/>
3. In the Libraries and Frameworks list, select the Web Profile specification and click Next.
4. Enter a name for your project: DockerJavaWebApp. Then click Finish.
5. In the Project tool window, right-click the src/main/java directory, point to New and click Create New Servlet.
6. In the New Servlet dialog, enter the name MyServlet and click OK.
7. Open the src/main/java/MyServlet.java file and replace the default doGet() method with the following:
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");

    try (PrintWriter writer = response.getWriter()) {
        writer.println("<!DOCTYPE html><html>");
        writer.println("<head>");
        writer.println("<meta charset=\"UTF-8\" />");
        writer.println("<title>MyServlet.java:doGet(): Servlet code!</title>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println("<h1>This is a simple java servlet.</h1>");

        writer.println("</body>");
        writer.println("</html>");
    }
}
```
Add the necessary imports and libraries, if prompted to do so.
8. In the Project tool window, right-click the src/main/webapp directory, point to New and click JSP/JSPX.
9. In the Create JSP/JSPX page dialog, enter the name index and click OK.
10. Open the src/main/webapp/index.jsp file and paste the following HTML code as the entrypoint for the servlet:
```jspx
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Hello, I am a Java web app!</title>
    </head>
    <body>
        <h1>Simple Java Web App Demo</h1>
        <p>To invoke the java servlet click <a href="MyServlet">here</a></p>
    </body>
</html>
```
11. Open the src/main/webapp/WEB-INF/web.xml file and paste the following servlet configuration:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">

    <servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>MyServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/MyServlet</url-pattern>
    </servlet-mapping>

</web-app>
```
Make sure that the name of your servlet matches the specified case.
12. Open the Build menu and click Rebuild Project to make sure that the servlet is compiling correctly. The Event Log should output Build completed successfully and you should see the compiled file target/classes/MyServlet.class.

###### Build a WAR artifact
1. From the main menu, select Build | Build Artifacts.
2. In the Build Artifact dialog, select to build the DockerJavaWebApp:war artifact.
  You should see the artifact target/DockerJavaWebApp-1.0-SNAPSHOT.war.

###### Deploy your application inside a Tomcat server
1. Open the Services tool window: View | Tool Windows | Services or ⌘8. In the Docker tool window, right-click the Images node, and then click Pull image.
2. Under the configured Docker connection node, select Images and specify to pull the Tomcat server image: tomcat. Press Ctrl+Enter.
3. Right-click the tomcat:latest image and then click Create container.
4. In the Create Docker Configuration dialog, specify the following:
   - Specify the name of the configuration: TomcatConfig
   - Specify the name of the container: TomcatContainer
   - Bind the container port 8080 to the host IP 127.0.0.1 and port 8080
   - Map the WAR artifact output directory [PROJECT_PATH]/target to the Tomcat server deployment directory /usr/local/tomcat/webapps.
<img src="https://www.jetbrains.com/help/img/idea/2020.2/docker_tutorial_deploy_java_web_app_in_tomcat.png"/>
Click Run to start the container.
5. SSH into the container :
    ```
    docker exec -it TomcatContainer /bin/bash`
    cp -r webapps.dist/* webapps/
    ```
5. When the container starts, open the following address in your web browser: http://127.0.0.1:8080/DockerJavaWebApp-1.0-SNAPSHOT/
   You should see the following page:
   <img src="https://www.jetbrains.com/help/img/idea/2020.2/DockerJavaWebApp-browser.png"/>
   Click the link to run the compiled Java servlet.


