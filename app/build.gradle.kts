import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.imiguez.moviesearcher"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.imiguez.moviesearcher"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val localProperties = Properties()
        localProperties.load(project.rootProject.file("local.properties").inputStream())
        val apiKey = localProperties.getProperty("TMDB_API_KEY")
        buildConfigField("String", "TMDB_API_KEY", "\"$apiKey\"")
        buildConfigField("String", "TMBD_API_BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "TMBD_IMAGES_BASE_URL", "\"https://image.tmdb.org/t/p/\"")


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}