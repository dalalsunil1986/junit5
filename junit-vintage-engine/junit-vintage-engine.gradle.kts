import aQute.bnd.gradle.BundleTaskConvention;

plugins {
	`java-library-conventions`
}

apply(from = "$rootDir/gradle/testing.gradle.kts")

description = "JUnit Vintage Engine"

val junit_4_13 by configurations.creating {
	extendsFrom(configurations.testRuntimeClasspath.get())
}

dependencies {
	api(platform(project(":junit-bom")))

	api("org.apiguardian:apiguardian-api:${Versions.apiGuardian}")
	api(project(":junit-platform-engine"))
	api("junit:junit:${Versions.junit4}")

	testImplementation(project(":junit-platform-launcher"))
	testImplementation(project(":junit-platform-runner"))
	testImplementation(project(":junit-platform-testkit"))
	junit_4_13("junit:junit:4.13-rc-2")

	testRuntimeOnly("org.apache.servicemix.bundles:org.apache.servicemix.bundles.junit:4.12_1")
}

tasks {
	val test_4_13 by registering(Test::class) {
		classpath -= configurations.testRuntimeClasspath.get()
		classpath += junit_4_13
	}
	check {
		dependsOn(test_4_13)
	}
}

tasks.jar {
	withConvention(BundleTaskConvention::class) {
		bnd("""
			# Import JUnit4 packages with a version
			Import-Package: \
				!org.apiguardian.api,\
				junit.runner;version="${Versions.junit4}",\
				org.junit;version="${Versions.junit4}",\
				org.junit.experimental.categories;version="${Versions.junit4}",\
				org.junit.internal.builders;version="${Versions.junit4}",\
				org.junit.platform.commons.logging;status=INTERNAL,\
				org.junit.runner.*;version="${Versions.junit4}",\
				org.junit.runners.model;version="${Versions.junit4}",\
				*
		""")
	}
}
