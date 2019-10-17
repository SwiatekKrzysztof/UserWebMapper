# UserWebMapper
Web application allowing creation and basic management of user database

FOLDER C:/uploads REQUIRED FOR FILES UPLOAD<br/>
Program parses .csv files, and adds users to in-memmory, temporal database.

## INPUT FORMAT

name;surname;YYYY.mm.DD;phoneNumber

Third parameter is user birthdate, for example:<br/>
john;smith;1985.11.02;111222333

Example WRONG input file can be found in resources/testUgly.csv <br/>
Example RIGHT input file can be found in resources/testPropper.csv

## PARAMETER RULES

name, surname and birthdate are must-have - if any of it are blank or all white spaces,user will not be added.<br/>
Phone number is optional but must be unique - in database there cannot be 2 persons with the same phone number.<br/>
It also needs to be exactly 9 numbers long. Any special characters and white spaces will be deleted from parameters.<br/>.<br/>

## SERVER

Server used is Tomcat, its configuration can be found in .idea/runConfigurations/UserWebMapper.xml<br/>
Project must be run using Tomcat server

