import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {


    public static boolean isSymmetric(String filepath) {
        return filepath.endsWith(".tsp");
    }

    public static void loadFile(String filepath) {
        ArrayList graph;
        try {
            File f = new File(filepath);
            Scanner sc = new Scanner(f);
            if(isSymmetric(filepath)) {
                graph = parseSymFile(sc);
            } else
                graph = parseAsymFile(sc);
            sc.close();
        } catch (Exception e) {
            System.out.println("Could not read file");
        }

    }

    public static ArrayList parseAsymFile(Scanner scanner) {
            int numCities = 0;
            ArrayList graph = new ArrayList();
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
                                graph.add(row);
                                row = new ArrayList();
                            }
                        }
                    }
                }
            }
            System.out.println(graph.size());
            System.out.println(graph);
            return graph;
    }

    public static ArrayList parseSymFile(Scanner scanner) {
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
        System.out.println(coordinates.size());
        System.out.println(coordinates);
        return coordinates;
    }

    public static void main(String args[]) {
        //FileReader f = new FileReader();
        String path1 = "assets/wi29.tsp";
        String path2 = "assets/br17.atsp";
        //System.out.println(System.getProperty("user.dir"));
        //System.out.println(isSymmetric(path));
        loadFile(path1);
    }
}
