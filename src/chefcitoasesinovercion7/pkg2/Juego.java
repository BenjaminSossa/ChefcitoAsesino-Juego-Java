
package chefcitoasesinovercion7.pkg2;

import static chefcitoasesinovercion7.pkg2.Principal.reiniciarJuego;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Juego extends JPanel{
    
    
    //sonidos
    URL direccionesSonidoSalto, direccionesSonidoChoque;
    AudioClip sonidoChoque, sonidoSalto;
   //objetos de las otras clases
    Auto auto =new Auto (this);
    Obstaculo obstaculo=new Obstaculo(this);
    Fondo fondo =new Fondo(this);
    //varriables
    public static boolean juegoFinalizado=false;
    public static boolean pierdeVida = false;
    public static int vidas = 3;
    public static int Puntos=0;
    public static int nivel=1;
    
    //rastrear
    public static int nivels = 1;
    public static boolean avanzadoANivel2 = false;
    //voladores
    
    
    private JFrame contenedor;
     private boolean pausado = false;
    
    public int obtenerPuntuacion() {
        return Puntos;
    }

    public void avanzarNivel() {
    if (Puntos >= 3 && nivel == 1) {
        nivel = 2;
    } else if (Puntos >= 5 && nivel == 2) {
        nivel = 3;
    }
}
    
    
     public int getSuelo() {
        // Aquí debes proporcionar la lógica para obtener la posición del suelo
        // En tu código original, parece ser 270, pero puede depender de tu implementación
        return 270;
    }

    
    public Auto getAuto() {
        return auto;
    }
     
     public void aumentarPuntuacion(int puntosGanados) {
    Puntos += puntosGanados;
    if (Puntos >= 10 && nivel == 1) {
        avanzarNivel();
    }
}
 
     
  
     
     
    
    public Juego(JFrame contenedor){
        direccionesSonidoChoque=getClass().getResource("/multimedia/choque.wav");
        sonidoChoque = Applet.newAudioClip(direccionesSonidoChoque);
        
        direccionesSonidoSalto=getClass().getResource("/multimedia/salto.wav");
        sonidoSalto= Applet.newAudioClip(direccionesSonidoSalto);
        
        this.contenedor = contenedor;
        
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //el salto se activa cuando se presiona la tecla espacio
                if (e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyChar() == 'w'|| e.getKeyChar() == 'W' || e.getExtendedKeyCode() == KeyEvent.VK_UP ){
                sonidoSalto.play();
                auto.keyPressed(e);
                }
            }

           @Override
public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        pausado = !pausado; // Cambia el estado de pausa

        if (pausado) {
            // Realiza cualquier acción adicional al pausar el juego (opcional)
            // Por ejemplo, puedes detener la música, mostrar un mensaje de pausa, etc.
        } else {
            // Realiza cualquier acción adicional al reanudar el juego (opcional)
            // Por ejemplo, puedes reanudar la música, ocultar el mensaje de pausa, etc.
        }
    } else {
        // Otro código para manejar otras teclas liberadas (si es necesario)
    }
}
    });
 
        setFocusable(true);
}
    
    public void mover() {
    if (!pausado) {
        obstaculo.mover();
        auto.mover();
        fondo.mover();
    }
}

@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    dibujar(g2);
    dibujarPuntaje(g2);
}
public void dibujar(Graphics2D g) {
    fondo.paint(g);
    auto.paint(g);
    obstaculo.paint(g);
    mover();
}

public void dibujarPuntaje (Graphics2D g){

    Graphics2D g1=g, g2=g;
    Font score=new Font ("Arial", Font.BOLD, 30);
    g.setFont(score);
    g.setColor(Color.RED);
    g1.drawString("Puntaje: "+Puntos, 1100, 30 );
    g1.drawString("vidas: "+vidas, 20, 30 );
    g1.drawString("Nivel: "+nivel, 570, 30 );
    
    
    if (juegoFinalizado){
    g2.setColor(Color.yellow);
    g2.drawString("HAS PERDIDO", ((float)getBounds().getCenterX()/2)+170, 70) ;
    
    
    }

}

public void finJuego() {
    juegoFinalizado = true;
    sonidoChoque.play();

    if (reiniciarJuego == 1) {
        // Abre la ventana de la base de datos con la puntuación del jugador completada
        
    }
}


    





public void pierdeVida(){

    
    sonidoChoque.play();
    pierdeVida=true;

}

void juegofinakizado(){
        throw new UnsupportedOperationException("dont know");

    }



}
