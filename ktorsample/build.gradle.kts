import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlin_version: String by project
val logback_version: String by project
val ktor_version: String by project
val koin_version: String by project
val kotlinx_datetime_version: String by project
val hikaricp_version: String by project
val mysql_connector_version: String by project
val exposed_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.1.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
}

group = "com.pedrorau"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor Core
    implementation("io.ktor:ktor-server-core-jvm:${ktor_version}")
    implementation("io.ktor:ktor-server-netty:${ktor_version}")
    implementation("io.ktor:ktor-server-content-negotiation:${ktor_version}")
    implementation("io.ktor:ktor-server-default-headers:${ktor_version}")
    implementation("io.ktor:ktor-server-cors:${ktor_version}")
    implementation("io.ktor:ktor-server-host-common:${ktor_version}")
    implementation("io.ktor:ktor-server-status-pages:${ktor_version}")
    implementation("io.ktor:ktor-server-resources:${ktor_version}")
    implementation("io.ktor:ktor-server-config-yaml")
    // Serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktor_version}")
    // Koin for Dependency Injection
    implementation("io.insert-koin:koin-ktor:${koin_version}")
    implementation("io.insert-koin:koin-logger-slf4j:${koin_version}")
    // Logging
    implementation("ch.qos.logback:logback-classic:${logback_version}")
    implementation("io.ktor:ktor-server-call-logging:${ktor_version}")
    // Date/Time
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${kotlinx_datetime_version}")
    // Database
    implementation("org.jetbrains.exposed:exposed-core:${exposed_version}")
    implementation("org.jetbrains.exposed:exposed-dao:${exposed_version}")
    implementation("org.jetbrains.exposed:exposed-jdbc:${exposed_version}")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:${exposed_version}")
    implementation("com.mysql:mysql-connector-j:${mysql_connector_version}")
    implementation("com.zaxxer:HikariCP:${hikaricp_version}")
    // Status Pages
    implementation("io.ktor:ktor-server-status-pages:${ktor_version}")
    // Swagger/OpenAPI
    implementation("io.ktor:ktor-server-swagger:${ktor_version}")
    implementation("io.ktor:ktor-server-openapi:${ktor_version}")
    // Testing
    testImplementation("io.ktor:ktor-server-test-host:${ktor_version}")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${kotlin_version}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}