# Pet Store
#### Basic implementation of [Swagger PetStore](http://petstore.swagger.io/) like APIs, along with front-end. 

[Swagger Doc](http://petstore-subhadip.herokuapp.com/petstore/swagger-ui.html#/Pet_resources) for all available APIs

[Heroku Deployment](http://petstore-subhadip.herokuapp.com/petstore/) for live demo. 

#### Fornt-End : 
- AngularJs
- Bootstrap
- NPM
- Bower
- Gulp

#### Back End Services :
- Spring Boot
- REST with Spring
- Swagger for API documentation
- JPA for storage

#### Persistence :
- In Memory HSQLDB

#### Cloude Deployment :
- [Heroku](https://www.heroku.com)


### Run from local :

#### *Npm* and *Gulp* must be installed globally in order to build the project.
> If using unix, replace 'npm.cmd' with 'npm' and 'gulp.cmd' with 'gulp' in the pom.xml. 

```
git clone https://github.com/onesubhadip/PETSTORE.git
cd PETSTORE
mvn spring-boot:run
```

Open in browser : 

UI : http://localhost:8080/petstore/

Swagger : http://localhost:8080/petstore/swagger-ui.html


### Deployment in Heroku
Heroku CLI, Git, Maven 3, Java 8 should be locally installed. 
Please use [this](https://devcenter.heroku.com/articles/getting-started-with-java#introduction) reference document.
```
$heroku login
$heroku create
$git push heroku master
$heroku ps:scale web=1
$heroku open
$heroku logs --tail
```

>**Credit for Pet Images**
><div>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
