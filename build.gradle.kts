plugins {
    id("org.springframework.boot") version ("3.0.1")
    id("io.spring.dependency-management") version ("1.1.0")
    id("java")
}



group ="co.edu.uniquindio.proyecto"
version ="1.0-SNAPSHOT"
description ="Breve descripción del proyecto"


configurations {
    compileOnly {
       extendsFrom(configurations.named("annotationProcessor").get())
    }

}

repositories {
    mavenCentral()
}

dependencies {

    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly ("mysql:mysql-connector-java")
    runtimeOnly ("org.mariadb.jdbc:mariadb-java-client:2.7.3")

}

tasks.test {
    useJUnitPlatform()
}
