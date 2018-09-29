public class Main {


    public static void main(String[] args) {

        for( String[] line : FileHandler.readFile("./resources/test.txt")) {
            System.out.println(line[0] + " " + line[1]);
        }

    }
}
