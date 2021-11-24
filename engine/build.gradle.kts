import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `java-test-fixtures`
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

    testImplementation(kotlin("test"))
    testImplementation("com.natpryce", "hamkrest", hamkrestVersion)
    testImplementation("com.ubertob.kondor", "kondor-tools", kondorVersion)

    testFixturesImplementation("com.natpryce", "hamkrest", hamkrestVersion)
    testFixturesImplementation("com.ubertob.kondor", "kondor-core", kondorVersion)
    testFixturesImplementation("com.ubertob.kondor", "kondor-outcome", kondorVersion)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}