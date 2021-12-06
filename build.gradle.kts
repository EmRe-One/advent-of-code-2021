plugins {
    kotlin("jvm") version "1.6.0"
}

group = "de.emreak.adventofcode"
version = "2021"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src/main/kotlin")
        }
    }

    test {
        useJUnitPlatform()
    }

    wrapper {
        gradleVersion = "7.3"
    }
}

tasks.register("prepareNextDay") {
    doLast {
        val nextDay = 6
        val withTest = true
        val packageId = "de.emreak.adventofcode"

        val newSrcFile = """$projectDir/src/main/kotlin/${packageId.replace(".", "/")}/days/Day${nextDay}.kt"""
        val newTestFile = """$projectDir/src/test/kotlin/${packageId.replace(".", "/")}/days/Day${nextDay}Test.kt"""

        if (file(newSrcFile).exists()) {
            println("$newSrcFile already exists")
        }
        else {
            file(newSrcFile).writeText(
                file("""$projectDir/template/DayX.kt""").readText().replace("$1", "$nextDay")
            )
            file("""$projectDir/src/main/resources/day$nextDay.txt""").writeText("")

            if (withTest) {
                file(newTestFile).writeText(
                    file("""$projectDir/template/DayXTest.kt""").readText().replace("$1", "$nextDay")
                )

                file("""$projectDir/src/test/resources/day${nextDay}_example.txt""").writeText("")
            }
        }
    }
}