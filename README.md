# Notes App - Authentication Lib

Authentication lib is designed for manage the all Authentication releated funcationlity in one place using with room database so now you don't need to create login and signup module in every application seperetly. you need to use this libray and enjoy it.

## Project Features

This is complete notes app and manage data via locally using with room database and below some featured might be helpful to you.

- Splash screen - Android 12 Splash API 
- Login - signup
- Add, Update, Delete Notes
- Theme support (Dark, Ligh, System Default)
- Logout features

## Download

### For new android studio

Add in setting.gradle file

```bash
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven ("https://jitpack.io") // Add this line 

    }
}
```

### For old android studio

Add in project level build.gradle file 

```bash
repositories {
  google()
  maven ("https://jitpack.io") // Add this line 
} 
```

### Add this libray in app level build.gradle file 

```bash
dependencies {

    implementation 'com.github.chiragt1835:Authentication-android-lib:v1.0.3'
          
    // for build.gradle.kts file
    implementation("com.github.chiragt1835:Authentication-android-lib:v1.0.3")
} 
```

### For maven

```bash
<dependency>
	<groupId>com.github.chiragt1835</groupId>
	<artifactId>Authentication-android-lib</artifactId>
	<version>v1.0.3</version>
</dependency>
```

## How to Use in Project 

### Step 1

You need to extend AuthenticationApp class


```bash
class App : AuthenticationApp() {

}
```

### Step 2

Add this funcation in your activity and enjoy it.

```bash
AuthenticationApp.instance?.startLoginActivity(this,  object : UserCallBack {
            override fun onSuccess(userData: UserData) {
               // Here you get the user data
            }

            override fun onError() {
            }

            override fun onBackPressed() {
                
            }
})
```
## App Screenshot

https://drive.google.com/drive/folders/1Ou8_3F8GRtdErnjshCFh1iWCunv7pl_3?usp=sharing

## Feedback

If you have any feedback, please reach out to us at chirag.vaghani.tot@gmail.com
