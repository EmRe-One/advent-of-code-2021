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
        val nextDay = 7
        val withTest = true
        val packageIdPath = "de.emreak.adventofcode".replace(".", "/")

        val mainFile    = """$projectDir/src/main/kotlin/${packageIdPath}/Main.kt"""
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
                            // ${"$"}1 
                        """.trimIndent()
                    )
                    .replace(
                        "// $2", """
                        fun solveDay${nextDay}() {
                            val input = AdventOfCodeUtils.readLines(filename = "day${nextDay}.txt")
    
                            val solution1 = Day${nextDay}.part1(input)
                            println("Solution1: ${"$"}solution1")
    
                            val solution2 = Day${nextDay}.part2(input)
                            println("Solution2: ${"$"}solution2")
                        }
                        
                        // ${"$"}2
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