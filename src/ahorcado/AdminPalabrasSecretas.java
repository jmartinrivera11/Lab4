
package ahorcado;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminPalabrasSecretas {
    
    private List<String> palabrasSecretas;
    private Random random;
    private String[] palabras;
    
    public AdminPalabrasSecretas() {
        palabrasSecretas = new ArrayList<>();
        random = new Random();
        
        agregarPalabra("carro");
        agregarPalabra("cartera");
        agregarPalabra("fresa");
        
        palabras = palabrasSecretas.toArray(String[]::new);
    }
    
    public void agregarPalabra(String palabra) {
        palabrasSecretas.add(palabra);
    }
    
    public String seleccionarPalabraAlAzar() {
        int posicion = random.nextInt(palabrasSecretas.size());
        return palabrasSecretas.get(posicion);
    }
    
    public String[] getPalabras() {
        return palabras;
    }
}
