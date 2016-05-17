## SALES-TAXES
### Requirements
- JDK 1.7 or higher
- Maven
#### Usage
From the main directory run:
```bash
mvn clean install
```
If the installation completed without any error run
```bash
java -jar target/sales-taxes-1.0-SNAPSHOT.jar
```
to execute the program

#### Skip tests
To compile and install without performing any test run
```bash
mvn clean install -Dmaven.test.skip=true
```
