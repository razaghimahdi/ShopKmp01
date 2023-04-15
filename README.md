# Shopping App (Compose Multiplatform mobile application)

Welcome to the documentation for the Jetpack Compose Multiplatform Shopping Application! 
This is a cross-platform application that is built using Jetpack Compose Multiplatform, a declarative framework for sharing UIs across multiple platforms with Kotlin. 
The application allows users to browse, search, and purchase products from a shopping catalog on Android, iOS, and desktop platforms.


## Development
This project is a work in progress and is not yet complete. 
We will continue to work on it and update it regularly until we have a complete shopping application. 
We appreciate any feedback or suggestions that you may have to help us improve the project.

We plan to add more features, improve the code quality, and make the application more user-friendly. 
Our goal is to create a high-quality, multiplatform shopping application that demonstrates the power and flexibility of Jetpack Compose.

Please stay tuned for updates and feel free to contribute to the project by submitting pull requests or opening issues. 
Together, we can create a great shopping application that meets the needs of users across multiple platforms.

![mobile-app-development-banner](https://user-images.githubusercontent.com/61207818/232203047-54940b08-d53f-41ce-a313-483a5fbeb9d3.jpg)

## Architecture
The Jetpack Compose Multiplatform Shopping Application is built using the Clean Architecture and the MVI (Model-View-Intent) pattern. 
The application is divided into the following layers:

**Presentation**: This layer includes the Jetpack Compose user interface components and logic. 
It's also responsible for mapping data from the domain layer into a format that can be displayed by the user interface.

**Domain**: This layer includes the business logic and use cases of the application. It's also responsible for defining the data models and the repository interfaces.

**Data**: This layer includes the repository implementation that fetches data from the https://fakestoreapi.com server using Ktor.

<img src="https://user-images.githubusercontent.com/61207818/232203143-1815f502-18d4-4051-b636-dc016699c770.png" alt="Clean Architecture in Android" width="600"/>

## Screenshots

### Android

When Android is one of your targets, 
you can get the same experience for Android as if you were developing an Android app using Jetpack Compose.

| Main                                       | Detail                                     |
|--------------------------------------------|--------------------------------------------|
| <img src="screenshots/1.jpg" width="300"/> | <img src="screenshots/2.jpg" width="300"/> |


### IOS
> iOS support is in Alpha. It may change incompatibly and require manual migration in the future.

Compose Multiplatform shares most of its API with Jetpack Compose, the Android UI framework developed by Google. 
You can use the same APIs to build user interfaces for both Android and iOS.

### Desktop

Compose Multiplatform targets the JVM and supports high-performance hardware-accelerated UI rendering on all major desktop platforms – macOS,
Windows, and Linux.

It has desktop extensions for menus, keyboard shortcuts, window manipulation, and notification management.

## Contributing
Contributions are welcome! If you find a bug or would like to create a new feature, please submit a pull request.

## License
This library is licensed under the MIT License. See [LICENSE.txt](https://github.com/razaghimahdi/ShopKmp01/blob/master/license)


### more ideas:
https://github.com/JetBrains/compose-multiplatform-ios-android-template

https://github.com/JetBrains/compose-multiplatform

Developed by Mahdi Razzaghi Ghaleh
