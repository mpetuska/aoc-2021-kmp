plugins {
  if (System.getenv("CI") == null) {
    id("plugin.git-hooks")
  }
  id("plugin.library-mpp")
}

idea {
  module {
     excludeDirs.add(projectDir.resolve("src/commonTest/kotlin/input"))
  }
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

kotlin { sourceSets { commonTest { dependencies { implementation(project(":test")) } } } }
