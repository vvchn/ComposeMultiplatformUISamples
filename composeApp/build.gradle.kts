plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kmpConvention)
    alias(libs.plugins.cmpConvention)
    alias(libs.plugins.detektConvention)
}

private val defaultName = "vvchn.at.composempuisamples"

android {
    namespace = defaultName

    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = defaultName
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = libs.versions.androidVersionCode.get().toInt()
        versionName = libs.versions.androidVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            versionNameSuffix = "_debug"
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":host"))
        }
    }
}