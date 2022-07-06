# Coconut Blog

Coconut is a blog community for users to register, login, share daily thoughts, make comments, and follow other users. 

**Technology Stack**: Spring Boot, MySQL, MyBatis, Redis, Elasticsearch, Vue.js. 

## Architecture
<img width="300" src="image/architecture.png">

## Entity Relationship Diagram
<img width="500" src="image/erd.png">

## Quick Start
```shell
# Create the backend Docker image. 
backend$ mvn package -DskipTests
backend$ docker build -t coconut-backend .
# Create the frontend Docker image.
frontend$ docker build -t coconut-frontend .
# Run all containers.
$ docker compose up -d
```

## Blog Sample
<img width="400" src="image/blog-list-sample.png">
<img width="400" src="image/blog-detail-sample.png">