
package ahorcado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClaseMain extends JFrame {
    
    private JuegoAhorcadoFijo juegoAhorcadoFijo;
    private JuegoAhorcadoAzar juegoAhorcadoAzar;
    private AdminPalabrasSecretas adminPalabrasSecretas = new AdminPalabrasSecretas();
    private JLabel titulo;
    private JLabel textPalabra;
    private JLabel textIntentos;
    private JTextField textLetra;
    private JButton ingresarLetra;
    private JButton ahorcadoFijoBoton;
    private JButton ahorcadoAzarBoton;
    private JButton agregarPalabraBoton;
    private JButton salir;
    
    public ClaseMain() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.YELLOW);
        this.setTitle("AHORCADO");
        
        titulo = new JLabel("AHORCADO", SwingConstants.CENTER);
        titulo.setForeground(Color.BLACK); 
        add(titulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel(new FlowLayout()); 
        panelBotones.setBackground(Color.YELLOW);
        
        ahorcadoFijoBoton = new JButton("Jugar ahorcado fijo");
        ahorcadoAzarBoton = new JButton("Jugaar ahorcado azar");
        agregarPalabraBoton = new JButton("Agregar palabra");
        salir = new JButton("Salir");

        ahorcadoFijoBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jugarAhorcadoFijo();
            }
        });

        ahorcadoAzarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jugarAhorcadoAzar();
            }
        });
        
        agregarPalabraBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                agregarPalabra();
            }
        });
        
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });
        
        ahorcadoFijoBoton.setBackground(Color.BLACK);
        ahorcadoAzarBoton.setBackground(Color.BLACK);
        agregarPalabraBoton.setBackground(Color.BLACK);
        salir.setBackground(Color.RED);
        
        ahorcadoFijoBoton.setForeground(Color.WHITE);
        ahorcadoAzarBoton.setForeground(Color.WHITE);
        agregarPalabraBoton.setForeground(Color.WHITE);
        salir.setForeground(Color.WHITE);
        
        panelBotones.add(ahorcadoFijoBoton);
        panelBotones.add(ahorcadoAzarBoton);
        panelBotones.add(agregarPalabraBoton);
        panelBotones.add(salir);
        add(panelBotones, BorderLayout.CENTER);
        
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.setBackground(Color.YELLOW); 
        
        textPalabra = new JLabel("Palabra: ", SwingConstants.CENTER);
        textPalabra.setForeground(Color.BLACK); 
        textIntentos = new JLabel("Intentos restantes: ", SwingConstants.CENTER);
        textIntentos.setForeground(Color.BLACK); 
        
        textLetra = new JTextField();
        ingresarLetra = new JButton("Ingresar letra");
        ingresarLetra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ingresarLetra();
            }
        });
        
        panel.add(textPalabra);
        panel.add(textIntentos);
        panel.add(textLetra);
        panel.add(ingresarLetra);
        add(panel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void jugarAhorcadoFijo() {
        String[] palabras = adminPalabrasSecretas.getPalabras();
        String seleccionar = (String) JOptionPane.showInputDialog(
                this,
                "Elige una palabra:",
                "Ahorcado fijo",
                JOptionPane.PLAIN_MESSAGE,
                null,
                palabras,
                palabras[0]);

        if (seleccionar != null) {
            juegoAhorcadoFijo = new JuegoAhorcadoFijo(seleccionar);
            actualizarEstadoJuego(juegoAhorcadoFijo.palabraActual, juegoAhorcadoFijo.intentos);
        }
    }
    
    private void jugarAhorcadoAzar() {
        juegoAhorcadoAzar = new JuegoAhorcadoAzar(adminPalabrasSecretas);
        actualizarEstadoJuego(juegoAhorcadoAzar.palabraActual, juegoAhorcadoAzar.intentos);
    }
    
    private void ingresarLetra() {
        String letra = textLetra.getText().trim();
        if (letra.isEmpty() || letra.length() > 1) {
            JOptionPane.showMessageDialog(this, "Ingresa una letra a la vez");
            return;
        }
        
        char letraChar = letra.toLowerCase().charAt(0);
        boolean correcta = false;

        if (juegoAhorcadoFijo != null) {
            correcta = juegoAhorcadoFijo.verificarLetra(letraChar);
            if (correcta) {
                juegoAhorcadoFijo.actualizarPalabraActual(letraChar);
                if (juegoAhorcadoFijo.hasGanado()) {
                    JOptionPane.showMessageDialog(this, "Has ganado! La palabra era: " + juegoAhorcadoFijo.palabraSecreta);
                }
                
            } else {
                juegoAhorcadoFijo.intentos--;
                if (juegoAhorcadoFijo.intentos == 0) {
                    JOptionPane.showMessageDialog(this, "Has perdido! La palabra era: " + juegoAhorcadoFijo.palabraSecreta);
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Letra incorrecta");
                }
            }
            
        } else if (juegoAhorcadoAzar != null) {
            correcta = juegoAhorcadoAzar.verificarLetra(letraChar);
            if (correcta) {
                juegoAhorcadoAzar.actualizarPalabraActual(letraChar);
                if (juegoAhorcadoAzar.hasGanado()) {
                    JOptionPane.showMessageDialog(this, "Has ganado! La palabra era: " + juegoAhorcadoAzar.palabraSecreta);
                }
                
            } else {
                juegoAhorcadoAzar.intentos--;
                if (juegoAhorcadoAzar.intentos == 0) {
                    JOptionPane.showMessageDialog(this, "Has perdido! La palabra era: " + juegoAhorcadoAzar.palabraSecreta);
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Letra incorrecta");
                }
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un modo antes");
        }
        
        if (juegoAhorcadoFijo != null) {
            actualizarEstadoJuego(juegoAhorcadoFijo.palabraActual, juegoAhorcadoFijo.intentos);
        } else if (juegoAhorcadoAzar != null) {
            actualizarEstadoJuego(juegoAhorcadoAzar.palabraActual, juegoAhorcadoAzar.intentos);
        }
    }
    
    private void actualizarEstadoJuego(String palabra, int intentos) {
        textPalabra.setText("Palabra: " + palabra);
        textIntentos.setText("Intentos restantes: " + intentos);
        textLetra.setText("");
    }
    
    private void agregarPalabra() {
        String nuevaPalabra = JOptionPane.showInputDialog(this, "Ingresar una nueva palabra: ");
        if (nuevaPalabra != null && !nuevaPalabra.isEmpty()) {
            adminPalabrasSecretas.agregarPalabra(nuevaPalabra.toLowerCase());
            JOptionPane.showMessageDialog(this, "Palabra agregada");
        }
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClaseMain().setVisible(true);
            }
        });
    }
}
