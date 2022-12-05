package application;

public class BillingModel {
	//Simple class that populates the columns in the billing controller
	//Instance 
	private String course;
	private Double    price;
	//Constructor
	public BillingModel(String course, Double price) {
		this.course = course;
		this.price  =  price;
	}
	public String getCourse() {
		return course;
	}
	
	public Double getPrice() {
		return price;
	}
	
}
