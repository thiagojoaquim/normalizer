package com.feefo.normalizer;

import java.util.*;

public class LevenshteinDistanceNormalizer implements Normalizer {

    private List<String> normalizedStrs;

    private Map<String, String> cacheMap;

    public LevenshteinDistanceNormalizer() {
        this.normalizedStrs = normalizedStrs = List.of("Software Engineer", "Accountant", "Quantity surveyor", "“Architect");
        cacheMap = new HashMap<>();
    }

    private int levenshteinDistanceAlg(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = getMin(dp[i - 1][j - 1] + numOfDiffs(str1.charAt(i - 1), str2.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    private int getMin(int... nums) {

        return Arrays.stream(nums).min().orElse(
                Integer.MAX_VALUE);
    }

    private int numOfDiffs(char ch1, char ch2) {
        return ch1 == ch2 ? 0 : 1;
    }

    @Override
    public String normalize(String strToNormalize) {
        if (normalizedStrs.contains(strToNormalize)) {
            return strToNormalize;
        }
        var possibleCache = cacheMap.get(strToNormalize);
        if (Objects.nonNull(possibleCache)) {
            return possibleCache;
        }
        int minDistance = Integer.MAX_VALUE;
        String normalizedStrResult = "";
        for (String normalizedStr : normalizedStrs) {
            int distance = levenshteinDistanceAlg(strToNormalize, normalizedStr);
            if (distance < minDistance) {
                minDistance = distance;
                normalizedStrResult = normalizedStr;
            }
        }
        cacheMap.put(strToNormalize, normalizedStrResult);
        return normalizedStrResult;
    }
}
