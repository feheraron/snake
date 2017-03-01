# snake
Pet project for the sake of fun :)
Desktop variant of the old Nokia phone style snake game.

## Software dependencies
* Java 7 JDK or newer - both for compiling and for the runtime
* Maven 3.3.1 or newer - to trigger build or execution and for managing library dependencies

## Get the code
Clone the project (or download and extract the source files) into the folder of your choice.

In command line cd into the folder which contains the pom.xml

## Compile
command line:
`mvn clean compile`

## Run the game (compiling is prerequisite)
command line:
`mvn exec:java`

## Build runnable jar
command line:
`mvn clean package`

On successful build the runnable jar will be under the project-root/target folder
To run the jar one will need Java 7 (JRE or JDK) or newer
command line execution example:
`java -jar snake-1.0.0-SNAPSHOT.jar`
