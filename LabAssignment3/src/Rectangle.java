public class Rectangle extends Shape2d{
    private double length;
    private double width;

    public Rectangle(String name,double length, double width){
        super(name);
        this.length=length;
        this.width=width;
    }
    public void setWidth(double width){
         this.width=width;
    }
    public double getWidth(){
        return width;
    }
    public void setLength(double length){
        this.length=length;
    }
    public double getLength(){
        return length;
    }
    @Override
    public double area(){
        return length*width;
    }
    @Override
    public boolean isFilled(){
        return false;
    }
    @Override
    public String toString(){
        return super.toString()+" Length: "+length+" Width: "+width+" Area: "+area();
    }
    @Override
    public void draw(){
        System.out.println("Rectangle drawn.");
    }
}
