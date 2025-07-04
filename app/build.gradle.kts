plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp) //Tras agregar el TOML ya se puede usar
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.tanucode.levelup"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tanucode.levelup"
        minSdk = 26
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

    //Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //FlexboxLayout
    implementation(libs.google.flexbox)

    //Firebase
    implementation(libs.firebase.auth)

    //Calendar views
    implementation(libs.core)
    // Month-grid view
    implementation(libs.view)

    //Para retrofit y Gson
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Adicional para el interceptor
    implementation(libs.logging.interceptor)

    //Glide y Picasso
    implementation(libs.glide)
    implementation(libs.picasso)


    //Corrutinas
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.play.services)

}
