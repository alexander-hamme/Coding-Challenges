#!/bin/python3

# Task: write a function that, for each String in the 
# provided String array, calculates the minimal number of 
# character switches (i.e. changing 'a' to 'c' in a String)
# needed to have no repeating characters in the String
#
# The function is expected to return an INTEGER_ARRAY.
# The function accepts STRING_ARRAY words as parameter.

''' 
Example 1 Input:
3
abba
aabbcccba
bbababbba

Example 1 Output:
[1, 3, 2]

------------------
 
Example 2 Input:
4
cbbbbbccacaaa
bbbbbbbbbbbbbbbbb
aaacaaccbbbbaaabb
aaccccbbccbbcccbbcccc

Example 2 Output:
[4, 8, 7, 10]
'''

########################################################################


'''
Returns all indices in the array where the char at 
word[i] == word[i+1]
'''
def getRepeats(word):
    return [i for i in range(len(word)-1) if word[i] == word[i+1]]


"""
This function was designed with the goal of only having to
search through all the characters of each word ONCE,
for efficient analysis of very long strings

First, the indices of adjacent matching characters are
stored in an array. Then, the indices of this array 
are traversed to find the minimal number of operations 
needed to have no repeating characters.
"""
def minimalOperations(words):
    # Write your code here
    operations = []

    for word in words:
        
        # if the number of unique characters in the word
        # is equal to its length, there are no repetitions
        if len(set(word)) == len(word):
            operations.append(0)

        else:
            # each integer in this list is a list index
            # where word[i] == word[i+1]
            repeatIdxs = getRepeats(word)   # O(n).  String is never accessed again after this call.

            # if there is only one repetition, only one change is required            
            if len(repeatIdxs) == 1:
                operations.append(1)
                continue

            # if there is more than one repetition, more changes will be required
            curr = 0         # current index in the list of repetition indices
            numbChanges = 0

            while(curr < len(repeatIdxs)):
                
                # last element in array 
                if (curr == len(repeatIdxs) - 1):
                    numbChanges += 1
                    break

                # if the current repeat was only two letters long,
                # then repeatIdxs[curr] will not be 1 less than repeatIdxs[curr+1]
                # therefore, only one change is required
                elif (repeatIdxs[curr] + 1 != repeatIdxs[curr+1]):
                    numbChanges += 1

                # the current repetition spans more than 2 letters,
                # so the number of adjacent letters is counted, and
                # then divided by two and floored, which gives
                # the number of changes required.
                else:

                    numbAdjacent = 2  # we already know at least 2 matching letters are adjacent

                    # counts the number of sequential indices in repeatIdxs
                    # starting from curr. This corresponds to the number of
                    # repeated letters.
                    while (repeatIdxs[curr] + 1 == repeatIdxs[curr+1]):
                        
                        numbAdjacent += 1
                        curr += 1

                        if (curr >= len(repeatIdxs)-1):  # failsafe
                            break
                    
                    # number of changes required is half the number of repeated letters, floored.
                    # e.g. 7 adjacent letters requires 7 // 2 = 3 changes
                    numbChanges += numbAdjacent // 2

                curr += 1  # move ahead to next number in repeatIdxs

            operations.append(numbChanges)

    return operations



if __name__ == '__main__':

	#fptr = open(os.environ['OUTPUT_PATH'], 'w')

	q = int(input())
	arr = []

	for q_itr in range(q):
		arr.append(input())

	result = minimalOperations(arr)

	#fptr.write(str(result) + '\n')
	print(str(result) + '\n')

    #fptr.close()
