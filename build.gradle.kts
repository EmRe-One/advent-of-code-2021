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
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-classic:1.2.7")
    implementation("ch.qos.logback:logback-core:1.2.7")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.0")
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
        val nextDay = 23
        val withTest = true
        val packageIdPath = "de.emreak.adventofcode".replace(".", "/")

        val mainFile    = """$projectDir/src/main/kotlin/${packageIdPath}/Main.kt"""
        val readmeFile  = """$projectDir/README.md"""
        val newSrcFile  = """$projectDir/src/main/kotlin/${packageIdPath}/days/Day${nextDay}.kt"""
        val newTestFile = """$projectDir/src/test/kotlin/${packageIdPath}/days/Day${nextDay}Test.kt"""

        if (file(newSrcFile).exists()) {
            println("WARNING: Files for Day$nextDay already exists. Do you really want to overwrite it?")
        } else {
            file(newSrcFile).writeText(
                file("""$projectDir/template/DayX.kt""")
                    .readText()
                    .replace("$1", "$nextDay")
            )
            file("""$projectDir/src/main/resources/day$nextDay.txt""").writeText("")

            file(mainFile).writeText(
                file(mainFile).readText()
                    .replace(
                        "// $1", """
                            $nextDay -> solveDay${nextDay}()
                            // ${"$1"} 
                        """.trimIndent()
                    )
                    .replace(
                        "// $2", """
                        fun solveDay${nextDay}() {
                            val input = AdventOfCodeUtils.readLines(filename = "day${nextDay}.txt")
    
                            val solution1 = Day${nextDay}.part1(input)
                            logger.info { "Solution1: ${"$"}solution1" }
    
                            val solution2 = Day${nextDay}.part2(input)
                            logger.info { "Solution2: ${"$"}solution2" }
                        }
                        
                        // ${"$2"}
                        """.trimIndent()
                    )
            )

            file(readmeFile).writeText(
                file(readmeFile).readText()
                    .replace(
                        "<!-- $1 -->",
                        """| [Day ${nextDay}](https://adventofcode.com/2021/day/${nextDay}) | [Day${nextDay}Test.kt](https://github.com/EmRe-One/advent-of-code-2021/blob/master/src/test/kotlin/de/emreak/adventofcode/days/Day${nextDay}Test.kt) | [Day${nextDay}.kt](https://github.com/EmRe-One/advent-of-code-2021/blob/master/src/main/kotlin/de/emreak/adventofcode/days/Day${nextDay}.kt) |       |       |
${"<!-- $1 -->"}
                        """.trimIndent()
                    )
            )

            if (withTest) {
                file(newTestFile).writeText(
                    file("""$projectDir/template/DayXTest.kt""")
                        .readText()
                        .replace("$1", "$nextDay")
                )

                file("""$projectDir/src/test/resources/day${nextDay}_example.txt""")
                    .writeText("")
            }
        }
    }
}