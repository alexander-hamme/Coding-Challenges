# Sick Travelers Challenge
### Premise:

A group of independent travelers are traveling around the world. Each day they travel to a new location as per their travel schedule. At the end of their travel schedule, they travel again using the same schedule from the beginning. Some of the travelers are sick. If a healthy traveler is at the same location as a sick or recovering traveler, they will also become sick.

Each traveler has a name, health condition (either *HEALTHY*, *SICK*, or *RECOVERING*), and a travel schedule (order of locations they are visiting).

Your objective is to trace the health of the travelers until they are all healthy, at which point the program should return the number of days it took to reach this point. If all the travelers do not become healthy within a year (365 days), then the program should stop tracing. 

The travelers transition through their sickness as follows:  
*HEALTHY*: if exposed to a *SICK/RECOVERING* traveler, becomes *SICK*; otherwise stays *HEALTHY*  
*SICK*: after one day of staying *SICK*, a traveler enters *RECOVERING* stage. 
*RECOVERING*: after one day of *RECOVERING*, a traveler becomes *HEALTHY*  

### Example Input 1:
```
4
Chris RECOVERING London Tokyo Berlin Tokyo
Marie RECOVERING Tokyo Berlin London Seattle Berlin
Rachel SICK Seattle London Tokyo Berlin
Matt SICK Berlin Berlin London Seattle
```
The first line of the input is the number of travelers. Each of the following lines corresponds to a single traveler. Each line has the traveler's name, health and travel locations separated by single spaces. The travel locations make up the traveler's schedule in order.

### Output:  

8

Steps to the solution:
```
 Chris          Marie          Rachel         Matt           
(London)       (Tokyo)        (Seattle)      (Berlin)       
RECOVERING     RECOVERING      SICK           SICK           

(Tokyo)        (Berlin)       (London)       (Berlin)       
HEALTHY        HEALTHY        RECOVERING     RECOVERING     

(Berlin)       (London)       (Tokyo)        (London)       
HEALTHY        SICK           HEALTHY        HEALTHY        

(Tokyo)        (Seattle)      (Berlin)       (Seattle)      
HEALTHY        RECOVERING     HEALTHY        SICK           

(London)       (Berlin)       (Seattle)      (Berlin)       
HEALTHY        HEALTHY        HEALTHY        RECOVERING     

(Tokyo)        (Tokyo)        (London)       (Berlin)       
HEALTHY        SICK           HEALTHY        HEALTHY        

(Berlin)       (Berlin)       (Tokyo)        (London)       
SICK           RECOVERING     HEALTHY        HEALTHY        

(Tokyo)        (London)       (Berlin)       (Seattle)      
RECOVERING     HEALTHY        HEALTHY        HEALTHY        

(London)       (Seattle)      (Seattle)      (Berlin)       
HEALTHY        HEALTHY        HEALTHY        HEALTHY              

Days of traveling required: 8
```




### Example Input 2:

```
6
Marie HEALTHY London Tokyo Seattle London
Lynn SICK Berlin Tokyo London Seattle Berlin
Chris SICK Tokyo London Tokyo Berlin
Tyrin RECOVERING Berlin Berlin London Seattle
Rachel HEALTHY Tokyo Seattle Berlin
Will RECOVERING London Tokyo Seattle
```

### Output: 

6

Steps to the solution:
```
 Marie         Lynn           Chris          Tyrin          Rachel         Will          
(London)       (Berlin)       (Tokyo)        (Berlin)       (Tokyo)        (London)       
HEALTHY         SICK           SICK          RECOVERING     HEALTHY        RECOVERING     

(Tokyo)        (Tokyo)        (London)       (Berlin)       (Seattle)      (Tokyo)        
SICK           RECOVERING     RECOVERING     HEALTHY        SICK           HEALTHY        

(Seattle)      (London)       (Tokyo)        (London)       (Berlin)       (Seattle)      
RECOVERING     HEALTHY        HEALTHY        HEALTHY        RECOVERING     SICK           

(London)       (Seattle)      (Berlin)       (Seattle)      (Tokyo)        (London)       
HEALTHY        HEALTHY        HEALTHY        HEALTHY        HEALTHY        RECOVERING     

(London)       (Berlin)       (Tokyo)        (Berlin)       (Seattle)      (Tokyo)        
SICK           HEALTHY        HEALTHY        HEALTHY        HEALTHY        HEALTHY        

(Tokyo)        (Berlin)       (London)       (Berlin)       (Berlin)       (Seattle)      
RECOVERING     HEALTHY        HEALTHY        HEALTHY        HEALTHY        HEALTHY        

(Seattle)      (Tokyo)        (Tokyo)        (London)       (Tokyo)        (London)       
HEALTHY        HEALTHY        HEALTHY        HEALTHY        HEALTHY        HEALTHY

Steps required: 6
```

A few more test cases are in the `test_cases.txt` file, along with the correct number of steps for each
