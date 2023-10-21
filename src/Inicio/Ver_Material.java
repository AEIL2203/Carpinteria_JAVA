package Inicio;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ver_Material {

    public static void Ver_Mat(String TxT) {
        ArrayList<String> materiales = new ArrayList<>();

        try {
            File archivo = new File(TxT); 
            FileReader lector = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(lector);

            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                
                materiales.add(linea);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        for (String material : materiales) {
            System.out.println(material);
        }
    }
}
