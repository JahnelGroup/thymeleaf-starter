# Spring Boot Thymeleaf Starter

Jahnel Group starter for Spring Boot and Thymeleaf.  

## Structure 

### Tech Stack

| Component | Tech |
| --- | --- |
| Languages Java | Kotlin, Java, Groovy, HTML, CSS, JavaScript |
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