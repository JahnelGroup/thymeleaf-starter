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
├── .elasticbeanstalk/
│   └── config.yml
├── ebextensions/
│   └── env.config
├── gradle/wrapper/
│   └── gradle.*.[jar|properties]
├── src/main/
│   ├── kotlin/
│   |   └── com/jahnelgroup/
│   |       └── *.[kt|java]
│   └── resources/
│       ├── static/
│       |   └── *.[js|css]
│       ├── templates/
│       |   └── *.html
│       └── application.[properties|yml]
├── src/test/
│   └── (testing framework)
├── Dockerfile
├── build.gradle
├── docker-compose.full.yml
├── docker-compose.yml
└── settings.gradle
```

### Gradle

[Gradle](https://gradle.org/) is the build and dependency management tool used by this starter. Most actions can be accomplish through the Gradle tasks described here.  

**Clean/Test/Build:**

| Command | Description |
| --- | --- |
| gradle clean build | Deletes build artifacts and runs a fresh build. |
| gradle test  | Run tests. |

**Docker:**

| Command | Description |
| --- | --- |
| gradle clean build docker | Clean, fresh build, then build docker image. |
| gradle fullComposeUp | Start everything including the app (must have built the container first) |
| gradle fullComposeDown | ^ Stops the full stack |
| gradle depsComposeUp | Start only the app dependencies |
| gradle depsComposeDown | ^ Stops the dependency stack |

**Flyway:**

| Command | Description |
| --- | --- |
| gradle [flywayMigrate](https://flywaydb.org/documentation/gradle/migrate) | Migrates the database |
| gradle [flywayClean](https://flywaydb.org/documentation/gradle/clean) | Drops all objects in the configured schemas |
| gradle [flywayInfo](https://flywaydb.org/documentation/gradle/info) | Prints the details and status information about all the migrations |
| gradle [flywayValidate](https://flywaydb.org/documentation/gradle/validate) | Validates the applied migrations against the ones available on the classpath |
| gradle [flywayBaseline](https://flywaydb.org/documentation/gradle/baseline) | Baselines an existing database, excluding all migrations up to and including baselineVersion |
| gradle [flywayRepair](https://flywaydb.org/documentation/gradle/repair) | Repairs the schema history table |

By default these Flyway commands are pointing to a local instance of the starter database.  

```groovy
flyway {
    url = 'jdbc:mysql://127.0.0.1:3306/jg_starter'
    user = 'root'
	password = 'rootpassword'
}
```

## AWS Elastic Beanstalk

TODO: Configure aws cli and eb cli

Commands:

`eb list`
`eb create --single --database`
`eb setenv SPRING_PROFILES_ACTIVE=beanstalk`

https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/environment-configuration-methods-during.html

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
