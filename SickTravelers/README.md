# Sick Travelers Challenge
## Premise:

A group of independent travelers are traveling around the world. Each day they travel to a new location as per their travel schedule. At the end of their travel schedule, they travel again using the same schedule from the beginning. Some of the travelers are sick. If a healthy traveler is at the same location as a sick or recovering traveler, they will also become sick.

Each traveler has a name, health condition (either *HEALTHY*, *SICK*, or *RECOVERING*), and a travel schedule (order of locations they are visiting).

Your objective is to trace the health of the travelers until they are all healthy, at which point the program should return the number of days it took to reach this point. If all the travelers do not become healthy within a year (365 days), then the program should stop tracing. The travelers transition through their sickness as follows:  
*HEALTHY*: if exposed to a *SICK/RECOVERING* traveler, becomes *SICK*; otherwise stays *HEALTHY*  
*SICK*: after one day of staying *SICK*, a traveler enters *RECOVERING* stage. 
*RECOVERING*: after one day of *RECOVERING*, a traveler becomes *HEALTHY*  

## Example Input:
```
4
Lily SICK Seattle London Tokyo Berlin
Joanna RECOVERING London Tokyo Berlin Tokyo
John RECOVERING Tokyo Berlin London Seattle Berlin
Tim SICK Berlin Berlin London Seattle
```
The first line of the input is the number of travelers. Each of the following lines corresponds to a single traveler. Each line has the traveler's name, health and travel locations separated by single spaces. The travel locations make up the traveler's schedule in order.

## Desired Output:  

`8`

The following are the travel steps for solving the above input.
```
 John           Lily           Joanna         Tim           
(Tokyo)        (Seattle)      (London)       (Berlin)       
RECOVERING      SICK           RECOVERING     SICK           

(Berlin)       (London)       (Tokyo)        (Berlin)       
HEALTHY        RECOVERING     HEALTHY        RECOVERING     

(London)       (Tokyo)        (Berlin)       (London)       
SICK           HEALTHY        HEALTHY        HEALTHY        

(Seattle)      (Berlin)       (Tokyo)        (Seattle)      
RECOVERING     HEALTHY        HEALTHY        SICK           

(Berlin)       (Seattle)      (London)       (Berlin)       
HEALTHY        HEALTHY        HEALTHY        RECOVERING     

(Tokyo)        (London)       (Tokyo)        (Berlin)       
SICK           HEALTHY        HEALTHY        HEALTHY        

(Berlin)       (Tokyo)        (Berlin)       (London)       
RECOVERING     HEALTHY        SICK           HEALTHY        

(London)       (Berlin)       (Tokyo)        (Seattle)      
HEALTHY        HEALTHY        RECOVERING     HEALTHY        

(Seattle)      (Seattle)      (London)       (Berlin)       
HEALTHY        HEALTHY        HEALTHY        HEALTHY        

Days of traveling required: 8
```
