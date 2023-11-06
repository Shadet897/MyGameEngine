package com.shadet.engine.renderer;

import com.shadet.engine.container.Container;
import com.shadet.engine.gfx.Image;

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
            pixels[i] = 0x000000;
        }
    }

    public void setPixel(int x, int y, int value){
        if ((x < 0 || y >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffff00ff){
            return;
        }

        pixels[x + y * pixelWidth] = value;
    }

    public void drawImage(Image image, int offX, int offY){
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth() ; x++) {
                setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }
}
