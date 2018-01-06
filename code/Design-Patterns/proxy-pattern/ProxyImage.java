public class ProxyImage implements Image{
  private RealImage realImage;
  private String fileName;
  public ProxyImage(String name){
    this.fileName = name;
  }
  @Override
  public void display(){
    if(realImage == null){
      realImage = new RealImage(fileName);
    }
    realImage.display();
  }
}
