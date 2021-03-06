/*
 * Copyright 2015-2019 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

/**
 * JUnit Vintage provides a {@linkplain TestEngine} for running JUnit 3 and 4 based tests on the platform.
 *
 * @moduleGraph
 * @since 5.5.0
 */
module org.junit.vintage.engine {
	requires junit; // 4
	requires org.apiguardian.api;
	requires org.junit.platform.engine;

	provides org.junit.platform.engine.TestEngine
			with org.junit.vintage.engine.VintageTestEngine;
}
