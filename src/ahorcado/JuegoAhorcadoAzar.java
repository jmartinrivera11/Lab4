
package ahorcado;

import java.util.Random;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {
    
    private AdminPalabrasSecretas adminPalabrasSecretas;
    private String[] palabrasPosibles;
    private Random random;

    public JuegoAhorcadoAzar(AdminPalabrasSecretas adminPalabrasSecretas) {
        this.adminPalabrasSecretas = adminPalabrasSecretas;
        this.palabraSecreta = adminPalabrasSecretas.seleccionarPalabraAlAzar();
        this.palabraActual = generarPalabraActual(this.palabraSecreta);
        this.intentos = 6;
    }

    public JuegoAhorcadoAzar(String[] palabrasPosibles) {
        this.palabrasPosibles = palabrasPosibles;
        this.random = new Random();
        seleccionarPalabraSecreta();
        this.palabraActual = "_".repeat(palabraSecreta.length());
        this.intentos = 6;

    }

    private void seleccionarPalabraSecreta() {
        int indice = random.nextInt(palabrasPosibles.length);
        palabraSecreta = palabrasPosibles[indice];
    }

    @Override
    public void actualizarPalabraActual(char letra) {
        StringBuilder string = new StringBuilder(palabraActual);
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                string.setCharAt(i, letra);
            }
        }
        palabraActual = string.toString();
    }

    @Override
    public boolean verificarLetra(char letra) {
        return palabraSecreta.contains(String.valueOf(letra));
    }

    @Override
    public boolean hasGanado() {
        return palabraSecreta.equals(palabraActual);
    }

    private String generarPalabraActual(String palabraSecreta) {
        StringBuilder stringActual = new StringBuilder();
        for (int i = 0; i < palabraSecreta.length(); i++) {
            stringActual.append("_");
        }
        return stringActual.toString();
    }
}
