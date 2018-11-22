# loadtest4j-slf4j

[![Build Status](https://travis-ci.com/loadtest4j/loadtest4j-reporter-slf4j.svg?branch=master)](https://travis-ci.com/loadtest4j/loadtest4j-reporter-slf4j)
[![Codecov](https://codecov.io/gh/loadtest4j/loadtest4j-reporter-slf4j/branch/master/graph/badge.svg)](https://codecov.io/gh/loadtest4j/loadtest4j-reporter-slf4j)

SLF4J reporter for [loadtest4j](https://github.com/loadtest4j/loadtest4j).

## Setup

Add this library to your project's `pom.xml`:

```xml
<dependency>
    <groupId>org.loadtest4j.reporters</groupId>
    <artifactId>loadtest4j-slf4j</artifactId>
    <scope>test</scope>
</dependency>
```

**Note:** Reporters reference the core loadtest4j library in `provided` scope. All standard Drivers will provide it automatically. If you are not using a standard Driver, you must add `org.loadtest4j:loadtest4j` to your `pom.xml`.

**Note:** SLF4J requires an appender to work. Your Driver or Web framework (such as Dropwizard) may provide one automatically. If not, you must add an appender (such as Logback) to your `pom.xml`.

```xml
<dependencies>
    <dependency>
        <groupId>org.loadtest4j.drivers</groupId>
        <artifactId>loadtest4j-[driver]</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.loadtest4j.reporters</groupId>
        <artifactId>loadtest4j-slf4j</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Usage

Attach the reporter using the `LoadTesterDecorator`, or any other method that suits you.

```java
public class PetStoreLT {
    private static final LoadTester loadTester = new LoadTesterDecorator()
            .add(new Slf4jReporter())
            .decorate(LoadTesterFactory.getLoadTester());
    
    @Test
    public void shouldFindPets() {
        // ...
    }
}
```