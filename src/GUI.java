import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GUI implements IMGDraw, Runnable {

    boolean stop = false;
    int i, j, k, l;

    NumberFormat nf = new DecimalFormat("00");

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
    JLabel displayTime = new JLabel("00:00:00:00");
    JLabel lap1 = new JLabel();
    JLabel lap2 = new JLabel();
    JLabel lap3 = new JLabel();
    JButton start = new JButton("Start");
    JButton lap = new JButton("Lap");

    void init() {
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
    }

    void renderComponents() {
        l1.setBounds(0, 0, 300, 300);
        displayTime.setBounds(50, 10, 200, 50);
        displayTime.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        displayTime.setForeground(Color.red);
        start.setBounds(40, 200, 100, 50);
        lap.setBounds(160, 200, 100, 50);
        lap1.setBounds(50, 70, 200, 20);
        lap2.setBounds(50, 100, 200, 20);
        lap3.setBounds(50, 130, 200, 20);
        start.addActionListener(e ->
        {
            Thread t = new Thread((Runnable) this);

            if (!stop && !start.getText().equals("Stop")) {
                t.start();
                start.setText("Stop");
            } else if (start.getText().equals("Stop")) {
                t.interrupt();
                stop = true;
                start.setText("Restart");
            } else if (start.getText().equals("Restart")) {
                t.start();
                dataclear();
                stop = false;
                start.setText("Stop");
            } else {
                t.interrupt();
            }
            System.out.println(stop + " " + start.getText());
        }
        );

        lap.addActionListener( e -> {
            if (lap1.getText().equals("")) {
                lap1.setText(nf.format(i) + ":" + nf.format(j) + ":" + nf.format(k) + ":" + nf.format(l));
            } else if (!lap1.getText().equals("") && lap2.getText().equals("")) {
                lap2.setText(nf.format(i) + ":" + nf.format(j) + ":" + nf.format(k) + ":" + nf.format(l));
            } else if (!lap2.getText().equals("") && lap3.getText().equals("")) {
                lap3.setText(nf.format(i) + ":" + nf.format(j) + ":" + nf.format(k) + ":" + nf.format(l));
            }
        });


        frame.add(displayTime);
        frame.add(start);
        frame.add(lap);
        frame.add(lap1);
        frame.add(lap2);
        frame.add(lap3);
        frame.add(l1);

    }

    void repaint() {
        frame.setVisible(true);
        frame.repaint();
    }

    void dataclear() {
        i = 0;
        j = 0;
        k = 0;
        l = 0;
        lap1.setText("");
        lap2.setText("");
        lap3.setText("");
    }


    public void run() {
            for (i = 0; ; i++) {
                for (j = 0; j < 60; j++) {
                    for (k = 0; k < 60; k++) {
                        for (l = 0; l < 100; l++) {
                            if (stop) {
                                break;
                            }

                            displayTime.setText(nf.format(i) + ":" + nf.format(j) + ":" + nf.format(k) + ":" + nf.format(l));
                            try {
                                Thread.sleep(9);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
            }
        }
    }
