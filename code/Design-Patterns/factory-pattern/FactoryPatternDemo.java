public class FactoryPatternDemo{
  public static void main(String[] args) {
    ShapeFactory factory = new ShapeFactory();
    Shape shape1 = factory.getInstance("Circle");
    shape1.draw();
    Shape shape2 = factory.getInstance("Rectangle");
    shape2.draw();
    Shape shape3 = factory.getInstance("Square");
    shape3.draw();

  }
}
