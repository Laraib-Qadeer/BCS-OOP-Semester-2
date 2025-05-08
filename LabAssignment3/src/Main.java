//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String args[]){

        Circle circle=new Circle("C1",5.0);
        System.out.println("Name: "+circle.getName());
        System.out.println("Radius: "+circle.getRadius());
        System.out.println("Area:"+circle.area());

        Rectangle rectangle=new Rectangle("R1",5.0,7.0);
        System.out.println("Name: "+rectangle.getName());
        System.out.println("Area: "+rectangle.area());

        Cube cube=new Cube("Cube1",7.0);
        System.out.println("Name: "+cube.getName());
        System.out.println("Area: "+cube.area());
        System.out.println("Volume: "+cube.Volume());

        Sphere sphere=new Sphere("S1",8.0);
        System.out.println("Name: "+sphere.getName());
        System.out.println("Area: "+sphere.area());
        System.out.println("Volume: "+sphere.Volume());
        Shape shapes[]=new Shape[20];
        for(int i=0;i<=4;i++){
            shapes[i]=new Circle("circle",6.0);
        }
        for(int i=5;i<=9;i++){
            shapes[i]=new Rectangle("rec",9.0,6.0);
        }
        for(int i=10;i<=14;i++){
            shapes[i]=new Cube("cube",8.0);
        }
        for(int i=15;i<=19;i++){
            shapes[i]=new Sphere("sphere",3.0);
        }


        ShapeUtils.displayshapes(shapes);
        ShapeUtils.increaseRectangleLength(shapes);
    }
}