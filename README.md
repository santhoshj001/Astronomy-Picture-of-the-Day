
# Astronomy Picture of the Day

This is a Native Andorid App developed in Jetpack Compose MVVM architecture, To view the Astronomy Picture of the day from NASA 
## API Key

To build this project, you will need to Add new file `secrets.properties` and Add the `nasaApiKey`
like `nasaApiKey="KEY_VALUE"`

by default, the API KEY is Used from `secrets.defaults.properties`, which has a limit of 30  requests per hour
and 50 requests per day

please get the `API KEY` from https://api.nasa.gov/



## Technologies Used 🛠
- [Kotlin](https://kotlinlang.org/) -  Official programming language for Android development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s modern toolkit for building native UI.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [Coil Image Loading](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.By default, it's already set up for disk caching and will set a max cache size of between 10-250MB depending on the remaining space on the user's device.
- [Retrofit](https://square.github.io/retrofit/)- A type-safe HTTP client for Android and Java
- [Hilt](https://dagger.dev/hilt/) - Hilt provides a standard way to incorporate Dagger dependency injection into an Android application
- [Room](https://developer.android.com/training/data-storage/room) - Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - data storage solution that allows you to store key-value pairs or typed objects with protocol buffers
- [Navigation](https://developer.android.com/guide/navigation) -helps in build and structure in-app UI, handle deep links, and navigate between screens

<br />


## Features

- search for the picture for a date of choice
- create/manage a list of "favorite" listings
- Display date, explanation, Title and the image of the day, if it's a video just show the video thumbnail 
- cache information and display last updated information in case of network unavailability.
- Light/dark mode supported, will match with system theme



## Screenshots



<img width="200" alt="Screenshot 2021-12-14 at 4 51 56 PM" src="https://user-images.githubusercontent.com/93982208/145989529-5aa8020a-fbc6-42a2-8ada-d20b6f9a99d1.png">   <img width="200" alt="Screenshot 2021-12-14 at 4 51 00 PM" src="https://user-images.githubusercontent.com/93982208/145989005-82e5d65c-9fd6-4895-99e0-a7fc3817efc7.png">   <img width="200" alt="Screenshot 2021-12-14 at 4 52 01 PM" src="https://user-images.githubusercontent.com/93982208/145989140-7db05f6f-75f9-4306-a9e5-a966eb590ebc.png">




## Feedback

If you have any feedback, please reach out to me at santhoshj296@gmail.com



