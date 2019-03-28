# WordSquareCalculator

## How to Run From The Command Line
##### Unit Tests 
In the root directory type `mvn clean compile test`
##### Integration Tests (As provided in spec)
In the root directory type `mvn clean compile integration-test`
##### To use The CLI
1. Make a jar `mvn clean package`
2. Have run the jar `java -jar target\WordSquareCalculator-1.0.0-SNAPSHOT-jar-with-dependencies.jar`

## APIs and Libraries Used
 - Java 8
 - Junit
 - Spring-context (for dependency injection)
 - Spring-core (for some helper methods)


## Overall Methodology
1. The problem was broken down into user stories (available under [userstories.md](userstories.md))
2. Each story has acceptance criteria ordered in terms of dependence
3. One at a time each of the acceptance criteria was converted into at least one unit test
4. Once created the test initially fails as it has no implementation. Thus it is made to pass before moving on (`TDD: Red/Green/Refactor cycle`)