# isql {Version 1.0} [![Build Status](https://travis-ci.org/ruanbotha33/isql.svg?branch=master)](https://travis-ci.org/ruanbotha33/isql)
Quick way to pull records out of MSSQL and put them into a text file.

More DB support to come at a later version for right now only Microsoft SQL is supported.

<a href="https://files.fm/f/key3qdsr">Download The Runnable Jar</a>

## Before you start you need to initialize your db.config file. Save a file in  the same directory as your jar file named "db.config"

```
username=mysqlusername
password=mysqlusernamepassword
connection=jdbc:sqlserver://IPTOMYMACHINE:YOURPORT;databaseName=MYDBNAME
driver=com.microsoft.sqlserver.jdbc.SQLServerDriver  (ONLY DRIVER CURRENTLY SUPPORTED)
```

## Example DB.config file
  ```
  username=dbreporter
  password=dba123
  connection=jdbc:sqlserver://192.168.1.20:1433;databaseName=EMPLOYEES
  driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
  ```

It has to contain the following command arguments

## --Command Line Arguments--

```
[Query Type] [Input File Name OR Query] [Output File Name] [Optional arg: Header]
```

###### Query Type:
  Q This is a raw SQL query directly into command line. Ideally meant for quick Select * from Queries. (Not ideal for large queries)
  F This is an sql query file. For instance if you are running a giant select query in sql just save that sql file and feed it with an F argument

###### Input File Name OR Query
  If Q was selected this is the argument you use to pass in the query.
  If F was selected this is where you pass the file path in.

###### Output File name
  This is the name of the file you would like to output.
  
###### OPTIONAL:
  Header - You can pass an optional header into or leave it blank. If you do not pass this argument in it will default to column names.

###### How to use:
  This application is command line driven.
  
  Windows:
    ```
    java -jar isql.jar [Query Type] [Input File Name OR Query] [Output File Name] [Optional arg: Header]
    ```

###### Examples:
```
Java -jar isql.jar Q "SELECT * FROM dbo.EMPLOYEE" "EMPLOYEES.txt"
Java -jar isql.jar F "Employee.sql" "Employees.txt"
```
###### With Header
```
Java -jar isql.jar Q "SELECT * FROM dbo.EMPLOYEE" "EMPLOYEES.txt" "FirstName|LastName|ID"
Java -jar isql.jar F "Employee.sql" "Employees.txt" "FirstName|LastName|ID"
```
