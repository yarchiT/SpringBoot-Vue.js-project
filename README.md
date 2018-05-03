# SpringBoot-Vue.js-project

Basic social network named "Kekker" (analogy to Twitter, and our "kek" is analog for tweet).
Basic functions:
- sign up a new user and login with your credentials;  
- create keks ("tweets");  
- comment on keks;  
- follow/unfollow people;  
- view your timeline (keks from people you follow) and your own profile;  
- search for people by their nickname;  

# Technology stack

1. Spring Boot - as the backend  
2. Vue.js - as the frontend framework  
3. PostgreSQL - database (change application.properties file to connect to your own db)


# Structure
We used maven as our project management and comprehension tool.
The base struture we created looks like this.

app  
├── frontend  
│	├── pom.xml  
├── backend  
│	├── src  
│	├── pom.xml  
├── pom.xml  

# Running the spring boot app
cd app  
mvn install  
mvn --projects backend spring-boot:run  
