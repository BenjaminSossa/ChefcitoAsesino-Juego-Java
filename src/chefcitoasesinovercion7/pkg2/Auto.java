package chefcitoasesinovercion7.pkg2;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class Auto {
    Juego jueguito;
    static boolean saltando = false;
    int anchoPersonaje = 112;
    int altoPersonaje = 78;
    static int x_inicial = 50;
    static int y_inicial = 270;
    int x_auxiliar = 0;
    int y_auxiliar = 0;
    boolean disparando = false;
    private KeyEvent eventoTeclado;
    
    private double yVelocidad = 0; // Velocidad vertical del salto
    private double gravedad = 0.35; // Gravedad para simular la caída
    static boolean enSuelo = true;

    public Auto(Juego jueguitoJuego) {
        this.jueguito = jueguitoJuego;
    }

    public void mover() {
        if (x_inicial + x_auxiliar > 0 && x_inicial + x_inicial < jueguito.getWidth() - anchoPersonaje) {
            x_inicial += x_auxiliar;
        }

        if (saltando) {
            if (enSuelo) {
                // Aplicar fuerza de salto
                yVelocidad = -10; // Puedes ajustar esta velocidad de salto
                enSuelo = false;
            }

            // Aplicar gravedad
            yVelocidad += gravedad;

            // Actualizar posición vertical
            y_inicial += yVelocidad;

            // Cuando vuelve al suelo, detener el salto
            if (y_inicial >= 270) {
                y_inicial = 270;
                enSuelo = true;
                saltando = false;
            }
        }
    }

    public void paint(Graphics2D g) {
        ImageIcon auto = new ImageIcon(getClass().getResource("/multimedia/cheficto2.gif"));
        g.drawImage(auto.getImage(), x_inicial, y_inicial, anchoPersonaje, altoPersonaje, null);
    }

    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getExtendedKeyCode() == KeyEvent.VK_UP
 && enSuelo) {
        saltando = true;
    }
}
    public void keyPre(KeyEvent e) {
        eventoTeclado = e;

        if (eventoTeclado.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyChar() == 'w'|| e.getKeyChar() == 'W' || e.getExtendedKeyCode() == KeyEvent.VK_UP && enSuelo ) {
            
            saltando = true;
        } else if (eventoTeclado.getKeyCode() == KeyEvent.VK_Z) {
            disparando = true;
        }
    }

    public void disparar() {
        if (disparando) {
            // Implementa la lógica de disparo aquí
            // Puedes crear objetos para representar los disparos y controlar su movimiento
        }
    }

    public Area getBound() {
        Rectangle forma1 = new Rectangle(x_inicial + 5, y_inicial + 5, 85, 52);
        Area carroceria = new Area(forma1);
        Ellipse2D forma2 = new Ellipse2D.Double(x_inicial + 5, y_inicial + 5, 45, 45);
        Area llantaTrasera = new Area(forma2);
        Ellipse2D forma3 = new Ellipse2D.Double(x_inicial + 55, y_inicial + 5, 45, 45);
        Area llantaDelantera = new Area(forma3);

        Area tractor = carroceria;
        tractor.add(carroceria);
        tractor.add(llantaTrasera);
        tractor.add(llantaDelantera);

        return tractor;
    }
}