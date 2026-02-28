# youi-core

## SBT Configuration

youi is published to Sonatype OSS and Maven Central and utilizes JVM and Scala.js with 2.11 and 2.12:

```
libraryDependencies += "com.outr" %%% "youi-core" % latestVersion // Scala and Scala.js
```

## Main Features

### URL

A fairly simple, but complete representation of a URL. Includes proper parsing, formatting, and manipulation
functionality.  Finally, URL interpolation at compile-time offers a convenient mechanism to validate URLs at
compile-time.

Creating a URL can be done in various ways.

#### Parsing

```
val url = URL("http://youi.io")
```

#### Interpolation

Has the benefit of throwing a compilation error if it fails to validate.

```
val url = url"http://youi.io"
```

#### Manually

```
val url = URL(protocol = Protocol.Http, host = "youi.io")
```

### Unique

Generates a unique String. Similar concept to UUID, but much faster, slightly less unique, and configurable. Uses
`ThreadLocalRandom` to generate the values.

Generating a 32 character with number and letters (upper and lower):

```
val id = youi.Unique()
```

For an 8 character string with lowercase letters and numbers:

```
val id = youi.Unique(length = 8, characters = youi.Unique.LettersAndNumbers)
```