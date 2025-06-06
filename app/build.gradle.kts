plugins {
    id("com.github.ben-manes.versions") version "0.42.0"
    id("application")
    id("checkstyle")
    id("jacoco")
    id("org.sonarqube") version "6.1.0.5360"
}
sonar {
    properties {
        property("sonar.projectKey", "kalaysolay_java-project-71")
        property("sonar.organization", "kalaysolay")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}
application {
    mainClass.set("hexlet.code.App")
}


group = "hexlet.code"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("info.picocli:picocli:4.7.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    //implementation("org.sonarqube:org.sonarqube.gradle.plugin:4.4.1.3373")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.6")
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}


tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)    // ☑️ ОБЯЗАТЕЛЬНО для SonarQube
        html.required.set(true)   // по желанию
    }
}