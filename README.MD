# Performance Log #

:chart_with_downwards_trend: Annotation API for measuring performance (How log it takes for run).

[![Apache License](http://img.shields.io/badge/license-ASL-blue.svg)](https://github.com/genyherrera/performance-log/blob/master/LICENSE)
[![Build Status](https://travis-ci.org/genyherrera/performance-log.svg)](https://travis-ci.org/genyherrera/performance-log)

## What is Performance Log? ##
Performance Log is a Java Annotation API that tells you how log it takes to run a method. 

You can configure the severity that you will like to print and the time response.

The annotation is called PerfLog and you just need to annotate your method and automatically the PerfLog will do the magic.

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
 