package chefcitoasesinovercion7.pkg2;

import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.SwingUtilities;

public class Principal {
    public static int reiniciarJuego = -1;
    private static boolean bloquearTeclado = false;
    private static JFrame contenedor;

    public static boolean Comparador = true;
    public static boolean Comparador2 = false;

    public static void main(String[] args) {

        if (Comparador) {
            Inicio2 cv = new Inicio2();
            cv.setVisible(true);
        }

        if (Comparador2) {
            //JOptionPane.showMessageDialog(null, "Ready?");

            SwingUtilities.invokeLater(() -> {
                contenedor = new JFrame("Jueguito");
                Juego Jueguito = new Juego(contenedor);
                contenedor.add(Jueguito);
                contenedor.setSize(1300, 400);
                contenedor.setLocation(70, 200);
                contenedor.setVisible(true);
                contenedor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        while (true) {
                            if (Jueguito.juegoFinalizado) {
                                if (!bloquearTeclado) {
                                    reiniciarJuego = JOptionPane.showConfirmDialog(
                                        null,
                                        "Has perdido, ¿quieres jugar de nuevo?",
                                        "Has perdido",
                                        JOptionPane.YES_NO_OPTION
                                    );

                                    if (reiniciarJuego == JOptionPane.YES_OPTION) {
    bloquearTeclado = true; // Bloquear el teclado
    reiniciarValores();
    bloquearTeclado = false; // Desbloquear el teclado
} else if (reiniciarJuego == JOptionPane.NO_OPTION) {
    contenedor.dispose();
    Comparador2 = false; // Agregado para volver al inicio
    Comparador = false; // Agregado para volver al inicio
    reiniciarJuego = -1;
    
    // Abre el JFrame "Jugadores"
    SwingUtilities.invokeLater(() -> {
        Jugadores jugadores = new Jugadores();
        jugadores.setVisible(true);
    });
    break; // Salir del bucle
}
                                }
                            } else {
                                Jueguito.repaint();

                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                if (Juego.pierdeVida) {
                                    JOptionPane.showMessageDialog(null, "Pierde vida T_T ");
                                    Juego.pierdeVida = false;
                                    Juego.vidas--;
                                    Auto.y_inicial = 270;
                                    Auto.saltando = false;
                                    Obstaculo.x_inicial = 1300;
                                }
                            }
                        }
                        return null;
                    }
                };

                worker.execute();
            });
        }
    }

    public static void reiniciarValores() {
        Juego.juegoFinalizado = false;
        Obstaculo.x_auxiliar = -4;
        Juego.Puntos = 0;
        Juego.nivel = 1;
        Juego.vidas = 3;
        reiniciarJuego = -1;
        Obstaculo.x_inicial = 1300;
        Obstaculo.velocidad = 4;

        // Restablecer las condiciones del personaje
        Auto.saltando = false;
        Auto.enSuelo = true;
    }
}
