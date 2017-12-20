package javathreads.examples.ch02;

public interface CharacterEventHandler{
  private Vector listeners = new Vector();

  public void addCharacterListener(CharacterListener cl){
    listeners.add(cl);
  }

  public void removeCharacterListener(CharacterListener cl){
    listeners.remove(cl);
  }

  public void fireNewCharacterListener(CharacterSource cs,int c){
    CharacterEvent ce = new CharacterEvent(cs,c);
    CharacterListener[] cl = (CharacterListener) listeners.toArray(new CharacterListener[0]);
    for(int i = 0;i<cl.length;i++){
      cl[i].newCharacter(ce);
    }
  }

  public CharacterSource source;
  public int character;
  public CharacterEvent(CharacterSource cs,int c){
    source = cs;
    character = c;
  }
  public void newCharacter(CharacterEvent e);
  public void addCharacterListener(CharacterListener cl);
  public void removeCharacterListener(CharacterListener cl);
  public void nextCharacter();
}
