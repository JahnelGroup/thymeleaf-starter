# Spring Boot Thymeleaf Starter

Jahnel Group starter for Spring Boot and Thymeleaf.  

## Structure 

### Tech Stack

| Component | Tech |
| --- | --- |
| Languages | Kotlin, Java, Groovy, HTML, CSS, JavaScript |
| Frameworks | Spring Boot, Thymeleaf, Bootstrap |
| Databases | MySQL, H2 |
| Deployments | Docker, AWS Elastic Beanstalk |

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

Gradle is the build and dependency management tool and Docker is the containerization technology. This starter uses Gradle docker plugins to help simplify the process and they can be combined in sequence. 

**Clean/Test/Build:**

| Command | Version |
| --- | --- |
| gradle clean | Delete distribution artifacts. |
| gradle test  | Run all tests. |
| gradle build | Build the app. |

**Docker:**

| Command | Version |
| --- | --- |
| gradle clean build docker | Remove artifact, rebuild, and then build a docker container. |
| gradle fullComposeUp | Start everything including the app (must have built the container first) |
| gradle fullComposeDown | ^ Stops the full stack |
| gradle depsComposeUp | Start everything including the app (must have built the container first) |
| gradle depsComposeDown | ^ Stops the dependency stack |

If running locally the app is located at http://localhost:8080/

## AWS Elastic Beanstalk

TODO: Configure aws cli and eb cli

Commands:

`eb list`
`eb create --single --database`
`eb setenv SPRING_PROFILES_ACTIVE=beanstalk`

## Spring Boot Dev Tools

[Spring Boot Dev Tools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html) includes an additional set of tools that can make the application development experience a little more pleasant.

### Live Reload within IntelliJ

IDE's are fantastic but introduce complexity when it comes to watching file changes. As such Spring Boot Dev Tools can't easily automatically detect if files have changed within IntelliJ. 

If you want changes to take effect then triggering a build within IntelliJ will cause Spring Boot to reload:

* \[Build\] then \[Rebuild Project]
* Or use short cut `Ctrl + F9`  

#### Fully Automatic

If you really want every change to trigger a reload then follow these steps. 

* \[Ctrl + Shift + A] then select Registry
* Enable "compiler.automake.allow.when.app.running" then close
* \[File Menu] then \[Settings]
* Expand \[Build, Execution, Deployment] then select Compiler
* Enable "Build project automatically" then Apply then Ok
* Restart Intellij
