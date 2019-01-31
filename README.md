# Spring Boot Thymeleaf Starter

Jahnel Group starter for Spring Boot and Thymeleaf.  

## Structure 

### Tech Stack

| Tech | Version |
| --- | --- |
| Java | TBD |
| Kotlin | TBD |
| Spring Boot | TBD |
| MariaDB | TBD |

### File Structure 

The overall file structure is as follows:

```text
/thymeleaf-starter/
├── src/main/
│   └── kotlin/
│       └── com/jahnelgroup/
│           └── *.[kt|java]
│   └── resources/
│       └── static/
│           └── *.[js|css]
│       └── templates/
│           └── *.html
│       └── application.[properties|yml]
├── Dockerfile
├── build.gradle
└── docker-compose.yml
```

### Gradle

Gradle is the build and dependency management tool and Docker is the containerization technology. This starter uses Gradle docker plugins to help simplify the process.

To run tests:

```bash
$ gradle test
```

To build a fat spring-boot jar (build/libs/*.jar):

```bash
$ gradle build
```

To build the docker container:

```bash
$ gradle docker
$ docker images | grep thyme
  com.jahnelgroup/thymeleaf-starter  latest 67dd8d0ea7d2  9 minutes ago  146MB
```

To bring up the stack with docker-compose:

```bash
$ gradle composeUp
...
BUILD SUCCESSFUL in 13s
```

Then navigate to http://localhost:8080/