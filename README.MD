# Performance Log #

:chart_with_downwards_trend: Annotation API for measuring performance (How log it takes for run).

[![Apache License](http://img.shields.io/badge/license-ASL-blue.svg)](https://github.com/genyherrera/performance-log/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/genyherrera/performance-log.svg)](https://travis-ci.org/genyherrera/performance-log)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.genyherrera.performancelog/performance-log/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.genyherrera.performancelog/performance-log)

## What is Performance Log? ##
Performance Log is a Java Annotation API that tells you how log it takes to run a method. 

You can configure the severity (INFO, WARN, DEBUG, ERROR) that you will like to print and the time response (NANO_SECONDS, MILI_SECONDS, SECONDS). 

The annotation is called PerfLog and you just need to annotate your method, and automatically the PerfLog will do the magic.

This API use [Spring AOP](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html) and [AspectJ](http://projects.eclipse.org/projects/tools.aspectj) to intercept the methods and print the result.

## Download ##

Download [the latest JAR](https://search.maven.org/remote_content?g=com.genyherrera.performancelog&a=performance-log&v=LATEST) or grab via Maven:
```xml
<dependency>
  <groupId>com.genyherrera.performancelog</groupId>
  <artifactId>performance-log</artifactId>
  <version>1.1</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.genyherrera.performancelog:performance-log:1.1'
```

You need to add the AspectJ capability to your project and configure the AspectJ library with `Performance Log` artifact. 
To do this is very simple, just copy the plugin declaration inside your pom.xml and it's done.

```
<plugin>
	<groupId>org.codehaus.mojo</groupId>
	<artifactId>aspectj-maven-plugin</artifactId>
	<version>1.7</version>
	<dependencies>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>1.8.1</version>
		</dependency>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjtools</artifactId>
			<version>1.8.1</version>
		</dependency>
	</dependencies>
	<executions>
		<execution>
			<goals>
				<goal>compile</goal>
				<goal>test-compile</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<outxml>true</outxml>
		<aspectLibraries>
			<aspectLibrary>
				<groupId>com.genyherrera.performancelog</groupId>
				<artifactId>performance-log</artifactId>
			</aspectLibrary>
		</aspectLibraries>
		<complianceLevel>${java.version}</complianceLevel>
		<source>${java.version}</source>
		<target>${java.version}</target>
	</configuration>
</plugin>		
```

Create your log4j.properties file, here is an example:
```
# Root logger option
log4j.rootLogger=DEBUG, stdout, file

# Redirect log messages to console
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n

# Redirect log messages to a log file, support file rolling.
log4j.appender.file=org.apache.log4j.RollingFileAppender
log4j.appender.file.File=log4j-application.log
log4j.appender.file.MaxFileSize=5MB
log4j.appender.file.MaxBackupIndex=10
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n

``` 

Snapshots of the development version are available in [Sonatype's Snapshots Repository](https://oss.sonatype.org/content/repositories/snapshots/com/genyherrera/performancelog/performance-log/).

## Usage Example ##

You can use this MainTest class to see how it's look.
 
```
import com.genyherrera.performancelog.annotations.PerfLog;
import com.genyherrera.performancelog.annotations.PerfLog.Severity;
import com.genyherrera.performancelog.annotations.PerfLog.TimeStyle;

public class MainTest {

	public static void main(String[] args) {
		new MainTest().helloWorldAnnotatedINFO();
		new MainTest().helloWorldAnnotatedWARN();
		new MainTest().helloWorldAnnotatedDEBUG();
		new MainTest().helloWorldAnnotatedERROR();

		new MainTest().helloWorldAnnotatedDEFAULT();
		
		new MainTest().helloWorldAnnotatedMILI_SECONDS();
		new MainTest().helloWorldAnnotatedNANO_SECONDS();
		new MainTest().helloWorldAnnotatedSECONDS();
		
		new MainTest().helloWorldNOT();
	}
	
	@PerfLog(severity=Severity.INFO)
    public void helloWorldAnnotatedINFO() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	@PerfLog(severity=Severity.WARN)
    public void helloWorldAnnotatedWARN() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	@PerfLog(severity=Severity.DEBUG)
    public void helloWorldAnnotatedDEBUG() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
	@PerfLog(severity=Severity.ERROR)
    public void helloWorldAnnotatedERROR() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	@PerfLog
    public void helloWorldAnnotatedDEFAULT() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
	@PerfLog(timeStyle = TimeStyle.MILI_SECONDS)
    public void helloWorldAnnotatedMILI_SECONDS() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	@PerfLog(timeStyle = TimeStyle.NANO_SECONDS)
    public void helloWorldAnnotatedNANO_SECONDS() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	@PerfLog(timeStyle = TimeStyle.SECONDS)
    public void helloWorldAnnotatedSECONDS() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
    public void helloWorldNOT() {
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}

``` 
In your log you will see something like:

```
2016-04-07 16:32:54 INFO  PerformanceLogAspect:49 - --------------------------------------------------------------------------------------
2016-04-07 16:32:59 INFO  PerformanceLogAspect:99 - MainTest.helloWorldAnnotatedINFO() took 5001 ms
2016-04-07 16:32:59 INFO  PerformanceLogAspect:49 - --------------------------------------------------------------------------------------
2016-04-07 16:32:59 WARN  PerformanceLogAspect:52 - --------------------------------------------------------------------------------------
2016-04-07 16:33:04 WARN  PerformanceLogAspect:102 - MainTest.helloWorldAnnotatedWARN() took 5003 ms
2016-04-07 16:33:04 WARN  PerformanceLogAspect:52 - --------------------------------------------------------------------------------------
2016-04-07 16:33:04 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:09 DEBUG PerformanceLogAspect:96 - MainTest.helloWorldAnnotatedDEBUG() took 5001 ms
2016-04-07 16:33:09 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:09 ERROR PerformanceLogAspect:55 - --------------------------------------------------------------------------------------
2016-04-07 16:33:14 ERROR PerformanceLogAspect:105 - MainTest.helloWorldAnnotatedERROR() took 5000 ms
2016-04-07 16:33:14 ERROR PerformanceLogAspect:55 - --------------------------------------------------------------------------------------
2016-04-07 16:33:14 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:19 DEBUG PerformanceLogAspect:96 - MainTest.helloWorldAnnotatedDEFAULT() took 5001 ms
2016-04-07 16:33:19 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:19 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:24 DEBUG PerformanceLogAspect:96 - MainTest.helloWorldAnnotatedMILI_SECONDS() took 5000 ms
2016-04-07 16:33:24 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:24 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:29 DEBUG PerformanceLogAspect:96 - MainTest.helloWorldAnnotatedNANO_SECONDS() took 4999692096 ns
2016-04-07 16:33:29 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:29 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------
2016-04-07 16:33:34 DEBUG PerformanceLogAspect:96 - MainTest.helloWorldAnnotatedSECONDS() took 5 s
2016-04-07 16:33:34 DEBUG PerformanceLogAspect:46 - --------------------------------------------------------------------------------------


``` 
 