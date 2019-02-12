# Sick Travelers
## Problem
A group of independent travelers are traveling around the world. Each day they travel to a new location as per their travel schedule. At the end of their travel schedule, they travel again using the same schedule from the beginning. Some of the travelers are diseased. If a healthy traveler is at the same location as a diseased traveler, they become sick.

Each traveler has a name, health condition (one of *HEALTHY*, *SICK*, or *RECOVERING*), and a travel schedule (order of locations they are visiting).

The objective is to trace the health of the travelers until they are all healthy. If all the travelers do not become healthy within a year (365 days), then stop tracing. The travelers transition through their sickness as follows:  
*HEALTHY*: if exposed to a *SICK/RECOVERING* traveler, become *SICK*; otherwise stay *HEALTHY*  
*SICK*: after one day of staying *SICK*, a traveler enters *RECOVERING* stage  
*RECOVERING*: after one day of *RECOVERING*, a traveler becomes *HEALTHY*  

## Input
```
4
John HEALTHY Seattle London Seattle Berlin
Lily RECOVERING Seattle Berlin
Joanna SICK Berlin Berlin London Tokyo
Tim RECOVERING Berlin London London Seattle
```
The first line of the input is the number of travelers. Each of the following lines correspond to a single traveler. Each line has the traveler's name, health and the travel locations separated by a space. The travel locations make up the traveler's schedule in order.

## Output
```
John Lily Joanna Tim
HEALTHY RECOVERING SICK RECOVERING
SICK HEALTHY RECOVERING HEALTHY
RECOVERING SICK HEALTHY HEALTHY
HEALTHY RECOVERING HEALTHY HEALTHY
SICK HEALTHY HEALTHY SICK
RECOVERING HEALTHY SICK RECOVERING
HEALTHY SICK RECOVERING HEALTHY
SICK RECOVERING HEALTHY HEALTHY
RECOVERING HEALTHY HEALTHY SICK
HEALTHY HEALTHY SICK RECOVERING
SICK SICK RECOVERING HEALTHY
RECOVERING RECOVERING HEALTHY HEALTHY
HEALTHY HEALTHY HEALTHY HEALTHY
13
```
The first line of the output is the name of the travelers separated by a space. The following lines print the health condition of the day for each of the corresponding travelers above. For example, on the first day, John is *HEALTHY*, Lily is *RECOVERING*, Joanna is *SICK*, and Tim is *RECOVERING*. The last line is the number of days for all the travelers to become healthy or 365 if at least one of the travelers is still *SICK* or *RECOVERING* after one year.
