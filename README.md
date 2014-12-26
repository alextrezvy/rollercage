rollercage
==========

Captcha plugin for Apache Roller based on cage library

This is a captcha plugin for Apache Roller.
It was tested on Apache Roller version 5.1.1.

Dependencies:
To build this plugin you need maven. And you need to specify the path to "WEB-INF/classes" directory of Roller in "pom.xml":

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

Then run: "mvn package".

Installation:
1. Put 'roller-cage-0.1.jar' and it's dependency 'cage-1.0.jar' into 'WEB-INF/lib' folder of application. You can put it into 'roller.war' before deploying.
2. Add the following string into "roller-custom.properties":
comment.authenticator.classname=com.github.rollercage.CageCommentAuthenticator
3. Also you can change the look of CAPTCHA by adding the following string:
comment.authenticator.type=y

