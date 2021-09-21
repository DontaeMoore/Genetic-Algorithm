Dontae Moore
OUTPUT FILES STORE THE RESULTS OF MY CODE RUNNING 10 TIMES, 1 RUN IN EACH FILE
I think it would be easier to see if my outputs were in different files/

Problem Statement, To find the shortest path that connects each of the 5 cities given in 
the adjacency matrix.

	V1	V2	V3	V4	V5
V1	0	14	4	10	20
V2	14	0	7	8	7
V3	4	5	0	7	16
V4	11	7	9	0	2
V5	18	7	17	4	0

Chromesome encoding: Since every city can connect to every other city, i generated
paths from 1-5 with no duplicate cities.

EX. 23415
    31254
    12543

Each path was then evaluated for the fitness of its travel. A distance from the start of each
path to the end was calculated.

5 3 1 2 4 Distance 43
5 3 2 4 1 Distance 41
5 4 1 3 2 Distance 24

I took the reciprocal of the individual distance and the total distance, this would
allow for a path with a smaller distance to take up more room in the cumulative distance
from 0-1. Thus allowing paths with better fitnesses to reproduce more.

Chromesome Crossover: 
For my crossover, i had two parents. Parent a and b.

45123 parent a
24513 parent b

To generate a child, i calculated a starting index and ending index for parent a
EX. 1,4 ---->  4[5123]
    0,2 ---->  [451]23

Then the new Chromesome would first start with whats inside the indexes for parent a
In the second example, child would start out with 451. Whats inside the brackets.

Then parent 2 would be examined. And we would build the rest of the child from parent 2.
So we would read parent2 from left to right.

if(parent2's element at i index is found inside the brackets)
skip the element, as we dont need it, as the child already contains that city, and we
dont want two of the same city in out paths.

else if(parent2's element is not in brackets)
add the element to the child.

EX.  231[45]
     23541
     
child = whats in brackets
      = 45
look at parent 2, add the elements from parent 2 so long as they are not contained in the brackets
      = 45 231
child = 45231

MUTATION:
Once every child has been created. we send it to a mutation array, which has 
a 1/1000 chance of picking 2 elements from a path and swapping there positions

EX. 45231 mutated from 0 and 2 = 25531. The elements at index 0 and 2 swapped positions.

Feel free to email me about this project anytime at Dontae.moore1023@gmail.com

The fastest path found consistently was 1 3 2 4 5, with a distance of 19




