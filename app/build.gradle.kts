plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.42.0"
    id("application")
    id("checkstyle")
    id("org.sonarqube") version "6.1.0.5360"
}

application {
    mainClass.set("hexlet.code.App")
}


group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("org.sonarqube:org.sonarqube.gradle.plugin:6.1.0.5360")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.6")
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.test {
    useJUnitPlatform()
}