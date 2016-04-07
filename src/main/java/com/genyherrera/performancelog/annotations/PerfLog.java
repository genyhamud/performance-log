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
package com.genyherrera.performancelog.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * PerfLog interface for use annotate the methods
 * @author genyherrera
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PerfLog {

	public enum Severity {
		INFO, WARN, DEBUG, ERROR
	}

	public enum TimeStyle {
		NANO_SECONDS {
			public String toString() {
				return "ns";
			}
		},
		MILI_SECONDS {
			public String toString() {
				return "ms";
			}
		},
		SECONDS {
			public String toString() {
				return "s";
			}
		}
	}

	Severity severity() default Severity.DEBUG;
	TimeStyle timeStyle() default TimeStyle.MILI_SECONDS; 

}
