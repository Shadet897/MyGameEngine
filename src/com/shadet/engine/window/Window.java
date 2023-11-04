package com.shadet.engine.window;

import com.shadet.engine.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics graphics;
    public Window(Container co){
        image = new BufferedImage(co.getWidth(), co.getHeight(), BufferedImage.TYPE_INT_RGB);
        Dimension s = new Dimension((int) (co.getWidth() * co.getScale()), (int) (co.getHeight() * co.getScale()));
        canvas = new Canvas();
        canvas.setPreferredSize(s);
        canvas.setMaximumSize(s);
        canvas.setMinimumSize(s);

        frame = new JFrame(co.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        graphics = bs.getDrawGraphics();
    }

    public void update(){
        graphics.drawImage(image, 0, 0,canvas.getWidth(), canvas.getHeight(), null);
        bs.show();
    }
}
