# 1. mapping collection using stream api
If you want to map a collection from one type to another using Java Streams API without relying on `TypeToken`, you can manually convert each element of the collection using a `Function` or a method reference. This approach is straightforward and works well when you need fine-grained control over the mapping process.

Here's how you can achieve this:

### Example: Mapping Collections Using Streams API

Suppose you have the following `Source` and `Destination` classes:

**Source Class**

```java
public class Source {
    private String name;
    private int age;

    // Constructors, getters, and setters
    public Source(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

**Destination Class**

```java
public class Destination {
    private String name;
    private int age;

    // Constructors, getters, and setters
    public Destination(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

### Mapping List of `Source` to List of `Destination`

**Using Streams API**

```java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiMappingExample {

    public static void main(String[] args) {
        // Create a list of Source objects
        List<Source> sourceList = new ArrayList<>();
        sourceList.add(new Source("Alice", 30));
        sourceList.add(new Source("Bob", 25));

        // Map Source to Destination using Streams API
        List<Destination> destinationList = sourceList.stream()
            .map(source -> new Destination(source.getName(), source.getAge()))
            .collect(Collectors.toList());

        // Output the results
        destinationList.forEach(dest -> System.out.println("Name: " + dest.getName() + ", Age: " + dest.getAge()));
    }
}
```

### Explanation

1. **Create Source List**: Initialize a `List<Source>` with some example data.

2. **Stream Mapping**:
   - **`sourceList.stream()`**: Convert the `sourceList` to a stream.
   - **`.map(source -> new Destination(source.getName(), source.getAge()))`**: Use the `map` function to convert each `Source` object to a `Destination` object. This is done using a lambda function or method reference.
   - **`.collect(Collectors.toList())`**: Collect the mapped `Destination` objects into a `List`.

### Alternative with Method Reference

If you prefer to use method references, you can define a mapping function separately:

```java
public class StreamApiMappingExample {

    public static void main(String[] args) {
        // Create a list of Source objects
        List<Source> sourceList = new ArrayList<>();
        sourceList.add(new Source("Alice", 30));
        sourceList.add(new Source("Bob", 25));

        // Method reference for mapping
        List<Destination> destinationList = sourceList.stream()
            .map(StreamApiMappingExample::mapToDestination)
            .collect(Collectors.toList());

        // Output the results
        destinationList.forEach(dest -> System.out.println("Name: " + dest.getName() + ", Age: " + dest.getAge()));
    }

    // Mapping method
    private static Destination mapToDestination(Source source) {
        return new Destination(source.getName(), source.getAge());
    }
}
```

### Summary

- **Streams API**: Provides a functional approach to map collections with the `.map()` method.
- **Lambda Expressions or Method References**: Allows you to define the mapping logic inline or through a separate method.
- **Collect**: Use `Collectors.toList()` to gather the results into a new collection.

This approach is flexible and suitable for many use cases where you want to transform collections without additional dependencies like `TypeToken`.



### Using ModelMapper in a Stream

Here’s how you can use `ModelMapper` to map a list of `Source` objects to a list of `Destination` objects using Java Streams:

**1. Add ModelMapper Dependency**

Make sure `ModelMapper` is included in your project dependencies.

**Maven:**
```xml
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.1.0</version> <!-- Check for the latest version -->
</dependency>
```

**Gradle:**
```groovy
implementation 'org.modelmapper:modelmapper:3.1.0' // Check for the latest version
```

**2. Use ModelMapper in Stream**

```java
import org.modelmapper.ModelMapper;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperStreamExample {

    public static void main(String[] args) {
        // Initialize ModelMapper
        ModelMapper modelMapper = new ModelMapper();

        // Create a list of Source objects
        List<Source> sourceList = Arrays.asList(
            new Source("Alice", 30),
            new Source("Bob", 25)
        );

        // Map Source to Destination using Streams and ModelMapper
        List<Destination> destinationList = sourceList.stream()
            .map(source -> modelMapper.map(source, Destination.class))
            .collect(Collectors.toList());

        // Output the results
        destinationList.forEach(dest -> 
            System.out.println("Name: " + dest.getName() + ", Age: " + dest.getAge())
        );
    }
}
```

### Explanation

1. **Initialize ModelMapper**: Create an instance of `ModelMapper`.

2. **Create Source List**: Initialize a list of `Source` objects with sample data.

3. **Map Using Streams**:
   - **`sourceList.stream()`**: Convert the list to a stream.
   - **`.map(source -> modelMapper.map(source, Destination.class))`**: Use the `map` function to convert each `Source` object to a `Destination` object using `ModelMapper`. The `modelMapper.map(source, Destination.class)` call handles the property mapping automatically.
   - **`.collect(Collectors.toList())`**: Collect the mapped `Destination` objects into a new `List`.

4. **Output Results**: Print the mapped `Destination` objects to verify the results.

### Summary

- **Automatic Mapping**: `ModelMapper` simplifies property mapping between source and destination objects.
- **Streams API**: Use the `map` method in streams to apply the transformation function to each element in the collection.
- **No Manual Setters**: You avoid manually setting properties or using getters and setters explicitly.

This approach leverages the power of both the Streams API and ModelMapper to make the code more readable and maintainable, especially when dealing with large collections and complex mappings.