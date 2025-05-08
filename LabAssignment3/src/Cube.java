public class Cube extends Shape3d {
    private double side;
    public Cube(String name,double side){
        super(name);
        this.side=side;
    }
    @Override
    public double area(){
        return 6*side*side;
    }
    @Override
    public double Volume(){
        return side*side*side;
    }

    @Override
    public String toString(){
        return super.toString()+" Side:" +side+ " Area: "+area()+" Volume: "+Volume();
    }
    @Override
    public void draw(){
        System.out.println("Rectangle drawn.");
    }
}
