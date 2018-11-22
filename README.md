# loadtest4j-reporter-slf4j

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

**Note:** Reporters reference loadtest4j in `provided` scope. This means you must 'provide' it separately. A Driver will provide it automatically:

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