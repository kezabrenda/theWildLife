# theWildLife

### author name
* Brenda UMUTONIWASE KEZA

## Description of project
This is an app that helps a park or forest to keep track of animals, endangered or regular by sight zones and rangers

### Project setup instructions
* install INTELLIJ IDEA
* JAVA as programming language
* Have POSTGRES and MAVEN
* Access to HEROKU

#### Setup Requirements for Database
* CREATE DATABASE wildlife_tracker;
* \c wildlife_tracker
* CREATE TABLE animals (id serial PRIMARY KEY, name varchar,type VARCHAR,health VARCHAR,age VARCHAR);
* CREATE TABLE locations (id serial PRIMARY KEY,locationName VARCHAR);
* CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, date TIMESTAMP);
* CREATE TABLE rangers (id serial PRIMARY KEY, rangerName varchar, badgeNo varchar);


## copyright and license information
For any further explaination, questions or suggestions, feel free to contact me keza1brenda@gmail.com

License and Copyright information.
Copyright (c) [2020] [Brenda UMUTONIWASE KEZA]

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
