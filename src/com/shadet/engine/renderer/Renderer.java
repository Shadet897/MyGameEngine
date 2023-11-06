package com.shadet.engine.renderer;

import com.shadet.engine.container.Container;
import com.shadet.engine.gfx.Font;
import com.shadet.engine.gfx.Image;
import com.shadet.engine.window.Window;

import java.awt.image.DataBufferInt;

public class Renderer {
    private int pixelWidth;
    private int pixelHeight;
    private int[] pixels;
    private Window window;

    private Font font = Font.STANDART;

    public Renderer(Container co, Window window){
        this.window = window;
        pixelWidth = co.getWidth();
        pixelHeight = co.getHeight();
        pixels = ((DataBufferInt)co.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0x000000;
        }
    }

    public void drawText(String text, int posX, int posY, int color){
        text = text.toUpperCase();
        int offset = 0;

        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;

            for (int y = 0; y < font.getFontImage().getHeight(); y++) {
                for (int x = 0; x < font.getWidth()[unicode]; x++) {
                    if (font.getFontImage().getPixels()[(x + font.getOffset()[unicode]) + y * font.getFontImage().getWidth()] == 0xffffffff){
                        setPixel(x + posX + offset, y + posX, color);
                    }
                }
            }
            offset += font.getWidth()[unicode];
        }
    }

    public void drawImage(Image image, int offX, int offY, int posX, int posY){
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth() ; x++) {
                setPixel(x + offX + posX, y + offY + posY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

    public void setPixel(int x, int y, int value){
        if ((x < 0 || y >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffff00ff){
            return;
        }

        pixels[x + y * pixelWidth] = value;
    }
}
