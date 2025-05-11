package chefcitoasesinovercion7.pkg2;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class Obstaculo {
    private Juego jueguito;
    private Area cabeza, cuerpo, vaca;
    private int anchoObstaculo = 70;
    private int altoObstaculo = 70;
    static int x_inicial = 1300;
    static int y_inicial = 270;
    static int velocidad = 4;
    static int x_auxiliar =-4;

    public Obstaculo(Juego jueguito) {
        this.jueguito = jueguito;
    }
    
   

    public void mover() {
    if (x_inicial <= -100) {
        jueguito.aumentarPuntuacion(1);
        x_inicial = 1300;

        if (Juego.Puntos >= 0 && Juego.nivel == 1) {
            velocidad = 15;
        } else if (Juego.Puntos >= 7 && Juego.nivel == 1) {
            Juego.nivel = 2;
            velocidad = 17; // Ajusta la velocidad para el nivel 2
        } else if (Juego.Puntos >= 15 && Juego.nivel == 2) {
            Juego.nivel = 3;
            velocidad = 22;
        } else if (Juego.Puntos >= 30 && Juego.nivel == 3) {
            
            velocidad = 27;
        } else if (Juego.Puntos >= 50 && Juego.nivel == 3) {
            
            velocidad = 35;
        } else if (Juego.Puntos >= 70 && Juego.nivel == 3) {
            
            velocidad = 40;
            // Puedes agregar más condiciones para niveles superiores si es necesario
        }

    } else {
        if (colision()) {
            if (jueguito.vidas == 0) {
                jueguito.finJuego();
            } else {
                jueguito.pierdeVida();
            }
        } else {
            x_inicial -= velocidad;
        }
    }
}


    public void paint(Graphics2D g) {
        ImageIcon animal = new ImageIcon(getClass().getResource("/multimedia/rataluis.png"));
        g.drawImage(animal.getImage(), x_inicial, y_inicial, anchoObstaculo, altoObstaculo, null);
    }

    public Area getBounds() {
        Ellipse2D forma1 = new Ellipse2D.Double(x_inicial + 5, y_inicial + 5, 10, 30);
        Ellipse2D forma2 = new Ellipse2D.Double(x_inicial + 27, y_inicial + 31, 10, 32);
        cabeza = new Area(forma1);
        cuerpo = new Area(forma2);
        vaca = cabeza;
        vaca.add(cabeza);
        vaca.add(cuerpo);
        return vaca;
    }

    public boolean colision() {
        Area areaA = new Area(jueguito.auto.getBound());
        areaA.intersect(getBounds());
        return !areaA.isEmpty();
    }
}
