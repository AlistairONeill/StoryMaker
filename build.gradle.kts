import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.21"
}

group = "uk.co.alistaironeill"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val hamkrestVersion : String by project
val kondorVersion : String by project

dependencies {
    implementation("com.ubertob.kondor", "kondor-core", kondorVersion)
    implementation("com.ubertob.kondor", "kondor-outcome", kondorVersion)
    implementation(project(":engine"))

    testImplementation(kotlin("test"))
    testImplementation("com.natpryce", "hamkrest", hamkrestVersion)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}