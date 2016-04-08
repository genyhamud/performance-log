/**
 * Copyright (C) 2016 Geny Isam Hamud Herrera (geny.herrera@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.genyherrera.performancelog.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.genyherrera.performancelog.annotations.PerfLog;

/**
 * This is the Aspect class
 * @author genyherrera 
 *
 */
@Aspect
public class PerformanceLogAspect {

    private static Logger logger = LoggerFactory.getLogger(PerformanceLogAspect.class);

    @Pointcut("execution(* *(..)) && @annotation(com.genyherrera.performancelog.annotations.PerfLog)")
    public void performanceMethod(){}
   
    @Before("performanceMethod()")
    public void before(JoinPoint joinpoint) {
        PerfLog perfLog = retrievePerfLogAnnotation(joinpoint);
        switch (perfLog.severity()) {
		case DEBUG:
			logger.debug("--------------------------------------------------------------------------------------");
			break;
		case INFO:
			logger.info("--------------------------------------------------------------------------------------");
			break;
		case WARN:
			logger.warn("--------------------------------------------------------------------------------------");
			break;
		case ERROR:
			logger.error("--------------------------------------------------------------------------------------");
			break;
		default:
			logger.debug("--------------------------------------------------------------------------------------");
			break;
		}
    }

	@Around("performanceMethod()")
    public Object logPerformanceStats(ProceedingJoinPoint joinpoint) {
        try {
        	PerfLog perfLog = retrievePerfLogAnnotation(joinpoint);
        	Long start = null;
            Long end = null;
            Object result = null;
            
            switch (perfLog.timeStyle()) {
			case NANO_SECONDS:
				start = System.nanoTime();
	            result = joinpoint.proceed();
	            end = System.nanoTime();
				break;
			case MILI_SECONDS:
				start = System.currentTimeMillis();
	            result = joinpoint.proceed();
	            end = System.currentTimeMillis();
				break;
			case SECONDS:
				start = System.currentTimeMillis() / 1000;
	            result = joinpoint.proceed();
	            end = System.currentTimeMillis() / 1000;
				break;
			default:
				start = System.currentTimeMillis();
	            result = joinpoint.proceed();
	            end = System.currentTimeMillis();
				break;
			}
            
            switch (perfLog.severity()) {
            case DEBUG:
    			logger.debug(String.format("%s took %d " + perfLog.timeStyle().toString(), joinpoint.getSignature().toShortString(), (end - start)));
    			break;
    		case INFO:
    			logger.info(String.format("%s took %d " + perfLog.timeStyle().toString(), joinpoint.getSignature().toShortString(), (end - start)));
    			break;
    		case WARN:
    			logger.warn(String.format("%s took %d " + perfLog.timeStyle().toString(), joinpoint.getSignature().toShortString(), (end - start)));
    			break;
    		case ERROR:
    			logger.error(String.format("%s took %d " + perfLog.timeStyle().toString(), joinpoint.getSignature().toShortString(), (end - start)));
    			break;
    		default:
    			logger.debug(String.format("%s took %d " + perfLog.timeStyle().toString(), joinpoint.getSignature().toShortString(), (end - start)));
    			break;
    		}
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
   
    @After("performanceMethod()")
    public void after(JoinPoint joinpoint ) {
    	before(joinpoint);
    } 
    
    private PerfLog retrievePerfLogAnnotation(JoinPoint joinpoint) {
    	MethodSignature signature = (MethodSignature) joinpoint.getSignature();
        Method method = signature.getMethod();
        
        PerfLog perfLog = method.getAnnotation(PerfLog.class);
		return perfLog;
	}
}