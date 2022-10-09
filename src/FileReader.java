import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {

    public void parseFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                line.trim();
                String arr[] = line.split(" ");
                System.out.println(Arrays.toString(arr));
                if(line.charAt(0) >= '0' && line.charAt(0) <='9') {
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        FileReader f = new FileReader();
        //System.out.println(System.getProperty("user.dir"));
        f.parseFile("../assets/br17.atsp");
    }
}
