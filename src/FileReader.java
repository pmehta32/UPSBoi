import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
* FileReader is the public class which takes the file from the user and reads it.
* <p>
* Button will allow user to upload the file with loaction points
*/
public class FileReader {

/** 
* Checks whether the uploaded file is symmetric or not
*
* @param filepath string that specifies the path of the uploaded file
* @return displays the path of the file with an extension of tsp
* @since 
*/
    public static boolean isSymmetric(String filepath) {
        return filepath.endsWith(".tsp");
    }
/**
* Loads file in the system 
* 
* @param filepath string that shows the file path uploaded by the user
* @return displays the graph if the function can read otherwise null
* @exception 
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
* parses the asymmetric file upladed by the user which has a Graph datatype
* 
* @param scanner scans the uploaded file with a datatype Scanner
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

                if(arr[0].startsWith("DIMENSION")) {
                    numCities = Integer.valueOf(arr[arr.length-1]);
                }
                if(!arr[0].matches("[A-Z].*")) {
                    for(String s: arr) {
                        if(s.length()>0) {
                            Float num = Float.valueOf(s);
                            row.add(num);
                            if(row.size() == numCities) {
                                edges.add(row);
                                row = new ArrayList();
                            }
                        }
                    }
                }
            }
            //System.out.println(edges.size());
            //System.out.println(edges);
            Graph graph = new Graph(edges.size());
            graph.setEdges(edges);
            return graph;
    }
/**
* parses the symmetric file upladed by the user which has a Graph datatype
* 
* @param scanner scans the uploaded file with a datatype Scanner
* @return displays the graph of the uploaded file which has symmetric loaction points
*/
    public static Graph parseSymFile(Scanner scanner) {
        //int numCities = 0;
        ArrayList coordinates = new ArrayList();
        ArrayList row;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.trim().replaceAll(" +", " ");
            String arr[] = line.split(" ");
            //if(arr[0].startsWith("DIMENSION")) {
             //   numCities = Integer.valueOf(arr[arr.length-1]);
           // }
            row = new ArrayList();
            if(!arr[0].matches("[A-Z].*")) {
                //row.add(Integer.valueOf(arr[0]));
                row.add(Float.valueOf(arr[1]));
                row.add(Float.valueOf(arr[2]));
                coordinates.add(row);
            }
        }
        //System.out.println(coordinates.size());
        //System.out.println(coordinates);
        Graph graph = new SymGraph(coordinates.size(), coordinates);
        return graph;
    }
/**
* calls every method created in this java file for the application
* 
* @param args 
*/
    
    public static void main(String args[]) {
    //     FileReader f = new FileReader();
    //     String path1 = "assets/wi29.tsp";
    //     String path2 = "assets/br17.atsp";
    //     System.out.println(System.getProperty("user.dir"));
        // System.out.println(isSymmetric(path));
    //     Graph g = loadFile(path1);
    //     g.printEdges();
    }
}
