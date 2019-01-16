package sample;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by sebo on 12/2/18.
 * janko.sebastian@gmail.com
 */
public class XMLVisitor extends SimpleFileVisitor {

    private String coords = "";
    private XMLReader xmlReader;

    XMLVisitor(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) {
        if (isExtensionXML((Path) file)) {
            coords += getCoords((Path) file);
        }
        return FileVisitResult.CONTINUE;
    }

    private String getCoords(Path file) {
        if (prepareCoordsToCSV(readXML(file)).equals(""))
            return "";
        else
            return prepareCoordsToCSV(readXML(file)) + "\n";
    }

    private String prepareCoordsToCSV(String readXML) {
        return readXML.replace(' ', ',');
    }

    private boolean isExtensionXML(Path file) {
        return file.getFileName().toString().substring(file.getFileName().toString().length() - 3).equals("xml");
    }

    private String readXML(Path file) {
        return xmlReader.findGPSPosition(file.toFile());
    }

    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    String getCoords() {
        return coords;
    }
}

