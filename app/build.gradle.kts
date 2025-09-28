plugins {
    id("application.plugin")
}

dependencies {
    implementation(projects.product.productListing)

    implementation(projects.logger.api)
    implementation(projects.logger.impl)
}

