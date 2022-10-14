import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * TspFilter class inherits from the FileFilter class
 * checks for the file extension and filters them
 */
public class TspFilter extends FileFilter {

    /**
     * accept is a checking method of boolean type whichi basically checks if the extension of the uploaded file
     * is tsp or atsp or not
     * overrides the in-built method of accept from the parent class FileFilter
     *
     * @param f inserts the file to check its extension
     * @return gives the value true if the extension is tsp or atsp, otherwise gives null
     */
    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".tsp") ||
                f.getName().toLowerCase().endsWith(".atsp") ;
    }

    /**
     * getDescription method checks if there is any description about the file given or not
     * overrides the getDescription method from the FileFilter class
     *
     * @return gives description if there is any, otherwise null
     */
    @Override
    public String getDescription() {
        return null;
    }
}