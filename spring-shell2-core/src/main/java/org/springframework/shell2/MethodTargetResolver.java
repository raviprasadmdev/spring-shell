/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.shell2;

import java.util.Map;

import org.springframework.context.ApplicationContext;

/**
 * Strategy interface for discovering commands.
 *
 * @author Eric Bottard
 */
public interface MethodTargetResolver {

	/**
	 * Return a mapping from {@literal <command keyword(s)>} to actual behavior.
	 */
	public Map<String, MethodTarget> resolve(ApplicationContext context);

}