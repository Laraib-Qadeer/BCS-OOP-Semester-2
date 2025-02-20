public class Product{
	private static int IdCounter=1;
	private String ProductId;
	private String ProductName;
	private int ProductQuantity;
	private int ProductPrice;
	private String ProductCategory;
	
	public Product(String name, int quantity , int price , String category){
		this.ProductId = "P"+IdCounter;
		IdCounter++;
		this.ProductName = name;
		this.ProductQuantity = quantity;
		this.ProductPrice = price;
		this.ProductCategory = category;
	}
	
	public String getProductId(){
		return ProductId;
	}
	public void setProductId(String Id){
		ProductId=Id;
	}
	
	public String getProductName(){
		return ProductName;
	}
	public void setProductName(String name){
		ProductName=name;
	}

	public int getProductQuantity(){
		return ProductQuantity;
	}
	public void setProductQuantity(int quantity){
		ProductQuantity=quantity;
	}

	public int getProductPrice(){
		return ProductPrice;
	}
	public void setProductPrice(int price){
		ProductPrice=price;
	}

	public String getProductCategory(){
		return ProductCategory;
	}
	public void setProductCategory(String category){
		ProductCategory=category;
	}

	public void DisplayProduct(){
		System.out.println("Product Id= "+ ProductId);
		System.out.println("Product Name= "+ ProductName);
		System.out.println("Product Quantity= "+ ProductQuantity);
		System.out.println("Product Price= $ "+ ProductPrice);
		System.out.println("Product Category= "+ ProductCategory);
	}
	
		

}
