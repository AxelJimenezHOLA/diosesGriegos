import java.io.*;
import java.util.*;

public class LectorArchivo {
    private static final String RUTA_ARCHIVO = "input/greek_gods.csv";

    public ListaDoble<Dios> crearListaPrincipal() {
        ListaDoble<Dios> dioses = new ListaDoble<>();
        procesarArchivo(linea -> {
            String[] datos = linea.split(",");
            if (!datos[0].equals("name-english")) {
                agregarDios(dioses, datos);
            }
        });
        dioses.sortAscending();
        return dioses;
    }

    private void agregarDios(ListaDoble<Dios> dioses, String[] datos) {
        String descripcion = String.join("", Arrays.copyOfRange(datos, 4, datos.length));
        Dios dios = new Dios(
                datos[0],  // nomIng
                datos[1],  // nomGriego
                datos[2],  // tipo1
                datos[3],  // tipo2
                descripcion
        );
        dioses.addLast(dios);
    }

    public String[] getSubtipos() {
        Set<String> subtipos = new TreeSet<>();
        procesarArchivo(linea -> {
            String subtipo = linea.split(",")[3];
            if (!subtipo.equals("sub-type")) {
                subtipos.add(subtipo);
            }
        });
        return subtipos.toArray(new String[0]);
    }

    private void procesarArchivo(ProcessLine processor) {
        try (BufferedReader lector = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                processor.process(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se encontró el archivo.");
        } catch (IOException e) {
            System.out.println("Error: no se puede leer.");
        }
    }

    @FunctionalInterface
    private interface ProcessLine {
        void process(String line);
    }
}