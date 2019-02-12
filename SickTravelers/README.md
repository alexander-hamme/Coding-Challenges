# Sick Travelers Problem
## Premise:

A group of independent travelers are traveling around the world. Each day they travel to a new location as per their travel schedule. At the end of their travel schedule, they travel again using the same schedule from the beginning. Some of the travelers are diseased. If a healthy traveler is at the same location as a diseased traveler, they become sick.

Each traveler has a name, health condition (one of *HEALTHY*, *SICK*, or *RECOVERING*), and a travel schedule (order of locations they are visiting).

The objective is to trace the health of the travelers until they are all healthy, at which point the program should return the number of days it took to reach this point. If all the travelers do not become healthy within a year (365 days), then stop tracing. The travelers transition through their sickness as follows:  
*HEALTHY*: if exposed to a *SICK/RECOVERING* traveler, become *SICK*; otherwise stay *HEALTHY*  
*SICK*: after one day of staying *SICK*, a traveler enters *RECOVERING* stage  
*RECOVERING*: after one day of *RECOVERING*, a traveler becomes *HEALTHY*  

## Example Input:
```
4
Lily SICK Seattle London Tokyo Berlin
Joanna RECOVERING London Tokyo Berlin Tokyo
John RECOVERING Tokyo Berlin London Seattle Berlin
Tim SICK Berlin Berlin London Seattle
```
The first line of the input is the number of travelers. Each of the following lines correspond to a single traveler. Each line has the traveler's name, health and the travel locations separated by a space. The travel locations make up the traveler's schedule in order.

## Desired Output:
```
Travel Steps:

 John           Lily           Joanna         Tim           
(Tokyo)        (Seattle)      (London)       (Berlin)       
RECOVERING     SICK           RECOVERING     SICK           

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
The first line of the output is the name of the travelers separated by a space. The following lines print the health condition of each of the corresponding travelers on each day, and what location they are currently at. For example, on the first day, John is *HEALTHY*, Lily is *RECOVERING*, Joanna is *SICK*, and Tim is *RECOVERING*. The last line is the number of days for all the travelers to become healthy. If at least one of the travelers is still *SICK* or *RECOVERING* after one year, the number should be 365.
