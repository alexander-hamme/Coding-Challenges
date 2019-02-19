### Problem

You are given an array and you need to find the number of tripets of indices `(i,j,k)` such that the elements at those indices are in [geometric progression](https://en.wikipedia.org/wiki/Geometric_progression) for a given common ratio `r` and `i < j < k`.

For example, `arr = [1,4,16,64]`. If `r` = 4, we have `[1,4,16]` and `[4,16,64]` at indices `(0,1,2)` and `(1,2,3)`.

### Function Description

Complete the `countTriplets` function in the editor. It should return the number of triplets forming a geometric progression for a given `r` as an integer.

countTriplets has the following parameter(s):

- arr: an array of integers
- r: an integer, the common ratio

### Input Format

The first line contains two space-separated integers `n` and `r`, the size of `arr` and the common ratio. 
The next line contains `n` space-separated integers `arr[i]`.

### Constraints

- 1 ≤ `n` ≤ 10<sup>5</sup>
- 1 ≤ `r` ≤ 10<sup>9</sup>
- 1 ≤ `arr[i]` ≤ 10<sup>9</sup>

### Output Format

Return the count of triplets that form a geometric progression.

### Sample Input 1

`4 2`

`1 2 2 4`

### Sample Output 1

`2`

Explanation: There are 2 triplets satisfying our criteria, whose indices are `(0,1,3)` and `(0,2,3)`.


### Sample Input 2

`6 3`

`1 3 9 9 27 81`

### Sample Output 2

`6`

Explanation: Triplets satisfying our criteria are at indices `(0,1,2)`, `(0,1,3)`, `(1,2,4)`, `(1,3,4)`, `(2,4,5)`, and `(3,4,5)`.


### Sample Input 3


`5 5`

`1 5 5 25 125`

### Sample Output 3

`4`

Explanation: Triplets satisfying our criteria are at indices `(0,1,3)`, `(0,2,3)`, `(1,3,4)`, and `(2,3,4)`.


Problem available [here](https://www.hackerrank.com/challenges/count-triplets-1/problem) on HackerRank.
