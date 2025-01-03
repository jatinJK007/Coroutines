plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.gradletest"
    compileSdk = 35

    buildFeatures{
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.gradletest"
        //        this used while uploading app on play store
        minSdk = 28
        targetSdk = 35

        versionCode = 1
        versionName = "1.0"
//        while updating the app this has to set to +1 than last version this tells the version of app

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += listOf("paid_status")
//    if we want to have two version of the same app like free ana paid version of the same app then we can do this by
//    productflavours in gradle
    productFlavors{
//        here the product can only be either paid or free not combine as it has same dimension
//        but the combination of both can be possible we can see this in build varient coz of diff dimension
        create("free"){
            applicationIdSuffix=".free"
            dimension= "paid_status"
        }
        create ("paid"){
            applicationIdSuffix=".paid"
            dimension= "paid_status"
        }
//        here the product can be either green or red
//        create ("green"){
//            dimension="style"
//        }
//        create ("red"){
//            dimension="style"
//        }

    }



//     in this we have diff atages of our app like staging , developing, relaese then we can use buildTypes efficiently
    buildTypes {
//        we can create our own buildtypes here staging is defined as per my choice
        create("staging"){
            isMinifyEnabled= true
            buildConfigField("String","Base_url", "\"https:staging_test_api.com\"")
        }
//        this is system generated buildtype
        debug{
            isMinifyEnabled= true
            buildConfigField("String","Base_url", "\"https:debug_test_api.com\"")
        }

        release {
//            if we enable the isMinifyEnabled then a tool called R8 will be used and this wil convert all thc classes and files
//            into a short unreadble class function file helps to prevent reverse engineering of the app
            isMinifyEnabled = false
            buildConfigField("String","Base_url", "\"https:live_test_api.com\"")

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
}

dependencies {
//to use streaming module we need to insert the dependecy here is how to do it
//    implementation(project(":streaming"))

//    kotlin dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}