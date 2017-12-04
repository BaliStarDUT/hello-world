package javathreads.examples.ch02.example1;

import javathreads.examples.ch02.CharacterEvent;
import javathreads.examples.ch02.CharacterListener;
import javathreads.examples.ch02.CharacterSource;

import javax.swing.*;


public class ScoreLable extends JLabel implements CharacterListener {
    private volatile int score = 0;
    private int char2type = -1;
    private CharacterSource generator = null,typist = null;
    public ScoreLable(CharacterSource generator,CharacterSource typist){
        this.generator = generator;
        this.typist = typist;
        if(generator!=null){
            generator.addCharacterListener(this);
        }
        if(typist!=null){
            typist.addCharacterListener(this);
        }
    }
    public ScoreLable(){
        this(null,null);
    }
    @Override
    public void newCharacter(CharacterEvent e) {

    }
}
