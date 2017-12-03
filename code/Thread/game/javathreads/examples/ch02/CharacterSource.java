package javathreads.examples.ch02;

public interface CharacterSource{
  public void addCharacterListener(CharacterListener c1);
  public void removeCharacterListener(CharacterListener c1);
  public void nextCharacter();
}
