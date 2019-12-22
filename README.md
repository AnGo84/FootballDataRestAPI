# FootballDataRestAPI
Build status: [![build_status](https://travis-ci.com/AnGo84/FootballDataRestAPI.svg?branch=master)](https://travis-ci.com/AnGo84/FootballDataRestAPI.svg)
- - -
This is a sample Java / Maven / Spring Boot application that can be used as a starter for providing football data of all major leagues in a machine-readable way. The API for football data is football-data.org.

### Requirements:

- IDE
- JDK 1.8 or later
- Maven
- DynamoDB(or MySQL) 

### Explore Rest APIs
The app will start running at:
```sh
localhost:8088
```
Here are some endpoints you can call:
- /api/competitions : gets all available competitions;
- /api/competitions/{id} : gets info about competiton with choosen id;
- /api/competitions/{id}/matches : gets all matches for competitions with choosen id;
- /api/teams/{id} :  gets info about team with choosen id.

The working examples is available by application link https://footballdatarest.herokuapp.com.
According to the [Heroku] cloud platform, the first request may take about 30 seconds.
For example, for getting all information about [UEFA champions league], you can use link:

[comment]: # ([https://footballdatarest.herokuapp.com/api/competitions/2001](https://footballdatarest.herokuapp.com/api/competitions/2001)

```sh
https://footballdatarest.herokuapp.com/api/competitions/2001
```
All available endpoints for working with database you can find in package **ua.footballdata.controller**.

Also, for getting full describing REST APIs is using the [Swagger].
To view the result of Swagger's work use the URL **v2/api-docs**:

```sh
https://footballdatarest.herokuapp.com/v2/api-docs
```

The result is a JSON response with a large number of key-value pairs.
For making user interaction with the Swagger-generated API documentation much easier use Swagger UI **swagger-ui.html**:

```sh
https://footballdatarest.herokuapp.com/swagger-ui.html
```

License
----
MIT
**Free Software, Hell Yeah!**

[football-data.org]: <https://www.football-data.org/>
[UEFA champions league]: <https://www.uefa.com/uefachampionsleague/>
[Heroku]: <https://www.heroku.com/>
[Swagger]: <https://swagger.io/>

