plugins { id("plugin.library-mpp") }

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(kotlin("test-common"))
        api(kotlin("test-annotations-common"))
        api("dev.petuska:klip:_")
      }
    }
    named("jsMain") { dependencies { api(kotlin("test-js")) } }
    named("jvmMain") { dependencies { api(kotlin("test-junit5")) } }
    named("androidMain") { dependencies { api(kotlin("test-junit5")) } }
  }
}
