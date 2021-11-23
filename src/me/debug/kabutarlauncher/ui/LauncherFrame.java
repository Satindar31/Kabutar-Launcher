package me.debug.kabutarlauncher.ui;

import me.debug.kabutarlauncher.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;

public class LauncherFrame {

    public static LauncherFrame instance;
    public static JFrame frame;
    public static JPanel panel;




    public LauncherFrame() throws IOException, FontFormatException {
        initialize();
    }

    // intialize the gui
    public static void initialize() throws IOException, FontFormatException {



        URL fonturl = new URL("https://github.com/extremepro999/Kabutar-Utils/raw/main/Montserrat-ExtraBold.ttf");
        URL SmolFontUrl = new URL("https://github.com/extremepro999/Kabutar-Utils/raw/main/Montserrat-Medium.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fonturl.openStream()).deriveFont(Font.BOLD, 55);
        Font SmolFont = Font.createFont(Font.TRUETYPE_FONT, fonturl.openStream()).deriveFont(Font.BOLD, 33);
        frame = new JFrame("Kabutar Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        // frame.setContentPane(panel);
        frame.setBounds(760, 415, 600, 300);
        frame.setIconImage(new ImageIcon(Main.class.getResource("/me/debug/kabutarlauncher/ui/icon.png")).getImage());

        panel = new JPanel();
        panel.setBounds(0, 0, 600, 300);
        panel.setBackground(new Color(44, 47, 51));
//        panel.repaint();  <- Not required.
//        panel.setVisible(true);  <- Not required.





        JLabel label = new JLabel();
        label.setText("Kabutar Launcher");
        label.setForeground(Color.WHITE);
        label.setBounds(3, 3, 100, 100);
        label.setFont(font);


        panel.add(label);  // Added this line.
//        label.setVisible(true);  <- Not required.


        JButton button = new JButton();
        button.setText("Launch");
        button.setFont(SmolFont);
        button.setBounds(250,270,350,290);
        button.setBackground(new Color(44, 47, 51));
        button.setForeground(Color.WHITE);
        button.setVisible(true);




        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {


                        frame.setVisible(false);
                        panel.setVisible(false);
                        Main.launch();


                    } catch (IOException | FontFormatException ioException) {
                        ioException.printStackTrace();
                    }
                }).start();
            }
        });
        panel.add(button);

        frame.getContentPane().add(panel);
        frame.setVisible(true);  // Moved this line.
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        EventQueue.invokeLater(() -> {
            try {
                new LauncherFrame();
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        });
    }
}