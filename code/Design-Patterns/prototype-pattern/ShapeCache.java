import java.util.Hashtable;

public class ShapeCache{
  private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();

  public static Shape getShape(String shapeId) {
      Shape cachedShape = shapeMap.get(shapeId);
      Shape shape2 = (Shape) cachedShape.clone();
      shape2.setType("clone");
      return shape2;
   }
   public static void loadCache() {
      Circle circle = new Circle();
      circle.setId("1");
      shapeMap.put(circle.getId(),circle);

      Square square = new Square();
      square.setId("2");
      shapeMap.put(square.getId(),square);

      Rectangle rectangle = new Rectangle();
      rectangle.setId("3");
      shapeMap.put(rectangle.getId(),rectangle);
   }
}
