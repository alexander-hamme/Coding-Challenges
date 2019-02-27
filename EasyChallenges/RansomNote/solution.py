#!/bin/python3

# Complete the checkMagazine function below.
# runtime is O(2n + m)
def checkMagazine(magazine, note):
    if len(note) > len(magazine):
        return False

    note_dct = {}

    # O(n)
    for word in note:
        if word in note_dct:
            note_dct[word] += 1
        else:
            note_dct[word] = 1
    
    # O(m)
    for word in magazine:
        if word in note_dct:
            note_dct[word] -= 1

    # worst case O(n)
    for count in note_dct.values():
        if count > 0:
            return False

    return True

if __name__ == '__main__':

    m, n = map(int, input().split())

    magazine = input().rstrip().split()

    note = input().rstrip().split()

    answer = "Yes" if checkMagazine(magazine, note) else "No"

    print(answer)
