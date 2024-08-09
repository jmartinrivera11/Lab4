
package ahorcado;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {
    
    public JuegoAhorcadoFijo(String palabraSecreta) {
        this.palabraSecreta = palabraSecreta;
        this.palabraActual = "_".repeat(palabraSecreta.length());
        this.intentos = 6;
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
}
