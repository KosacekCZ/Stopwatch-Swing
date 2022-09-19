import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GUI implements IMGDraw, Runnable {

    boolean stop = false;
    int i, j, k, l;

    BufferedImage img;
    {
        try {
            img = ImageIO.read(new File("images/karbon.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    JFrame frame = new JFrame();
    JLabel l1 = new JLabel(new ImageIcon(img));
    JLabel displayTime = new JLabel();
    JButton start = new JButton("Fungus");

    void init() {
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
    }

    void renderComponents() {
        l1.setBounds(0, 0, 300, 300);
        displayTime.setBounds(50, 10, 200, 50);
        start.setBounds(50, 100, 200, 50);
        start.addActionListener(e ->
        {
            Thread t = new Thread((Runnable) this);

            if (!stop && !start.getText().equals("Stop")) {
                t.start();
                start.setText("Stop");
            } else if (start.getText().equals("Stop")) {
                stop = true;
                start.setText("Restart");
            } else if (start.getText().equals("Restart")) {
                i = 0;
                j = 0;
                k = 0;
                l = 0;
                stop = false;
                start.setText("Stop");
            }
            System.out.println(stop + " " + start.getText());
        }
        );


        frame.add(displayTime);
        frame.add(start);
        frame.add(l1);

    }

    void repaint() {
        frame.setVisible(true);
        frame.repaint();
    }

    public void run() {
        while (!stop) {
            for (i = 0; ; i++) {
                for (j = 0; j < 60; j++) {
                    for (k = 0; k < 60; k++) {
                        for (l = 0; l < 100; l++) {
                            if (stop) {
                                break;
                            }
                            NumberFormat nf = new DecimalFormat("00");
                            displayTime.setText(nf.format(i) + ":" + nf.format(j) + ":" + nf.format(k) + ":" + nf.format(l));
                            try {
                                Thread.sleep(10);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        }
        Thread.interrupted();
    }

}