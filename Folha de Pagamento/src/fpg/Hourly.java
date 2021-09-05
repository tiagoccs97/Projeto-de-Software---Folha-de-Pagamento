package fpg;

public class Hourly extends Employee{
	private double salary_per_hour;
	
	public Hourly(String name, String adress, char type, int payment_method, boolean syndicate, double salary_per_hour) {
		super(name, adress, type, payment_method, syndicate);
		this.salary_per_hour = salary_per_hour;
	}
	
	public double get_salary_per_hour(){
		return salary_per_hour;
	}
	public void set_salary_per_hour(double salary_per_hour) {
		this.salary_per_hour = salary_per_hour;
	}

}
