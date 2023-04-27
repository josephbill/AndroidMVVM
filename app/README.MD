# Software Architecture 
MVVM : Model View ViewModel
Model : data class 
View : Screens 
ViewModel : state management

## State Management 
Mediator Pattern : reduce the coupling between components in a system. The idea being to create a central component that control the 
communication between other components.

In MVVM , the mediator is the ViewModel and the components are the views and models. 

Brief summary : process of managing and maintaining the state of the User interface and data in the app.

## Dependency Injection. 
This is a design pattern : used in software dev. to achieve maintainable and testable code 
A technique for managing objects and their relationships in a program. 
Library D.I : Hilt -> Dagger. : simplifies the D.I by providing access to annotations and tools that automate the creation 
and management of objects and their dependencies. 

## DEMO Dependencies 
1. Hilt 
2. Dagger
3. retrofit : API fetch <GET> process  : HTTP requests 
4. Kotlin Coroutines. : used to manage to async API calls 

## Plugins 
1. hilt 
2. kapt