package javathreads.examples.ch02;

public interface CharacterListener{
  public void newCharacter(CharacterEvent e);
  public void addCharacterListener(CharacterListener cl);
  public void removeCharacterListener(CharacterListener cl);
  public void nextCharacter();
}
