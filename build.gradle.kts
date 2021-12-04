plugins {
  if (System.getenv("CI") == null) {
    id("plugin.git-hooks")
  }
  id("plugin.library-mpp")
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

kotlin { sourceSets { commonTest { kotlin.srcDir(rootDir.resolve("tasks")) } } }
