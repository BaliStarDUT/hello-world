package javathreads.examples.ch02;
import java.util.*;

public class CharacterEventHandler{
  private Vector listeners = new Vector();
  public void addCharacterListener(CharacterListener c1){
    listeners.add(c1);
  }
  public void removeCharacterListener(CharacterListener c2){
    listeners.remove(c2);
  }
  public void fireNewCharacter(CharacterSource source ,int c){
    CharacterEvent ce = new CharacterEvent(source,c);
    CharacterListener[] c1 = (CharacterListener[]) listeners.toArray(new CharacterListener[0]);
    for(int i=0;i<c1.length;i++){
      c1[i].newCharacter(ce);
    }
  }
}
