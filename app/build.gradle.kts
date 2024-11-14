import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.google.services)
}

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "api.properties")))
}
val baseUrl: String = prop.getProperty("BASE_URL_API")
val apiToken: String = prop.getProperty("API_TOKEN")
val readApiToken: String = prop.getProperty("READ_API_TOKEN")

android {
    namespace = BuildVersion.Environment.APPLICATION_ID
    compileSdk = BuildVersion.Environment.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildVersion.Environment.APPLICATION_ID
        minSdk = BuildVersion.Environment.MIN_SDK_VERSION
        targetSdk = BuildVersion.Environment.TARGET_SDK_VERSION
        versionCode = BuildVersion.Environment.APP_VERSION_CODE
        versionName = BuildVersion.Environment.APP_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildFeatures {
            buildConfig = true
        }

        buildConfigField("String", "BASE_URL", baseUrl)
        buildConfigField("String", "API_TOKEN", apiToken)
        buildConfigField("String", "READ_API_TOKEN", readApiToken)
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
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi",
            "-opt-in=androidx.paging.ExperimentalPagingApi",
            "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.bundles.layer.ui)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.datastore)
    implementation(libs.compose.material.icons.extended)
    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.play.services.safetynet)

    ksp(libs.bundles.compilers.kapt.hilt)
    ksp(libs.bundles.compilers.kapt.room)

//    ksp(libs.bundles.compilers.kapt.hilt)
//    ksp(libs.bundles.compilers.kapt.room)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}