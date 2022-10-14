import javax.swing.filechooser.FileFilter;
import java.io.File;

public class TspFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".tsp") ||
                f.getName().toLowerCase().endsWith(".atsp") ;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
