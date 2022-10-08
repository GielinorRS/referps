package org.necrotic.client;

import java.awt.*;

public class JagexColor {

    public static int RGB_to_RS2HSB(int red, int green, int blue) {
        float[] HSB = Color.RGBtoHSB(red, green, blue, null);
        float hue = (HSB[0]);
        float saturation = (HSB[1]);
        float brightness = (HSB[2]);
        int encode_hue = (int) (hue * 63);			//to 6-bits
        int encode_saturation = (int) (saturation * 7);		//to 3-bits
        int encode_brightness = (int) (brightness * 127); 	//to 7-bits
        return (encode_hue << 10) + (encode_saturation << 7) + (encode_brightness);
    }
    public static javafx.scene.paint.Color RS2HSB_to_RGB(int RS2HSB) {
        int decode_hue = (RS2HSB >> 10) & 0x3f;
        int decode_saturation = (RS2HSB >> 7) & 0x07;
        int decode_brightness = (RS2HSB & 0x7f);
        Color awtColor = new Color(Color.HSBtoRGB((float)decode_hue/63, (float)decode_saturation/7, (float)decode_brightness/127));
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0 ;
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b, opacity);
        return fxColor;
    }
    public static Color RS2HSB_to_RGB1(int RS2HSB) {
        int decode_hue = (RS2HSB >> 10) & 0x3f;
        int decode_saturation = (RS2HSB >> 7) & 0x07;
        int decode_brightness = (RS2HSB & 0x7f);
        Color awtColor = new Color(Color.HSBtoRGB((float)decode_hue/63, (float)decode_saturation/7, (float)decode_brightness/127));
        int r = awtColor.getRed();
        int g = awtColor.getGreen();
        int b = awtColor.getBlue();
        int a = awtColor.getAlpha();
        double opacity = a / 255.0 ;
        Color fxColor = new Color(r, g, b, a);
        return fxColor;
    }

    public static final int[] hsl2rgb;

    public static final int[] map;

    static {
        int[] out = hsl2rgb = map = new int[65536];
        double d = 0.7D;
        int i = 0;
        for (int i1 = 0; i1 != 512; ++i1) {
            float f = ((i1 >> 3) / 64.0F + 0.0078125F) * 360.0F;
            float f1 = 0.0625F + (0x7 & i1) / 8.0F;
            for (int i2 = 0; i2 != 128; ++i2) {
                float f2 = i2 / 128.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = f / 60.0F;
                int i3 = (int) f6;
                int i4 = i3 % 6;
                float f7 = f6 - i3;
                float f8 = f2 * (-f1 + 1.0F);
                float f9 = f2 * (-(f7 * f1) + 1.0F);
                float f10 = (1.0F - f1 * (-f7 + 1.0F)) * f2;
                if (i4 == 0) {
                    f3 = f2;
                    f5 = f8;
                    f4 = f10;
                } else if (i4 == 1) {
                    f5 = f8;
                    f3 = f9;
                    f4 = f2;
                } else if (i4 == 2) {
                    f3 = f8;
                    f4 = f2;
                    f5 = f10;
                } else if (i4 == 3) {
                    f4 = f9;
                    f3 = f8;
                    f5 = f2;
                } else if (i4 == 4) {
                    f5 = f2;
                    f3 = f10;
                    f4 = f8;
                } else {
                    f4 = f8;
                    f5 = f9;
                    f3 = f2;
                }
                out[i++] = ((int) ((float) Math.pow(f3, d) * 256.0F) << 16)
                        | ((int) ((float) Math.pow(f4, d) * 256.0F) << 8) | (int) ((float) Math.pow(f5, d) * 256.0F);
            }
        }

        for (int i1 = 0; i1 < map.length; i1++) {

            int index = i1 & 0xffff;

            if (index < hsl2rgb.length) {
                map[index] = i1;
            }

        }

    }

    public static void main(String[] args) throws Exception {

        int rgb = hsl2rgb[926];

        System.out.println(String.format("%02x%02x%02x", rgb & 0xFF, (rgb >> 8) & 0xFF, (rgb >> 16) & 0xFF));

    }

    public static int toHSB(int red, int green, int blue) {

        float[] HSB = Color.RGBtoHSB(red, green, blue, null);

        int hue = (int) ((HSB[0]) * 63);
        int saturation = (int) ((HSB[1]) * 7);
        int brightness = (int) ((HSB[2]) * 127);

        return (hue << 10) + (saturation << 7) + (brightness);

    }

    public static int toRGB(int hsb) {

        int hue = (hsb >> 10) & 0x3F;
        int saturation = (hsb >> 7) & 0x07;
        int brightness = (hsb & 0x7F);

        return Color.HSBtoRGB((float) hue / 63, (float) saturation / 7, (float) brightness / 127);

    }

    public static int fromHEX(String hex) {

        int[] rgb = hex2Rgb(hex);

        return fromRGB(rgb[0], rgb[1], rgb[2]);

    }

    public static int fromRGB(Color color) {
        return fromRGB(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static int fromRGB(int red, int green, int blue) {
        return fromRGB((red << 16) + (green << 8) + blue);
    }

    public static int fromRGB(int rgb) {

        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        rgb = ((blue << 16) + (green << 8) + red) & 0xFFFF;

        for (int i = 0; i < hsl2rgb.length; i++) {
            if (hsl2rgb[i] == rgb) {
                return map[i] & 0xFFFF;
            }
        }

        return -1;

    }

    public static int fromHSL(int color) {
        int rgb = hsl2rgb[color & 0xFFFF];
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        return (blue << 16) + (green << 8) + red;
    }

    public static int[] hex2Rgb(String color) {

        int hex = Integer.parseInt(color, 16);
        int red = (hex & 0xFF0000) >> 16;
        int green = (hex & 0xFF00) >> 8;
        int blue = (hex & 0xFF);

        return new int[]{red, green, blue};

    }

}