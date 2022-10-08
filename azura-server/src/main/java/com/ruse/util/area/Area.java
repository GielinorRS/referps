package com.ruse.util.area;

import lombok.Data;

/**
 * @author Walied K. Yassen
 */
@Data
public final class Area {

    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;

    public boolean contains(int x, int y) {
        return x >= startX && x <= endX && y >= startY && y <= endY;
    }
}
