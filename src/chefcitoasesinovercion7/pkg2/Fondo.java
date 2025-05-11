
package chefcitoasesinovercion7.pkg2;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;


 public class Fondo {
    private Juego juguito;
    private int anchoFondo = 1300;
    private int altoFondo = 400;
    private int x1 = 1300;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;
    
    private ImageIcon[] fondos;

    public Fondo(Juego jueguito) {
        this.juguito = jueguito;
        fondos = new ImageIcon[3]; // Suponemos que tienes 3 niveles y 3 fondos diferentes
        fondos[0] = new ImageIcon(getClass().getResource("/multimedia/xss.png"));
        fondos[1] = new ImageIcon(getClass().getResource("/multimedia/bosque.png"));
        fondos[2] = new ImageIcon(getClass().getResource("/multimedia/calle.png"));
    }

    public void cambiarNivel(int nuevoNivel) {
        // Verifica si el nuevo nivel es válido (por ejemplo, 1, 2 o 3) antes de cambiar el fondo.
        if (nuevoNivel >= 1 && nuevoNivel <= 3) {
            juguito.nivel = nuevoNivel;
        }
    }

    public void mover() {
        x1 -= 2;
        x2 -= 2;
        if (x1 == 0 && x2 == -1300) {
            x1 = 1300;
            x2 = 0;
        }
    }

    public void paint(Graphics2D g) {
        ImageIcon imagenFondo = fondos[juguito.nivel - 1]; // -1 porque los índices comienzan en 0
        g.drawImage(imagenFondo.getImage(), x1, y1, anchoFondo, altoFondo, null);
        g.drawImage(imagenFondo.getImage(), x2, y2, anchoFondo, altoFondo, null);
    }
}
