/*
 * Copyright 2011 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.lag.kestrel

import org.specs.Specification
import com.twitter.logging.{Level, Logger}

/**
 * Mixin for specs-based Specifications. When mixed in, logging will be reset to log at a certain
 * level (usually FATAL) during tests. The log level can be increased by setting the environment
 * variable `log` to a different level.
 */
trait TestLogging { self: Specification =>
  val logLevel = Logger.levelNames(Option[String](System.getenv("log")).getOrElse("FATAL").toUpperCase)

  new SpecContext {
    beforeSpec {
      Logger.get("").setLevel(logLevel)
    }
  }
}
