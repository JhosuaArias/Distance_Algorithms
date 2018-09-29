public class Main {


    public static void main(String[] args) {
        int count = 1;
        boolean stepByStep = true;

        for( String[] line : FileHandler.readFile("./resources/test.txt")) {
            System.out.println("Test " + count + ": " + line[0] + " & " + line[1]);

            System.out.println();
            System.out.println("////////////////////////////////////////////");
            System.out.println("//  Distancia Hamming");
            System.out.println("////////////////////////////////////////////");
            System.out.println("Distancia = " + DistanceCalculator.computeHammingDistance(line[0], line[1]));

            System.out.println();
            System.out.println("////////////////////////////////////////////");
            System.out.println("//  Distancia Levenshtein");
            System.out.println("////////////////////////////////////////////");
            System.out.println("Distancia = " + DistanceCalculator.computeLevenshteinDistance(line[0], line[1], stepByStep));
            stepByStep = false;

            System.out.println();
            System.out.println("////////////////////////////////////////////");
            System.out.println("//  Distancia Bigramas");
            System.out.println("////////////////////////////////////////////");
            System.out.println("Distancia = " + DistanceCalculator.computeBigramsDistance(line[0], line[1]));

            System.out.println("-----------------------------------------------------------");
            count++;
        }

    }
}
