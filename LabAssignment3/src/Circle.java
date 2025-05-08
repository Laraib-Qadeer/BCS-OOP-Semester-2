public class Circle extends Shape2d{
    private double radius;
    public Circle(String name,double radius){
        super(name);
        this.radius=radius;
    }
    public void setRadius(){
        this.radius=radius;
    }
    public double getRadius(){
        return radius;
    }
    @Override
    public double area(){
        return  Math.PI * radius * radius;
    }
    @Override
    public boolean isFilled(){
        return false;
    }

    @Override
    public String toString(){
        return super.toString()+" Radius: "+radius+" Area: "+area();
    }

    @Override
    public void draw(){
        System.out.println("Circle drawn.");
    }
}
