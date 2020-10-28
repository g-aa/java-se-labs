package task8_mydrawtest;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class MyDrawTest extends JFrame {

    public MyDrawTest() {
        super("Draw test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension baseSize = new Dimension(400,400);
        this.setMinimumSize(baseSize);
        this.setBackground(new Color(38, 132, 200));
        this.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                //super.paintComponent(g);

                Graphics2D g2D = (Graphics2D)g;
                g2D.setStroke(new BasicStroke(7));

                int figCount = 12;
                Random random = new Random();
                Dimension d = this.getSize();

                for (int c = 0; c < figCount; c++) {
                    Shape shape;
                    g2D.setColor(new Color(random.nextInt()));

                    int pX = random.nextInt(d.width / 5);
                    int pY = random.nextInt(d.height/5);
                    int w = random.nextInt(d.width - 10);
                    int h = random.nextInt(d.height - 10);

                    switch (random.nextInt(3)) {
                        case 1:
                            shape = new Line2D.Double(pX, pY, pX + w, pX + h);
                            break;
                        case 2:
                            shape = new Rectangle2D.Double(pX, pY, w, h);
                            break;
                        default:
                            shape = new Ellipse2D.Double(pX, pY, w, h);
                            break;
                    }
                    g2D.draw(shape);
                }
            }
        });
        this.setVisible(true);
    }
}
