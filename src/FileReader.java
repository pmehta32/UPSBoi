import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * FileReader is the public class which takes the file from the user and reads it.
 * <p>
 * Button will allow user to upload the file with loaction points
 */
public class FileReader {
    /**
     * Checks whether the uploaded file is symmetric or not
     * uses once to check the symmetric file for parsing it later on in the code
     *
     * @param filepath string that specifies the path of the uploaded file
     * @return checks if the uploaded file has an extension of tsp and returns true, else false
     */
    public static boolean isSymmetric(String filepath) {
        return filepath.endsWith(".tsp");
    }
    /**
     * loadFile method of type Graph loads the uploaded file in the system
     * has attributes for Graph to plot and access them in future
     *
     * @param filepath string that shows the file uploaded by the user
     * @return displays the graph if the function can read the file, otherwise null
     * @exception Exception if file does not exist
     */
    public static Graph loadFile(String filepath) {
        Graph graph;
        try {
            File f = new File(filepath);
            Scanner sc = new Scanner(f);
            if(isSymmetric(filepath)) {
                graph = parseSymFile(sc);
            } else
                graph = parseAsymFile(sc);
            sc.close();
            return graph;
        } catch (Exception e) {
            System.out.println("Could not read file");
        }
        return null;
    }
    /**
     * parses the asymmetric file upladed by the user which has a Graph type
     * uses once to check the asymmetric file for parsing it in the loadFile method
     * for the route, method makes an array which later on displays it in the array[0]
     *
     * @param scanner scans the uploaded file with a type Scanner
     * @return displays the graph of the uploaded file which has asymmetric loaction points
     */
    public static Graph parseAsymFile(Scanner scanner) {
        int numCities = 0;
        ArrayList edges = new ArrayList();
        ArrayList row = new ArrayList();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.trim().replaceAll(" +", " ");
            String arr[] = line.split(" ");

            if (arr[0].startsWith("DIMENSION")) {
                numCities = Integer.valueOf(arr[arr.length - 1]);
            }
            if (!arr[0].matches("[A-Z].*")) {
                for (String s : arr) {
                    if (s.length() > 0) {
                        Float num = Float.valueOf(s);
                        row.add(num);
                        if (row.size() == numCities) {
                            edges.add(row);
                            row = new ArrayList();
                        }
                    }
                }
            }
        }
        Graph graph = new Graph(edges.size());
        graph.setEdges(edges);
        return graph;
    }

    /**
     * parses the symmetric file upladed by the user which has a Graph type
     * uses once to check the symmetric file for parsing it in the loadFile method
     * for the route, method makes an array which later on displays it in the array[1] and array[2]
     * float has been used for the attributes because some co-ordinates have float values
     *
     * @param scanner scans the uploaded file with a type Scanner
     * @return displays the graph of the uploaded file which has symmetric loation points
     */
    public static Graph parseSymFile(Scanner scanner) {
        ArrayList coordinates = new ArrayList();
        ArrayList row;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.trim().replaceAll(" +", " ");
            String arr[] = line.split(" ");
            row = new ArrayList();
            if(!arr[0].matches("[A-Z].*")) {
                row.add(Float.valueOf(arr[1]));
                row.add(Float.valueOf(arr[2]));
                coordinates.add(row);
            }
        }
        Graph graph = new SymGraph(coordinates.size(), coordinates);
        return graph;
    }

}
