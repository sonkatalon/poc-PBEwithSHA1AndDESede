plugins {
    id("java")
    id("org.teavm") version "0.10.0"
}

group = "com.daohoangson"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

teavm.js {
    mainClass = "CryptoUtil"
}