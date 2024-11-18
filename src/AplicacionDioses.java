import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AplicacionDioses {
    private final HashMap<String, ListaDoble<Dios>> listasDioses;
    private final LectorArchivo lectorArchivo;

    private Dios diosSeleccionado;
    private int indiceDios;
    private ListaDoble<Dios> listaSeleccionada;

    public AplicacionDioses() {
        listasDioses = new HashMap<>();
        lectorArchivo = new LectorArchivo();
        listasDioses.put("Todos", lectorArchivo.crearListaPrincipal());
        diosSeleccionado = listasDioses.get("Todos").get(0);
        listaSeleccionada = listasDioses.get("Todos");
        indiceDios = 0;
        agregarSublistas();
    }

    private void agregarSublistas() {
        String[] subtipos = lectorArchivo.getSubtipos();
        for (String subtipo : subtipos) {
            listasDioses.put(subtipo, crearSublista(subtipo));
        }
    }

    private ListaDoble<Dios> crearSublista(String subtipo) {
        ListaDoble<Dios> listaPrincipal = listasDioses.get("Todos");
        ListaDoble<Dios> sublista = new ListaDoble<>();
        for (int i = 0; i < listaPrincipal.size(); i++) {
            Dios diosAComparar = listaPrincipal.get(i);
            if (diosAComparar.getTipoSecundario().equals(subtipo)) {
                sublista.addLast(diosAComparar);
            }
        }
        return sublista;
    }

    public void seleccionarLista(String subtipo) {
        listaSeleccionada = listasDioses.get(subtipo);
        indiceDios = 0;
        diosSeleccionado = listaSeleccionada.get(indiceDios);
    }

    public void elegirOrden(String tipoOrden) {
        switch (tipoOrden) {
            case "Ascendente" -> listaSeleccionada.sortAscending();
            case "Descendente" -> listaSeleccionada.sortDescending();
        }
        indiceDios = 0;
        diosSeleccionado = listaSeleccionada.get(indiceDios);
    }

    public void avanzarEnListaSeleccionada() {
        if (indiceDios < listaSeleccionada.size() - 1) {
            indiceDios++;
        } else {
            indiceDios = 0;
        }
        diosSeleccionado = listaSeleccionada.get(indiceDios);
    }

    public void retrocederEnListaSeleccionada() {
        if (indiceDios > 0) {
            indiceDios--;
        } else {
            indiceDios = listaSeleccionada.size() - 1;
        }
        diosSeleccionado = listaSeleccionada.get(indiceDios);
    }

    public ListaDoble<Dios> getListaSeleccionada() {
        return listaSeleccionada;
    }

    public ImageIcon getImagenDios() {
        File imgFile = new File("imagenes/%s.jpg".formatted(diosSeleccionado));
        return imgFile.exists() ? new ImageIcon(imgFile.toString()) : new ImageIcon("imagenes/not_found.jpg");
    }

    public int getIndiceDios() {
        return indiceDios;
    }

    public Dios getDiosSeleccionado() {
        return diosSeleccionado;
    }

    public String[] getSubtipos() {
        ArrayList<String> subtipos = new ArrayList<>();
        subtipos.add("Todos");
        Collections.addAll(subtipos, lectorArchivo.getSubtipos());
        return subtipos.toArray(new String[0]);
    }
}