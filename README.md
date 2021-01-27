# leaderboard_gjg

Leaderboard application is a spring application and use Redis and Postgresql
To run the application, database environment should be change.
For postgresql:
  In application.properties url ,username and password should be change.
  
  Exp : 
  
    spring.datasource.url=jdbc:postgresql://localhost:5432/leaderboard?schemas=gjg

    spring.datasource.username=leaderboard

    spring.datasource.password=secret
    
For redis:

  In RedisService.java host name should be change:
  
  Exp : 
    
              try (Jedis jedis = new Jedis("localhost")){


# endpoints

1  -  Leaderboard Top 10     - GET v1/leaderboard

2 -   Leaderboard            - GET v1/leaderboard/{pageNumber}

          {page} --> {1, 2, 3, 4, 5 ... N} 
          Each page has 10 player.
        
3 -   Leaderboard by Country - GET v1/leaderboard/{country}/{pageNumber}

          {page} --> {1, 2, 3, 4, 5 ... N} for pagination
          {country} --> country_iso_code
        
4 - Score Submission     - POST /score/submit 
        
          Sample JSON Request:
          { "uuid" : "1e673707-8380-42bd-a798-ccb29bff834c", "score" : 999.0, "country": "tr" }
      
5 - Create New User      - POST /user/create    
        
          Sample JSON Request:
          { "name" : "Can", "country" : "tr" }

6 - Get User Profile - GET /user/profile/{uuid}

          {uuid} --> Player user id

7 - Create Random user - GET /user/{n}

          {n} --> Number of player, you want to create.
