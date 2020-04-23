# Simple Jackson Date Module
 
## Overview
I found myself constantly fighting date serialization with Java 8 dates in our Springboot microservices since we have
front ends that use different date representations (i.e. UTC or Unix Epoch timestamps, etc...)

This was further complicated by the javascript libraries we were using to handle dates which didn't like partial
ISO date's like LocalDate which were serialized to something like '2020-01-01' by default. 

This library assembles date serializers and deserializers for the Java 8 temporal types and forces them all to 
a consistent representation when serialized. 

For example:
* Epoch: 2020-04-23T16:18:21.593-04:00
* Date: 2020-04-23T16:18:21.593-04:00
* LocalDate: 2020-04-23T00:00:00.000-04:00
* LocalDateTime: 2020-04-23T16:18:21.593-04:00
* ZonedDateTime: 2020-04-23T16:18:21.593-04:00
* OffsetDateTime: 2020-04-23T16:18:21.593-04:00
 

The above is using the default representation of ISO, but you can configure it to the following others:

UTC:
* Epoch: 2020-04-23T20:36:40.743Z
* Date: 2020-04-23T20:36:40.76Z
* LocalDate: 2020-04-23T04:00:00Z
* LocalDateTime: 2020-04-23T20:36:40.761Z
* ZonedDateTime: 2020-04-23T20:36:40.761Z
* OffsetDateTime: 2020-04-23T20:36:40.761Z

Epoch (in milliseconds)
* Epoch: 1587674232455
* Date: 1587674232468
* LocalDate: 1587614400000
* LocalDateTime: 1587674232468
* ZonedDateTime: 1587674232468
* OffsetDateTime: 1587674232468

# Key Classes
* **SimpleDateModule** - The Jackson date module you register in your config  

# Usage

## Maven Dependency
```
<dependency>
  <groupId>com.github.sourcegroove</groupId>
  <artifactId>jackson-simple-date-module</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Gradle Dependency
```
implementation 'com.github.sourcegroove:jackson-simple-date-module:1.0.0'
```

## Configuration

In a SpringBoot app, create a class like the below:
```java
@Configuration
public class ObjectMapperConfig {
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .modules(new SimpleDateModule());
    }
}
```

### Change the date representation
If you want to specify a supported date representation other than the default (ISO), 
you can set it in the SimpleDateModule constructor as follows:

```java
@Configuration
public class ObjectMapperConfig {
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .modules(new SimpleDateModule(DateRepresentationType.UTC));
    }
}
``` 

