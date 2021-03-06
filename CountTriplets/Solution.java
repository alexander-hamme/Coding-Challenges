import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * To pass not just most, but all of HackerRank's unit tests for this challenge, 
 * which have strict time limits, I ended up making three solutions:
 * 
 * countTriplets3, the O(n) solution that finally passed all the tests
 
 * countTriplets2, the O(n*k*j) solution that passed 10/13 tests but failed 3 
 *                 for taking too long
 *
 * countTriplets1, the O(n^3) brute force solution that passed maybe 5 tests
 *                 before timing out on the rest
 */
public class Solution {

    // O(n) solution. Increases the count retroactively upon reaching
    // each third tuple value, instead of searching forward from each
    // would-be first value to find second and third values.
    static long countTriplets3(List<Long> lst, long r) {

        HashMap<Long,Long> seconds = new HashMap<>();
        HashMap<Long,Long> thirds = new HashMap<>();

        Long count = 0L;
        for (Long key : lst) {

            // check if `key` exists as a key in thirds.
            // if so, then both key/r and key/r/r have previously occurred in lst
            // as many times as the count value currently mapped to `key` in thirds.
            count += thirds.getOrDefault(key, 0L);

            // if `key` exists in seconds, then key/r has previously occurred 
            // in lst, so map `key*r` in thirds to (or increment its count by)
            // however many times key has occurred in seconds.
            if (seconds.containsKey(key)) {

                // if key exists in seconds, map key*r as a key in thirds
                // to the count of key in seconds + the count of key*r in thirds
                thirds.put(
                    key*r, 
                    seconds.get(key) + thirds.getOrDefault(key*r, 0L)
                );
            }
            // increment count of `key*r` in seconds hashmap by 1
            seconds.compute(key*r, (k,val) -> (val == null) ? 1 : val+1);
        }
        return count;
    }

    // O(n*k*j + n) --> (probably ends up being around O(n^2)). 
    // Creates (number->[arr indices]) hashmap, then iterates
    // through indices to count occurrences of geometric triplets
    static long countTriplets2(List<Long> lst, long r) {

        HashMap<Long, ArrayList<Integer>> number_counts = new HashMap<>(lst.size());

        int i=0;
        for (Long numb : lst) {

            ArrayList<Integer> indices = number_counts.get(numb); // putIfAbsent(numb, 1);
            if (indices == null) {
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                number_counts.put(numb, tmp);
            } else {
                indices.add(i);
            }
            i++;
        }

        int tuple_count = 0;

        int indx1 = 0;
        for (Long numb1 : lst) {  // let k = average indices2.size, j = average indices3.size, then this is O(n*k*j)

            Long mult2 = numb1 * r;
            Long mult3 = numb1 * r * r;

            ArrayList<Integer> indices2 = number_counts.get(mult2);  
            ArrayList<Integer> indices3 = number_counts.get(mult3);

            if (indices2 != null && indices3 != null) {

                for (Integer indx2 : indices2) {

                    if (indx2 > indx1) {

                        for (Integer indx3 : indices3) {

                            if (indx3 > indx2) {
                                tuple_count++;
                            }
                        }
                    }
                }
            }
        }
        return tuple_count;
    }


    // Brute force, O(n^3)
    static long countTriplets1(List<Long> lst, long r) {

        // convert to array for quicker access
        Long[] arr = new Long[lst.size()];
        lst.toArray(arr);

        int idx1, idx2, idx3;
        int end = arr.length; 

        Long first, second, third;

        long tuple_count = 0;

        for(idx1 = 0; idx1 <= end-3; idx1++) {

            first = arr[idx1];

            for (idx2 = idx1+1; idx2 < end; idx2++) {

                second = arr[idx2];
            
                // all elements are > 0 so using modulo and division is safe
                if (second % first == 0 && second / first == r) {   

                    for (idx3 = idx2+1; idx3 < end; idx3++) {
                        third = arr[idx3];
                        if (third % second == 0 && third / second == r) {
                            tuple_count++;
                        }
                    }
                }
            }
        }
    return tuple_count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets3(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
