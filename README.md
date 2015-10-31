# Nuun-log-plugin [![Build status](https://travis-ci.org/nuun-io/nuun-log-plugin.svg?branch=master)](https://travis-ci.org/nuun-io/nuun-log-plugin)[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.nuun/nuun-log-plugin/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/io.nuun/nuun-log-plugin) 

This Nuun plugin provides an easy way to inject SLF4J loggers, or to create your own logger annotation.

## How to use it

Add the dependency to your pom.xml:

```xml
<dependency>
    <groupId>io.nuun</groupId>
    <artifactId>nuun-log-plugin</artifactId>
    <version>1.0.M2-SNAPSHOT</version>
</dependency>
```

Then, directly use it in your project.

```java
public class MyClass {

    @NuunLog
    private Logger logger;

    ...
}
```

## How to create my own logger annotation

Our if you are building a framework and you don't want your user to directly dependend on Nuun API,
you can create your own logger annotation as follows:

```java
@Scope
@Documented
@Retention(RUNTIME)
@Target({FIELD})
@NuunLog
public @interface AnnoCustomLog {
}
```
    
This annotation can now be used in the same way as the `NuunLog` annotation.

```java
public class MyClass {

    @AnnoCustomLog
    private Logger logger;

    ...
}
```

## Frequent questions

### Should Logger members of a class be declared as static?

There is no global response. SLF4J doesn't recommand any of the solution.

> We used to recommend that loggers members be declared as instance variables instead of static. After further analysis, we no longer recommend one approach over the other.

If you want more detail about when use static field or instance field see the [SLF4J documentation](http://slf4j.org/faq.html#declared_static) on the subject.

Here is the pros and cons of using instance over static field (Taken from the above page).

#### Advantages for declaring loggers as instance variables

Possible to take advantage of repository selectors even for libraries shared between applications. However, repository selectors only work if the underlying logging system is logback-classic. Repository selectors do not work for the SLF4J+log4j combination.
IOC-friendly

#### Disadvantages for declaring loggers as instance variables

Less common idiom than declaring loggers as static variables
higher CPU overhead: loggers are retrieved and assigned for each instance of the hosting class
higher memory overhead: logger declaration will consume one reference per instance of the hosting class

