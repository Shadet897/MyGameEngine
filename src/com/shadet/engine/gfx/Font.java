package com.shadet.engine.gfx;

public class Font {

    public static final Font STANDART = new Font("/standartFont.png");
    private Image fontImage;
    private int[] offset;
    private int[] width;

    public Font(String path){
        fontImage = new Image(path);
        offset = new int[59];
        width = new int[59];

        int unicode = 0;

        for (int i = 0; i < fontImage.getWidth(); i++) {
            if (fontImage.getPixels()[i] == 0xff0000ff){
                offset[unicode] = i;
            }
            if (fontImage.getPixels()[i] == 0xffffff00){
                width[unicode] = i - offset[unicode];
                unicode++;
            }
        }
    }

    public Image getFontImage() {
        return fontImage;
    }

    public int[] getOffset() {
        return offset;
    }

    public int[] getWidth() {
        return width;
    }
}
