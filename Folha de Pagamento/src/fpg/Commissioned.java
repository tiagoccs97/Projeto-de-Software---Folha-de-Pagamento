package fpg;

public class Commissioned extends Employee{
		private double commission_week;
		private double commission_percentage;
		
		public Commissioned(String name, String adress, char type, int payment_method, boolean syndicate, double commission_percentage) {
			super(name, adress, type, payment_method, syndicate);
			this.commission_percentage = commission_percentage;			
		}
		
		//Gets
		public double get_commission_week(){
			return commission_week;
		}
		public double get_commission_percentage(){
			return commission_percentage;
		}
		
		//Sets
		public void set_commission_week(double commission_week) {
			this.commission_week = commission_week;
		}
		public void set_commission_percentage(double commission_percentage) {
			this.commission_percentage = commission_percentage;
		}
}
