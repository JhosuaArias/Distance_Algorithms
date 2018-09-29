import java.util.HashSet;
import java.util.Set;

public final class DistanceCalculator {

    /**
     * Computes the Hamming distance between 2 strings.
     * @param string1 a string to compare.
     * @param string2 another string to compare.
     * @return an integer number that indicates the Hamming distance between 2 string. The higher this number is,
     *         the more different are those strings.
     */
    public static int computeHammingDistance(String string1, String string2){
        int string1Length = string1.length();
        int string2Length = string2.length();

        if(string1Length < string2Length) {
            string1 = fillStringWithEmptyChar(string1,string2Length-string1Length);
        }else{
            if(string1Length > string2Length) {
                string2 = fillStringWithEmptyChar(string2,string1Length-string2Length);
            }
        }


        return compareStringsHammingDistance(string1,string2);
    }

    /**
     * Fills a string with \u0000 character as many times as the emptyCharNumber says.
     * @param string the string to fill with empty chars.
     * @param emptyCharNumber the number of empty chars to concatenate.
     * @return the original string fill with empty chars.
     */
    private static String fillStringWithEmptyChar(String string, int emptyCharNumber){
        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < emptyCharNumber; i++) {
            stringBuilder.append((char) 0);
        }
        string = stringBuilder.toString();
        return string;
    }

    /**
     * Compares 2 strings with the same size, and computes the Hamming distance between them.
     * @param string1 a string to compare.
     * @param string2 another string to compare.
     * @return an integer number that indicates the Hamming distance between 2 string. The higher this number is,
     *         the more different are those strings.
     */
    private static int compareStringsHammingDistance(String string1, String string2){
        int hammingDistance = 0; // Zero means that both string are equal.

        for (int i = 0; i < string1.length() ; i++) {
            if(string1.charAt(i) != string2.charAt(i)) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }

    static int computeLevenshteinDistance(String string1, String string2, boolean stepByStep){
        final int X = string1.length();
        final int Y = string2.length();
        int[][] distance = new int[X+1][Y+1];

        int cost;

        for (int i = 0; i <= X; i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= Y; j++) {
            distance[0][j] = j;
        }


        for (int i = 1; i <= X; i++) {
            for (int j = 1; j <= Y; j++) {

                cost = (string1.charAt(i-1) == string2.charAt(j-1))? 0 : 1;

                distance[i][j] = min(
                                        distance[i-1][j] + 1,       // Deletion
                                        distance[i][j-1] + 1,       // Insertion
                                        distance[i-1][j-1] + cost); // Substitution

                if (stepByStep) {
                    System.out.println();
                    System.out.println("Distance Matrix");
                    for (int[] subA : distance) {
                        for (int n : subA) {
                            System.out.print(n + " ");
                        }
                        System.out.println();
                    }
                }
            }
        }

        return distance[X][Y];
    }

    private static int min(int n1, int n2, int n3) {
        if (n1 < n2 && n1 < n3)
            return n1;

        if (n2 < n1 && n2 < n3)
            return n2;

        return n3;
    }

    /**
     * Computes the Hamming distance between 2 strings.
     * @param string1 a string to compare.
     * @param string2 another string to compare.
     * @return an integer number that indicates the Bigrams distance between 2 string. This number is in the range of
     *         [0-1], the more close to 0 is this number, the more different this strings are.
     */
    public static double computeBigramsDistance(String string1, String string2) {
        Set<String> uniqueBigramsString1 = getUniqueBigrams(string1);
        Set<String> uniqueBigramsString2 = getUniqueBigrams(string2);

        double commonBigramsCounter = compareBigrams(uniqueBigramsString1,uniqueBigramsString2);
        return computeBigramsDistanceFormula(commonBigramsCounter,uniqueBigramsString1.size(),uniqueBigramsString2.size());
    }

    /**
     * Processes a string to get all its unique bigrams.
     * @param string the string to get bigrams.
     * @return a Set with all unique Bigrams in a string.
     */
    private static Set<String> getUniqueBigrams(String string){
        Set<String> uniqueBigrams = new HashSet<>();

        String currentBigram;
        for (int i = 0; i < string.length()-1 ; i++) {
            currentBigram = string.substring(i,i+2);
            uniqueBigrams.add(currentBigram);
        }

        return uniqueBigrams;
    }

    /**
     * Compares 2 sets and counts how many are the same.
     * @param uniqueBigramsString1 a set of bigrams.
     * @param uniqueBigramsString2 another set of bigrams.
     * @return the number of common bigrams.
     */
    private static double compareBigrams(Set<String> uniqueBigramsString1, Set<String> uniqueBigramsString2) {
        double commonBigramsCounter = 0;
        for (String bigram : uniqueBigramsString1) {
            if(uniqueBigramsString2.contains(bigram))
                commonBigramsCounter++;
        }
        return commonBigramsCounter;
    }

    /**
     * Computes the Bigrams distance with the given parameters.
     * @param commonBigramsAB the number of common bigrams in the strings A and B.
     * @param uniqueBigramsA the number of unique bigrams in  the string A.
     * @param uniqueBigramsB the number of unique bigrams in  the string B.
     * @return an integer number that indicates the Bigrams distance between 2 string. This number is in the range of
     *         [0-1], the more close to 0 is this number, the more different this strings are.
     */
    private static double computeBigramsDistanceFormula(double commonBigramsAB, double uniqueBigramsA, double uniqueBigramsB) {
        return ((2.0*commonBigramsAB)/(uniqueBigramsA+uniqueBigramsB));
    }
}
