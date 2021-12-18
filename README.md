
#  Telescope - Astronomy Picture of the Day

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

<img src="https://user-images.githubusercontent.com/93982208/146616608-150d5d68-168a-4731-afb3-bc1969b50011.png" width=200/>      <img src="https://user-images.githubusercontent.com/93982208/146616611-9589d584-b481-4ed5-97da-1726b7ba0262.png" width=200/>    <img src="https://user-images.githubusercontent.com/93982208/146616614-925f260a-0eb3-4498-852c-c6872bd87dd9.png" width=200/>    <img src="https://user-images.githubusercontent.com/93982208/146616599-9f87f56f-1a65-4600-b41d-de2485f8f78d.png" width=200/>






## Feedback

If you have any feedback, please reach out to me at santhoshj296@gmail.com



