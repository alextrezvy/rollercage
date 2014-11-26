rollercage
==========

Captcha plugin for Apache Roller based on cage library

This is a captcha plugin for Apache Roller.
It was tested on Apache Roller version 5.1.1.

Dependencies:
To build this plugin you need to specify the path to "WEB-INF/classes" directory of Roller in "pom.xml":

        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.2</version>
        <configuration>
	  <compilerArgs>
	    <arg>-classpath</arg>
	    <arg>${maven.compile.classpath}:YOUR_PATH_HERE</arg>
	 </compilerArgs>
        </configuration>
      </plugin>

Installation:
1. Put the jar with the plugin into the classpath. It can be either "lib" directory of Roller's WAR or Apache Tomcat's "lib" directory 
or it can be the directory pointed by the property "common.loader" of the "catalina.properties" (config file of Tomcat).
2. Add the following string into "roller-custom.properties":
comment.authenticator.classname=com.github.rollercage.CageCommentAuthenticator
3. Also you can change the look of CAPTCHA by adding the following string:
comment.authenticator.type=y
