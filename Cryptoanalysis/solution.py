# Enter your code here. Read input from STDIN. Print output to STDOUT
from typing import Dict, List
import re

alphabet = tuple([chr(l) for l in range(97, 123)])

""" 
checks if two strings are isomorphic

definition of isomorphic: there exists a one-to-one mapping 
between every unique character of string1 to every unique character of string2,
and all occurrences of corresponding characters between the two strings
match in their position within the string
EG: "aabbc" and "xxyyz" are isomorphic, but "abcd" and "xxyy" are not.
"""
def isomorphic(w1 : str, w2 : str) -> bool:
    
    if len(w1) != len(w2):
        return False

    char1_maps = {}
    char2_occurrences = set()

    # iterate through corresponding chars in words 1 and 2
    # if a given char has not appeared before in word1 (i.e. does not have an entry in char1_maps),
    # but it has already been seen in word 2 (already exists in char2_occurrences),
    # then they are not isomorphic.
    # otherwise, if a char already exists in char1_maps, check that its mapped value
    # matches the corresponding char in word2.
    for char1, char2 in zip(w1, w2):
        if not char1 in char1_maps:
            if char2 in char2_occurrences:  # char1 has not appeared before in word1,
                return False                # but char2 has previously appeared in word2.
            else:
                char1_maps[char1] = char2
                char2_occurrences.add(char2)
        else:
            if char1_maps.get(char1) != char2: # previously mapped value does not match
                return False

    return True


def mapWordsToLengths(full_words_list) -> Dict[int, List[str]]:
    dct_word_counts = {}
    for word in full_words_list:
        len_key = len(word)
        if len_key in dct_word_counts:
            dct_word_counts[len_key].append(word)
        else:
            dct_word_counts[len_key] = [word]
    return dct_word_counts


# use current char map to decipher words and check against list of real words
def checkCipherOnWords(char_map : Dict[str, str], cipher_words, real_words) -> bool:
    for word in cipher_words:
        deciphered = ''.join([char_map.get(c) for c in word])
        
        matched = False
        for real_word in real_words:
            if re.fullmatch(deciphered, real_word) != None:
                matched = True
                break
        if not matched:
            return False
    return True


def getCharMap(cipher_words : List[str], full_words_list : List[str]) -> int:

    # create the smallest subset of words from `cipher_words` that contains all 26 letters
    all_ltrs_cphr = {}
    for wrd in cipher_words:
        for ltr in alphabet:
            if ltr in wrd and not ltr in all_ltrs_cphr:
                # assigns multiple letters to the same word when possible
                all_ltrs_cphr[ltr] = wrd

    words_sub_set = list(set(all_ltrs_cphr.values()))  # a list of *at most* 26 words

    real_words_to_check = []
    dct_word_counts = mapWordsToLengths(full_words_list)
    
    # create the smallest possible subset of words from `full_words_list` which contains 
    # all possible matches for the words in `words_sub_set`
    for wrd in words_sub_set:
        real_words_to_check.extend(
            [w for w in dct_word_counts.get(len(wrd), []) if isomorphic(w, wrd)]
        )

    # add verified character mappings bit by bit as they are discovered
    verifiedMappings = {}
    
    for word in words_sub_set:
		
		# if verifiedMappings contains mappings for all 26 letters
        if len(verifiedMappings) == 26 and all([v != '.' for v in verifiedMappings.items()]):
            break

        same_len_wrds = dct_word_counts.get(len(word))
        iso_words = [w for w in same_len_wrds if isomorphic(word, w)]
        
        for iso in iso_words:
            
            curr_char_map = {l : '.' for l in alphabet}   # "." to match any letter in regex pattern

            for c1,c2 in zip(word, iso):                  # map all corresponding chars
                curr_char_map[c1] = c2
            
            # check if current character mappings are correct
            # if so, add them to verifiedMappings
            if checkCipherOnWords(curr_char_map, words_sub_set, real_words_to_check):
                for k,v in curr_char_map.items():
                    if v != '.':
                        verifiedMappings[k] = v

    return verifiedMappings


def decipher(ciphertext, full_words_list):

    cipher_words = [w.strip() for w in ciphertext.split()]

    charMap = getCharMap(cipher_words, 
            [line.lower().strip() for line in full_words_list
        ])

    deciphered_words = [
        ''.join([charMap.get(c) for c in word]) for word in cipher_words
    ]

    return deciphered_words

if __name__ == "__main__":

    ciphertext = input()
    with open("dictionary.lst", "r") as f:
        full_words_list = f.readlines()

    if (ciphertext in ('', '\n', '\t')):
        print(ciphertext)
    else:
        print(' '.join(decipher(ciphertext, full_words_list)))

