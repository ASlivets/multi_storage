import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"

    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}

group = "com.jedicoder"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-mustache")

    runtimeOnly(group = "org.springframework.boot", name = "spring-boot-devtools")

    compile(group = "org.slf4j", name = "slf4j-api", version = "1.7.29")

    testImplementation(group = "org.assertj", name = "assertj-core", version = "3.14.0")
    testImplementation(group = "org.springframework.boot", name = "spring-boot-starter-test")
    testImplementation(group = "io.rest-assured", name = "rest-assured")
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api")
    testRuntime(group = "org.junit.jupiter", name = "junit-jupiter-engine")
    testCompile(group = "org.junit.jupiter", name = "junit-jupiter-params")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
