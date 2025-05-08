public class Sphere extends Shape3d{
    private double radius;
    public Sphere(String name,double radius){
        super(name);
        this.radius=radius;
    }
    @Override
    public double area(){
        return 4*Math.PI*radius*radius;
    }
    @Override
    public double Volume(){
        return (4/3)*Math.PI*radius*radius*radius;
    }
    @Override
    public String toString(){
        return super.toString()+" Radius: "+radius+" Area: "+area()+" Volume: "+Volume();
    }

    @Override
    public void draw(){
        System.out.println("Rectangle drawn.");
    }
}

