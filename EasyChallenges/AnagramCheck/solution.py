'''
Challenge:
determine whether a is an anagram of b
in less than O(n log n) time
'''
import sys

def anagram(a, b):

	if len(a) != len(b):
		return False

	dct = {}
	for char in a:
		if dct.get(char) == None:
			dct[char] = 1
		else:
			dct[char] += 1

	for char in b:
		if dct.get(char) == None:
			return False
		dct[char] -= 1

	for k in dct.keys():
		if dct.get(k) != 0:
			return False

	return True


if __name__ == "__main__":

	if len(sys.argv) <= 1:
		print("usage: {} word word".format(sys.argv[0]))
	else:
		print(anagram(sys.argv[1], sys.argv[2]))
