  ____             _                  _   ____                  _
 | __ )  __ _  ___| | _____ _ __   __| | / ___|  ___ _ ____   _(_) ___ ___
 |  _ \ / _` |/ __| |/ / _ \ '_ \ / _` | \___ \ / _ \ '__\ \ / / |/ __/ _ \
 | |_) | (_| | (__|   <  __/ | | | (_| |  ___) |  __/ |   \ V /| | (_|  __/
 |____/ \__,_|\___|_|\_\___|_| |_|\__,_| |____/ \___|_|    \_/ |_|\___\___|
1. Prerequisite
Cài đặt JDK 17+ 
Install Maven 3.5+ 
Install IntelliJ 
Install Docker
2. Technical Stacks
Java 17
Maven 3.5+
Spring Boot 3.3.4
Spring Data Validation
Spring Data JPA
Postgres/MongoDB
Lombok
DevTools
Docker
Docker compose
…
3. Build & Run Application
– Run application bởi mvnw tại folder bbus-be

$ ./mvnw spring-boot:run
– Run application bởi docker

$ mvn clean install -P dev
$ docker build -t bbus-be:latest .
$ docker run -it -p 8080:8080 --name bbus-be bbus-be:latest
4. Test
– Check health với cURL

curl --location 'http://localhost:8080/actuator/health'

-- Response --
{
    "status": "UP"
}
