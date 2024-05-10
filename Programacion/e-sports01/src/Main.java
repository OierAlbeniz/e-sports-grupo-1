import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Obtener la ruta del archivo de fuente
        String fontFilePath = "src/fonts/BlasterFont-Demo.otf"; // Implementar fuente al proyecto

        // Cargar la fuente desde el archivo
        Font blaster = null;
        try {
            blaster = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath)).deriveFont(Font.PLAIN, 12); // Puedes ajustar el tamaño de la fuente según sea necesario
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}