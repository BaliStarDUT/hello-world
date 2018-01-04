public class ShapeFactory extends AbstractFactory{
  @Override
  public Color getColor(String color){
    return null;
  }
  @Override
  public Shape getShape(String shape){
    if(shape.equals("Circle")){
      return new Circle();
    }else if(shape.equals("Rectangle")){
      return new Rectangle();
    }else if(shape.equals("Square")){
      return new Square();
    }
    return null;
  }
}
