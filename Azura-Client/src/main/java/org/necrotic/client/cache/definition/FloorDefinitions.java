package org.necrotic.client.cache.definition;


import org.necrotic.client.cache.Archive;
import org.necrotic.client.io.ByteBuffer;

public class FloorDefinitions {
    public static FloorDefinitions[] underlays;
    public static FloorDefinitions[] overlays;

    //  public int rgb;
    public int textureId;
    public boolean shadowing;
    //  public int hue;
    //   public int saturation;
    //   public int luminance;
//    public int blendHue;
    public int chroma;
    public int color;

    private FloorDefinitions() {
        textureId = -1;
        shadowing = true;
    }

    public static void unpackUnderlays(Archive streamLoader) {
       ByteBuffer stream = new ByteBuffer(streamLoader.get("flo.dat"));
        int cacheSize = stream.getUnsignedShort();
        if (underlays == null) {
            underlays = new FloorDefinitions[cacheSize];
        }
        for (int j = 0; j < cacheSize; j++) {
            if (underlays[j] == null) {
                underlays[j] = new FloorDefinitions();
            }
            underlays[j].decode(stream);
        }
    }

    public static void unpackOverlays(final Archive stream) {

        final java.nio.ByteBuffer oldBuffer = java.nio.ByteBuffer.wrap(stream.get("flo2.dat"));
        final int oldOverlayAmount = oldBuffer.getShort();

        overlays = new FloorDefinitions[oldOverlayAmount];
        for (int i = 0; i < oldOverlayAmount; i++) {
            if (overlays[i] == null) {
                overlays[i] = new FloorDefinitions();
            }
            overlays[i].readOldValues(oldBuffer);
            overlays[i].generateHsl();
        }
    }

    private void readOldValues(final java.nio.ByteBuffer oldBuffer) {
        for (;;) {
            int opcode = oldBuffer.get();
            if (opcode == 0)
                break;
            else if (opcode == 1) {
                rgb = ((oldBuffer.get() & 0xff) << 16) + ((oldBuffer.get() & 0xff) << 8) + (oldBuffer.get() & 0xff);
            } else if (opcode == 2) {
                texture = oldBuffer.get() & 0xff;
            } else if (opcode == 3) {
                texture = oldBuffer.getShort() & 0xffff;
                if (texture == 65535) {
                    texture = -1;
                }
            } else if (opcode == 4) {

            } else if (opcode == 5) {
                boolean boolean_5 = false;
            } else if (opcode == 6) {

            } else if (opcode == 7) {
                int int_7 = ((oldBuffer.get() & 0xff) << 16) + ((oldBuffer.get() & 0xff) << 8) + (oldBuffer.get() & 0xff);
            } else if (opcode == 8) {

            } else if (opcode == 9) {
                int int_9 = oldBuffer.getShort() & 0xffff;
            } else if (opcode == 10) {
                boolean boolean_10 = false;
            } else if (opcode == 11) {
                int int_11 = oldBuffer.get() & 0xff;
            } else if (opcode == 12) {
                boolean boolean_12 = true;
            } else if (opcode == 13) {
                int int_13 = ((oldBuffer.get() & 0xff) << 16) + ((oldBuffer.get() & 0xff) << 8) + (oldBuffer.get() & 0xff);
            } else if (opcode == 14) {
                int int_14 = oldBuffer.get() & 0xff;
            } else if (opcode == 15) {
                int int_15 = oldBuffer.getShort() & 0xffff;
                if (int_15 == 65535) {
                    int_15 = -1;
                }
            } else if (opcode == 16) {
                int int_16 = oldBuffer.get() & 0xff;
            } else {
                System.err.println("[FloorOverlay] Missing AttributeId: " + opcode);
            }
        }
    }

    private void decode(ByteBuffer stream) {
        do {
            int opcode = stream.getUnsignedByte();
            if (opcode == 0)
                return;
            else if (opcode == 1) {
                rgb = stream.getTribyte();
                blend(rgb);
            } else if (opcode == 2)
                textureId = stream.getUnsignedByte();
            else if (opcode == 3) {
            } else if (opcode == 5)
                shadowing = false;
            else if (opcode == 6)
                stream.getString();
            else if (opcode == 7) {
                int hue = this.hue;
                int saturation = this.saturation;
                int luminance = this.luminance;
                int i1 = blendHue;
                int minimapRGB = stream.getTribyte();

                blend(minimapRGB);
                this.hue = hue;
                this.saturation = saturation;
                this.luminance = luminance;
                blendHue = i1;
                chroma = i1;
            } else {
                System.out.println("Error unrecognised floor underlay config code: " + opcode);
            }
        } while (true);
    }


    private void blend(int color) {
        double r = (color >> 16 & 0xff) / 256D;
        double g = (color >> 8 & 0xff) / 256D;
        double b = (color & 0xff) / 256D;
        double darkness = r;
        if (g < darkness)
            darkness = g;
        if (b < darkness)
            darkness = b;
        double brightness = r;
        if (g > brightness)
            brightness = g;
        if (b > brightness)
            brightness = b;
        double hue = 0.0D;
        double saturation = 0.0D;
        double luminance = (darkness + brightness) / 2D;
        if (darkness != brightness) {
            if (luminance < 0.5D)
                saturation = (brightness - darkness) / (brightness + darkness);
            if (luminance >= 0.5D)
                saturation = (brightness - darkness) / (2D - brightness - darkness);
            if (r == brightness)
                hue = (g - b) / (brightness - darkness);
            else if (g == brightness)
                hue = 2D + (b - r) / (brightness - darkness);
            else if (b == brightness)
                hue = 4D + (r - g) / (brightness - darkness);
        }
        hue /= 6D;
        this.hue = (int) (hue * 256D);
        this.saturation = (int) (saturation * 256D);
        this.luminance = (int) (luminance * 256D);
        if (this.saturation < 0)
            this.saturation = 0;
        else if (this.saturation > 255)
            this.saturation = 255;
        if (this.luminance < 0)
            this.luminance = 0;
        else if (this.luminance > 255)
            this.luminance = 255;
        if (luminance > 0.5D)
            chroma = (int) ((1.0D - luminance) * saturation * 512D);
        else
            chroma = (int) (luminance * saturation * 512D);
        if (chroma < 1)
            chroma = 1;
        blendHue = (int) (hue * chroma);

        this.color = encode(this.hue, this.saturation, this.luminance);
    }

    public static int encode(int hue, int saturation, int lightness) {
        if (lightness > 179) {
            saturation >>= 1;
        } else if (lightness > 192) {
            saturation >>= 2;
        } else if (lightness > 217) {
            saturation >>= 3;
        } else if (lightness > 243) {
            saturation >>= 4;
        }

        return ((hue >> 2 & 0x3f) << 10) + (saturation >> 5 << 7) + (lightness >> 1);
    }

    private void generateHsl() {
        if (anotherRgb != -1) {
            rgbToHsl(anotherRgb);
            anotherHue = hue;
            anotherSaturation = saturation;
            anotherLuminance = luminance;
        }
        rgbToHsl(rgb);
    }

    private void rgbToHsl(final int rgb) {
        final double r = (rgb >> 16 & 0xff) / 256.0;
        final double g = (rgb >> 8 & 0xff) / 256.0;
        final double b = (rgb & 0xff) / 256.0;
        double min = r;
        if (g < min)
            min = g;
        if (b < min)
            min = b;
        double max = r;
        if (g > max)
            max = g;
        if (b > max)
            max = b;
        double h = 0.0;
        double s = 0.0;
        final double l = (min + max) / 2.0;
        if (min != max) {
            if (l < 0.5)
                s = (max - min) / (max + min);
            if (l >= 0.5)
                s = (max - min) / (2.0 - max - min);
            if (r == max)
                h = (g - b) / (max - min);
            else if (g == max)
                h = 2.0 + (b - r) / (max - min);
            else if (b == max)
                h = 4.0 + (r - g) / (max - min);
        }
        h /= 6.0;
        hue = (int) (h * 256.0);
        saturation = (int) (s * 256.0);
        luminance = (int) (l * 256.0);
        if (saturation < 0)
            saturation = 0;
        else if (saturation > 255)
            saturation = 255;
        if (luminance < 0)
            luminance = 0;
        else if (luminance > 255)
            luminance = 255;
        if (l > 0.5)
            blendHueMultiplier = (int) ((1.0 - l) * s * 512.0);
        else
            blendHueMultiplier = (int) (l * s * 512.0);
        if (blendHueMultiplier < 1)
            blendHueMultiplier = 1;
        blendHue = (int) (h * blendHueMultiplier);
        hsl16 = hsl24to16(hue, saturation, luminance);
    }

    public static int hsl24to16(final int h, int s, final int l) {
        if (l > 179)
            s /= 2;
        if (l > 192)
            s /= 2;
        if (l > 217)
            s /= 2;
        if (l > 243)
            s /= 2;
        return (h / 4 << 10) + (s / 32 << 7) + l / 2;
    }

    // public static FloorOverlay[] overlays;
    public static int biggestTextureId;
    public int texture = -1;
    public int rgb;
    public boolean occlude;
    public int anotherRgb;

    /**
     * Hsl
     */
    public int hue;
    public int saturation;
    public int luminance;
    public int blendHue;
    public int blendHueMultiplier;
    public int hsl16;

    public int anotherHue;
    public int anotherSaturation;
    public int anotherLuminance;

}