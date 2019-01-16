package pkg;

import javafx.scene.paint.Color;

/**
 * Created by sebo on 1/16/19.
 * janko.sebastian@gmail.com
 */
public enum Colors {
    DEFAULT(Color.web("#000000")),
    ERROR(Color.web("#DC143C")),
    SUCCESS(Color.web("#006400"));

    private Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
