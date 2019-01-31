
package com.main;

import java.awt.Point;
import java.io.File;

public class Res {
    
    public static final SpriteSheet PATTERN_SHEET = new SpriteSheet(new File("res/patterns.png"), new Point(16, 16));
    
    public static final Sprite PATTERNS = new Sprite(PATTERN_SHEET, new Point(0, 0), 4);
}
