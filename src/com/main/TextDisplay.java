
package com.main;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class TextDisplay {
    
    public static final Font font = new Font("Monospace", Font.PLAIN, 80);
    
    private String text;
    private int lastChange;
    
    public TextDisplay(){
    }
    
    public void update(){
        lastChange--;
        if(lastChange <= 0){ text = null; }
    }
    
    public void setText(String text){
        this.text = text;
        lastChange = 120 + 60*3;
    }
    
    public void render(Graphics2D g){
        if(text != null){
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            float delta = lastChange/120f;
            if(delta>1){ delta = 1; }
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, delta);
            Composite save = g.getComposite();
            g.setComposite(ac);
            g.drawString(text, (Main.WIDTH - fm.stringWidth(text))/2, 90);
            g.setComposite(save);
        }
    }
}
