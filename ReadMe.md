# Getting Started

### Reference Documentation
Application Background

•	This application has been developed using Spring Boot as a backend and plain javascript.
•	Rest End points are developed for data transfer over HTTP as a json object.
•	XMLHttpRequest API is used for client server interaction.
•	Mockito framework is used for writing test cases to check the quality of the application.
•	Jacoco plugin is used for checking the code coverage.
•	LogCaptor dependency is used for mocking logger infos and errors from service and util classes.
•	LogCaptor is good for testing scenarios when the return type of a method is void or there are private method calls in the business logic.
•	The code coverage can be checked at path /ConnectFour/target/site/jacoco/index.html.

How to run application

•	Import the code in the IDE of your choice and do an mvn clean install to build the application.
•	If using STS or Eclipe -- > Right click on the root folder of the application and Run as a Spring Boot project.
•	If using IntelliJ go to /ConnectFour/src/main/java/ie/gens/game/ConnectFourApplication.java right click and run ConnectFourApplication.main()
•	When the server starts, hit http://localhost:8080/ and user will be on the application dashboard.
•	Use 2 tabs in the browser to play the game.
•	Player can only make a move when the other player joins the game.
•	First player to making a combination of 5 'X' or 'O' horizontally, vertically or diagonally wins the game and is displayed on the client.
•	If player A disconnects, player B wins the game and is displayed on the client.


What could have been better

•	More unit test cases to improve overall code coverage. Total code coverage is 66% 
•	Authentication and authorization of the rest end points.
•	Use Angular for building the client.

 

 

