# Lawn mower test 
First, make sure you have Maven and java 11 installed.

After cloning the repository, in order to compile the project and run unit tests go to the root project directory (where pom.xml is located) and execute the below command:
  ```sh
   mvn clean install
  ```
Maven will automatically install the required dependencies like log4j version 1.2 and junit 4 used by unit tests.

To run the program using Maven, an argument should be provided in the command line which is the absolute path to the file containing lawn mower configuration.
  ```sh
  mvn exec:java -Dexec.mainClass="com.project.app.Main" -Dexec.args="/home/adaoud/lawnmower/input.txt"
  ```
 
<!-- CONTACT -->
## Contact
daoud.amin7@gmail.com

