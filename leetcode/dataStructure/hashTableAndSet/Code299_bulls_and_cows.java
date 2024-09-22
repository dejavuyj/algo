package leetcode.dataStructure.hashTableAndSet;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Code299_bulls_and_cows {

    public String getHint(String secret, String guess) {
        Map<Character, Set<Integer>> mapSecret = new HashMap<>();
        Map<Character, Set<Integer>> mapGuess = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            Character c = secret.charAt(i);
            Set<Integer> set = mapSecret.getOrDefault(c, new HashSet<>());
            set.add(i);
            mapSecret.put(c, set);
        }
        for (int i = 0; i < guess.length(); i++) {
            Character c = guess.charAt(i);
            Set<Integer> set = mapGuess.getOrDefault(c, new HashSet<>());
            set.add(i);
            mapGuess.put(c, set);
        }

        AtomicInteger cntA = new AtomicInteger(0), cntB = new AtomicInteger(0);
        mapGuess.forEach((c, set) -> {
            Set<Integer> secretSet = mapSecret.get(c);
            if (secretSet != null && secretSet.size() > 0) {
                Set<Integer> guessRetainSet = new HashSet<>(set);
                guessRetainSet.retainAll(secretSet);
                cntA.addAndGet(guessRetainSet.size());

                secretSet.removeAll(guessRetainSet);
                set.removeAll(guessRetainSet);
                cntB.addAndGet(Math.min(set.size(), secretSet.size()));
            }
        });

        return cntA + "A" + cntB + "B";
    }

    public String getHint2(String secret, String guess) {
        int bulls = 0;
        int[] cntS = new int[10];
        int[] cntG = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                cntS[secret.charAt(i) - '0']++;
                cntG[guess.charAt(i) - '0']++;
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; i++) {
            cows += Math.min(cntS[i], cntG[i]);
        }
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        Code299_bulls_and_cows c = new Code299_bulls_and_cows();
        String secret = "1123";
        String guess = "0111";
        System.out.println(c.getHint(secret, guess));
    }
}
