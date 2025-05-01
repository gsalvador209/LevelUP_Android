plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp) //Tras agregar el TOML ya se puede usar
}

android {
    namespace = "com.tanucode.levelup"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tanucode.levelup"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Room
    implementation(libs.androidx.room.ktx)
    //kapt(libs.androidx.room.compiler) //kapt dice que se generará codigo en tiempo de compilación
    ksp(libs.androidx.room.compiler) //Se debe declarar en plugins aqui y en el otro build.gradle
    //ViewModelScope y Coroutines
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

}