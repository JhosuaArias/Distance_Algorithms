import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class in charge of reading files used for testing.
 */
class FileHandler {

    /**
     * Reads the file specified by a path
     * @param path path to a file
     * @return the file in a String Array List, the String array contains the pair of terms.
     */
    static List<String[]> readFile(String path) {
        List<String[]> stringArray = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(path))) {

            stream.forEach( (String line) -> stringArray.add(line.split(",")) );

        } catch (IOException e) {
            System.err.println("File does not exist or locked");
            System.exit(99);
        }

        return stringArray;
    }


}
