package javathreads.examples.ch02;

public interface CharacterEvent{
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
