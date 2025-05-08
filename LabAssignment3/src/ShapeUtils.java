
public class ShapeUtils {
    public static void displayshapes(Shape[] shapes) {
        for (Shape shape : shapes) {
            if (shape != null) {
                System.out.println(shape.toString());
                System.out.println("Area: " + shape.area());
                System.out.println();
            } else {
                System.out.println("Shape is null.");
            }
        }
    }
        public static void increaseRectangleLength(Shape[]shapes) {
            for (Shape shape : shapes) {
                if (shape instanceof Rectangle) {
                    Rectangle r = (Rectangle) shape;
                    r.setLength(r.getLength() + 5.0);
                    System.out.println("New Length: " + r.getLength());

                }
            }

        }

}

