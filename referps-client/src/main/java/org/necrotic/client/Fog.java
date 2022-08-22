package org.necrotic.client;

import org.necrotic.client.graphics.DrawingArea;

/**
 * Created by Greg on 24/03/2018.
 */
public class Fog {
    private static int begin = 2550;
    private static int end = 3400;
    private static int length = end - begin;

    public static void set(int begin, int end) {
        Fog.begin = begin;
        Fog.end = end;
        length = end - begin;
    }

    public static void draw(int rgb) {
        for (int index = 0; index < DrawingArea.raster.length; index++) {
        	DrawingArea.raster[index] = blend(DrawingArea.raster[index], rgb, (DrawingArea.depthBuffer[index] - begin) / length);
        }
    }

    private static int blend(int rgb1, int rgb2, float factor) {
        if (factor >= 1f) {
            return rgb2;
        }
        if (factor <= 0f) {
            return rgb1;
        }

        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >> 8) & 0xff;
        int b1 = (rgb1) & 0xff;

        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >> 8) & 0xff;
        int b2 = (rgb2) & 0xff;

        int r3 = r2 - r1;
        int g3 = g2 - g1;
        int b3 = b2 - b1;

        int r = (int) (r1 + (r3 * factor));
        int g = (int) (g1 + (g3 * factor));
        int b = (int) (b1 + (b3 * factor));

        return (r << 16) + (g << 8) + b;
    }
}

