package pkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sebo on 12/26/18.
 * janko.sebastian@gmail.com
 */
class Util {
    public static final Character SEPARATOR = ';';

    static void saveToFile(String coords, AtomicReference<File> saveFile) throws FileNotFoundException {
        File f = new File(saveFile.get().toString());
        PrintWriter pw = new PrintWriter(f);

        pw.write("X" + SEPARATOR + "Y" + System.lineSeparator());
        pw.write(coords);

        pw.close();
    }

    static void walkFolder(Path path, XMLVisitor xmlVisitor) throws IOException {
        Files.walkFileTree(path, xmlVisitor);
    }

    static XMLVisitor getXMLVisitor() {
        XMLReader xmlReader = new XMLReader();
        return new XMLVisitor(xmlReader);
    }
}
