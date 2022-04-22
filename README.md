**DISCLAIMER**: This project is test task for BMW Project. 

## Application description

This is a Java project that supports one HTTP call to another API endpoint:
https://jsonplaceholder.typicode.com/users
- Stores all the data from response in a PostgreSQL database
- Comparing expected data from response with actual one 
- Writes logs to file by path: /src/main/resources/logs/response-logs.txt

## How to run
Here you have two ways to run project:
1. If you want to see your logs file and work with it choose this way:
- Install java 11
- Install docker
- Clone the project by the link.
- Install your IDE what you are preferred
- Then run in your terminal following command in project folder:
   docker-compose up -d postgres
- Run the starter class named like TestTaskApplication in your IDE.
2. If you want just to work with database and requests and don't want to see your logs file, choose this way:
- Install java 11
- Install docker
- Then run in your terminal following command in project folder:
    docker-compose up
## How it works
When project is running, go to your browser or use Postman and go to
http://localhost:8075/api/users

After this you'll see a result of get request with users' data.

Also, you can connect to postgres db via Pgadmin or your IDE and see tables with data of your previous request.

If you started project with first way, after your request you can go to folder "/src/main/resources/logs/" and there will be file with logs of 
all your previous requests.
