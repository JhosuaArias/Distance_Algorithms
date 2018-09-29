public class Main {


    public static void main(String[] args) {
        int count = 1;

        for( String[] line : FileHandler.readFile("./resources/test.txt")) {
            System.out.println("Test " + count + ": " + line[0] + " & " + line[1]);

            System.out.println();
            System.out.println("////////////////////////////////////////////");
            System.out.println("//  Distancia Hamming");
            System.out.println("////////////////////////////////////////////");
            try {
                System.out.println("Distancia = " + DistanceCalculator.computeHammingDistance(line[0], line[1]));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Los strings son de tamaños distintos, no aplica para Hamming.");
            }

            System.out.println();
            System.out.println("////////////////////////////////////////////");
            System.out.println("//  Distancia Levenshtein");
            System.out.println("////////////////////////////////////////////");
            System.out.println("Distancia = " + DistanceCalculator.computeLevenshteinDistance(line[0], line[1]));

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
