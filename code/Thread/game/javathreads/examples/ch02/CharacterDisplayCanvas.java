package javathreads.examples.ch02;

import javax.swing.*;
import java.awt.*;

public class  CharacterDisplayCanvas extends JComponent implements CharacterListener{
    protected FontMetrics fm;
    protected char[] temChar = new char[1];
    protected int fontHeight ;
    public  CharacterDisplayCanvas(){
        setFont(new Font("Monospaced",Font.BOLD,18));
        fm = Toolkit.getDefaultToolkit().getFontMetrics(getFont());
        fontHeight = fm.getHeight();
    }
    public CharacterDisplayCanvas(CharacterSource cs){
        this();
        setCharacterSource(cs);
    }
    public void setCharacterSource(CharacterSource cs){
        cs.addCharacterListener(this);
    }
    public Dimension preferredSize(){
        return new Dimension(fm.getMaxAscent()+10,fm.getMaxAdvance()+10);
    }
    @Override
    public synchronized void newCharacter(CharacterEvent event){
        temChar[0] = (char)event.character;
        repaint();
    }
    @Override
    protected synchronized void paintComponent(Graphics gc){
        Dimension d = getSize();
        gc.clearRect(0,0,d.width,d.height);
        if(temChar[0] == 0){
            return;
        }
        int charWidth = fm.charWidth((int)temChar[0]);
        gc.drawChars(temChar,0,1,(d.width-charWidth)/2,fontHeight);
    }
}
