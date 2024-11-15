
# Note :  this version of the project is incomplete 

# Requirements :
-JDK 21 at least (to use gradle)  
-Gradle (You can use the Gradle Wrapper coming with the project)

# Powered by :
-Gradle
-Junit
-Spring
-H2
-Mockito

/!\ CAUTION
The project was hosted on Windows to help dealing with some errors (as suggested by the instructor). 
So there will be issues building it on a UNIX like machine
Try this command if available :
   
    dos2unix gradlew


# How to build the project :

## Clone the Repository: Clone the project to your local machine:
    git clone https://github.com/Clairevanr/Automacorp_save.git 
#
    cd Automacorp_save

## Build the Project: Run the following command:
    ./gradlew build


## Run tests: 
    ./gradlew test

## Package the project as a JAR: 
    ./gradlew jar

## Clean the build directory:
    ./gradlew clean
