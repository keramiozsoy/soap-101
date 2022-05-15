# soap-101
<!-- MarkdownTOC autolink=true -->

- [Project](#project)
- [Modules](#modules)
	- [country-repo-jdk-eight](#country-repo-jdk-eight)
	- [country-repo-jdk-eleven](#country-repo-jdk-eleven)
	- [country-repo-jdk-seventeen](#country-repo-jdk-seventeen)
	- [country](#country)
- [Toolchain](#toolchain)
- [Local-Sftp](#local-sftp)
- [Resource](#resource)

<!-- /MarkdownTOC -->


# Project

I have used CountryInfoService wsld file.

- http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL

# Modules

## country-repo-jdk-eight

I have generated classes with wsimport goal of jaxws-maven-plugin in JDK 8

## country-repo-jdk-eleven 

I have generated classes with wsdl2java goal of cxf-codegen-plugin in JDK 11

## country-repo-jdk-seventeen 

I have generated classes with wsdl2java goal of cxf-codegen-plugin in JDK 17

## country

Just service

http://localhost:8080/ws/countries.wsdl

# Toolchain

~~~
	The toolchain helps to run your project with different jdks.
	
	You can find toolchain.xml which need to run different moudles.

	Toolchain.xml file have to put under ~/.m2/toolchains.xml 

	you have to install all necessary version of jdks to run this project.

~~~

you can run command where parent-pom is located in.

```bash
	mvn clean install --log-file mylog.txt
```

run specific project

```bash
	 mvn boot:run
```

~~~
	If you would like to run by intellij-community you have to open different module by different intellij or use intellij-ultimate :)
~~~

# Local-Sftp

I added Makefile which will help to you :)

# Resource

- www.soapui.org/resources/tutorials/soap-sample-project
- www.soapui.org/docs/soap-and-wsdl/

- DYNAMIC WSDL www.tutorialspoint.com/springws/springws_first_application.htm
- STATIC WSDL www.tutorialspoint.com/springws/springws_static_wsdl.htm