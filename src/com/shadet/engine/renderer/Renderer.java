package com.shadet.engine.renderer;

import com.shadet.engine.container.Container;

import java.awt.image.DataBufferInt;

public class Renderer {
    private int pixelWidth;
    private int pixelHeight;
    private int[] pixels;

    public Renderer(Container co){
        pixelWidth = co.getWidth();
        pixelHeight = co.getHeight();
        pixels = ((DataBufferInt)co.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] += 0;
        }
    }
}
