import org.gradle.kotlin.dsl.invoke

plugins {
  id("plugin.library-mpp")
}

kotlin {
  sourceSets {
    val commonMain by getting
    val commonTest by getting {
      kotlin.srcDir(projectDir.resolve("tasks"))
      resources.srcDir(projectDir.resolve("inputs"))
      dependencies {
        implementation(project(":engine"))
      }
    }
  }
}
