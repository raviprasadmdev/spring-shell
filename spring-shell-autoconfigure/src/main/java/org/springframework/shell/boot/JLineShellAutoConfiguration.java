/*
 * Copyright 2017-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.shell.boot;

import java.io.IOException;

import org.jline.reader.Parser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.ExtendedDefaultParser;
import org.springframework.shell.jline.PromptProvider;

/**
 * Shell implementation using JLine to capture input and trigger completions.
 *
 * @author Eric Bottard
 * @author Florent Biville
 */
@Configuration(proxyBeanMethods = false)
public class JLineShellAutoConfiguration {

	@Bean(destroyMethod = "close")
	public Terminal terminal() {
		try {
			return TerminalBuilder.builder().build();
		}
		catch (IOException e) {
			throw new BeanCreationException("Could not create Terminal: " + e.getMessage());
		}
	}

	@Bean
	@ConditionalOnMissingBean(PromptProvider.class)
	public PromptProvider promptProvider() {
		return () -> new AttributedString("shell:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
	}

	@Bean
	public Parser parser() {
		ExtendedDefaultParser parser = new ExtendedDefaultParser();
		parser.setEofOnUnclosedQuote(true);
		parser.setEofOnEscapedNewLine(true);
		return parser;
	}
}
