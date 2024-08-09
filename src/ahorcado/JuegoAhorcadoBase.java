
package ahorcado;

public abstract class JuegoAhorcadoBase implements JuegoAhorcado {
    
    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    
    public abstract void actualizarPalabraActual(char letra);
    public abstract boolean verificarLetra(char letra);
    public abstract boolean hasGanado();
    
    @Override
    public void inicializarPalabraSecreta() {
    }

    @Override
    public void jugar() {
    }
}
