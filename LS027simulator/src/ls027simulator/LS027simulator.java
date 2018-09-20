/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ls027simulator;

import com.sun.org.apache.xpath.internal.operations.Bool;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Vincent
 */
public class LS027simulator extends Canvas implements Runnable {

    private static final int WIDTH = 240;
    private static final int HEIGHT = 400;

    private static final int HW_WIDTH = HEIGHT;
    private static final int HW_HEIGHT = WIDTH;

    private static int[] ls027_canvas;

    public InputStream in_buffer;

    public LS027simulator() {
        ls027_canvas = new int[WIDTH * HEIGHT / 8];
    }

    void read_pipe() {

        try {
            // Read response from buffer
            int nb_read = 0;

            if (in_buffer.available() > 0) {

                System.out.println(".");
                //in_buffer.read(ls027_canvas);

                while (nb_read < WIDTH * HEIGHT / 8) {
                    int byte_read = in_buffer.read();
                    if (byte_read >= 0) {
                        ls027_canvas[nb_read++] = byte_read;
                    }
                }

                //in_buffer.reset();
            }

            if (nb_read > 0) {
                System.out.println("Pipe updated, read bytes: " + nb_read);
            }

            this.repaint();

        } catch (IOException e) {
            // do something
            System.out.print(".");
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                this.read_pipe();
                sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(LS027simulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param n
     * @param k Kth bit, from 1 to 8
     * @return
     */
    private static boolean isKthBitSet(int n,
            int k) {

        if ((n & (1 << (k - 1))) >= 1) {
            return true;
        }

        return false;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_BINARY);

        for (int i = 0; i < HW_HEIGHT; i++) {
            for (int j = 0; j < HW_WIDTH / 8; j++) {

                int pixel_grp = ls027_canvas[i * HW_WIDTH / 8 + j];

                //int value = (int) (pixel_grp - '\u0000');

                if (pixel_grp != 0) {
                    //System.out.println("Grp: " + (int)(pixel_grp & 0xFF) + "  " + pixel_grp);
                }

                for (int k = 0; k < 8; k++) {

                    if (!isKthBitSet(pixel_grp, k + 1)) {
                        img.setRGB(HW_HEIGHT - i - 1, j * 8 + k, Color.WHITE.getRGB());
                    } else {
                        img.setRGB(HW_HEIGHT - i - 1, j * 8 + k, Color.BLACK.getRGB());
                        //System.out.println("    Set: " + k);
                    }
                }

            }
        }

//        try {
//            img = ImageIO.read(new File("image.png"));
//            //ImageIO.write(img, "png", new File("image.png"));
//        } catch (IOException e) {
//            System.out.println("Some exception occured " + e);
//        }
        g.drawImage(img, 0, 0, this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LS027simulator sim = new LS027simulator();

        // TODO code application logic here
        JFrame frame = new JFrame();

        frame.setSize(WIDTH, HEIGHT);
        frame.add(sim);

        frame.setVisible(true);
        WindowListener listen = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Hello LS027");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Bye LS027");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        };
        frame.addWindowListener(listen);

        try {
            String hostName = "localhost";
            int portNumber = 8080;

            Socket echoSocket = new Socket(hostName, portNumber);
            sim.in_buffer = echoSocket.getInputStream();

        } catch (IOException ex) {
            Logger.getLogger(LS027simulator.class.getName()).log(Level.SEVERE, null, ex);
        }

        Thread t1 = new Thread(sim, "t1");
        t1.start();
    }
}
