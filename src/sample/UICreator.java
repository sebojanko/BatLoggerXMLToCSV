package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sebo on 12/2/18.
 * janko.sebastian@gmail.com
 */

class UICreator {
    private GridPane grid;
    private DirectoryChooser directoryChooser;
    private FileChooser fileChooser;
    private AtomicReference<File> readFolder;
    private AtomicReference<File> saveFile;
    private Button openFolderBtn;
    private Button outputFileBtn;
    private Button convertBtn;
    private Label statusLbl;
    private Stage stage;

    UICreator(Stage stage) {
        this.stage = stage;
        directoryChooser = new DirectoryChooser();
        fileChooser = new FileChooser();
        readFolder = new AtomicReference<>();
        saveFile = new AtomicReference<>();

        setupGrid();
        populateGrid();

        createActions();

        createScene();
    }

    private void setupGrid() {
        grid = new GridPane();
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);
        grid.getColumnConstraints().add(column);

        column = new ColumnConstraints();
        column.setPercentWidth(50);
        grid.getColumnConstraints().add(column);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));
    }

    private void populateGrid() {
        openFolderBtn = new Button("Open folder");
        openFolderBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(openFolderBtn, 0, 0);

        outputFileBtn = new Button("Save to file...");
        outputFileBtn.setMaxWidth(Double.MAX_VALUE);
        grid.add(outputFileBtn, 0, 1);

        convertBtn = new Button("Convert");
        convertBtn.setMaxWidth(Double.MAX_VALUE);
        convertBtn.setMaxHeight(Double.MAX_VALUE);
        grid.add(convertBtn, 1, 0, 1, 2);

        statusLbl = new Label("");
        statusLbl.setMaxWidth(Double.MAX_VALUE);
        grid.add(statusLbl, 0, 3, 2, 1);
    }

    private void createActions() {
        openFolderBtn.setOnAction(e -> {
            openFolder();
        });

        outputFileBtn.setOnAction(e -> {
            saveFile();
        });

        convertBtn.setOnAction(e -> {
            readCoordinates();
        });
    }

    private void openFolder() {
        readFolder.set(directoryChooser.showDialog(stage));
        setLabelText("Opened folder: " + getFilename(readFolder));
    }

    private void saveFile() {
        saveFile.set(fileChooser.showSaveDialog(stage));
        setLabelText("File will be saved to: " + getFilename(saveFile));
    }

    private void readCoordinates() {
        XMLVisitor xmlVisitor = Util.getXMLVisitor();
        walkFolder(xmlVisitor);

        String coords = xmlVisitor.getCoords();

        if (!coords.equals("")) {
            saveCoordinates(coords);
        }
    }


    private void walkFolder(XMLVisitor xmlVisitor) {
        try {
            Util.walkFolder(getPath(readFolder), xmlVisitor);
        } catch (IOException e1) {
            setLabelText("Error occured while searching for XML files");
        }
    }

    private void saveCoordinates(String coordinates) {
        try {
            Util.saveToFile(coordinates, saveFile);
        } catch (FileNotFoundException e1) {
            setLabelText("Error occured while writing to file: " + getFilename(saveFile));
        }
        setLabelText("Coordinates written to file: " + getFilename(saveFile));
    }

    private void createScene() {
        Scene scene = new Scene(new VBox(), 400, 150);
        ((VBox) scene.getRoot()).getChildren().addAll(grid);
        stage.setScene(scene);
        stage.setTitle("BatLogger XML to CSV converter");

        stage.show();
    }

    private String getFilename(AtomicReference<File> file) {
        return file.get().getName();
    }

    private Path getPath(AtomicReference<File> file) {
        return file.get().toPath();
    }

    private void setLabelText(String s) {
        statusLbl.setText(s);
    }
}
