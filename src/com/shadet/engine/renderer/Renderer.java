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
    private int[] zBuffer;
    private int zDepth;

    private Window window;

    public Renderer(Container co, Window window){
        this.window = window;
        pixelWidth = co.getWidth();
        pixelHeight = co.getHeight();
        pixels = ((DataBufferInt)co.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zBuffer = new int[pixels.length];
    }

    public void clear(int color){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
            zBuffer[i] = 0;
        }
    }

    public void drawText(String text, int posX, int posY, int color, Font font){
        text = text.toUpperCase();
        int offset = 0;

        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32;

            for (int y = 0; y < font.getFontImage().getHeight(); y++) {
                for (int x = 0; x < font.getWidth()[unicode]; x++) {
                    if (font.getFontImage().getPixels()[(x + font.getOffset()[unicode]) + y * font.getFontImage().getWidth()] == 0xffffffff){
                        setPixel(x + posX + offset, y + posY, color);
                    }
                }
            }
            offset += font.getWidth()[unicode];
        }
    }

    public void drawRect(int posX, int posY, int width, int height, int color){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || y == 0 || y == height - 1 || x == width - 1){
                    setPixel(x + posX, y + posY, color);
                }
            }
        }
    }

    public void drawFillRect(int posX, int posY, int width, int height, int color){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setPixel(x + posX, y + posY, color);
            }
        }
    }

    public void drawImage(Image image, int posX, int posY){
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth() ; x++) {
                setPixel(x + posX, y + posY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

    public void setPixel(int x, int y, int value){
        int alpha = ((value >> 24) & 0xff);
        if ((x < 0 || y >= pixelWidth || y < 0 || y >= pixelHeight) || alpha == 0){
            return;
        }
        if (zBuffer[x + y * pixelWidth] > zDepth){
            return;
        }
        if (alpha == 255){
            pixels[x + y * pixelWidth] = value;
        }
        else {

            int pixelColor = pixels[x + y * pixelWidth];
            int newRed = ((pixelColor >> 16) & 0xff) - (int) ((((pixelColor >> 16) & 0xff) - ((value >> 16) & 0xff)) * (alpha / 255f));
            int newGreen = ((pixelColor >> 8) & 0xff) - (int) ((((pixelColor >> 8) & 0xff) - ((value >> 16) & 0xff)) * (alpha / 255f));
            int newBlue = (pixelColor & 0xff) - (int) (((pixelColor & 0xff) - (value & 0xff)) * (alpha / 255f));

            pixels[x + y * pixelWidth] = (255 << 24 | newRed << 16 | newGreen << 8 | newBlue);
        }
    }

    public int getzDepth() {
        return zDepth;
    }

    public void setzDepth(int zDepth) {
        this.zDepth = zDepth;
    }
}
