package fpg;

import java.util.Hashtable;

public class Commissioned extends Employee{
		private double commission_percentage;
		private double sells;
		
		public Commissioned(double commission_percentage, double sells, String name, String adress, char type, int payment_method, String agenda,int id, double salary, int first_day,int first_month, int first_year, int first_week_day,boolean syndicate,double syndicate_tax, double commission) {
			super(name, adress, type, payment_method, agenda, id, salary, first_day, first_month, first_year, first_week_day, syndicate, syndicate_tax);
			this.commission_percentage = commission;		
			this.sells = 0;
		}
		public Commissioned() {
			
		}
		//Gets
		public double get_commission_percentage(){
			return commission_percentage;
		}
		public double get_sells(){
				return sells;
		}
		
		//Sets
		public void set_commission_percentage(double commission_percentage) {
			this.commission_percentage = commission_percentage;
		}
		public void set_sells(double sells) {
			this.sells += sells;
		}
		
		public static void calculate_commissioned_payment(Hashtable<Integer,Employee> employee,int i) {
			if(employee.get(i).get_type() == 'C') {
				if(employee.get(i) instanceof Employee) {
					Commissioned currentemployee = (Commissioned) employee.get(i);
					double total = currentemployee.get_sells();
					if(total > 0) {
						total = total * (currentemployee.commission_percentage);
					}
					double finalsalary = total + (currentemployee.get_salary());
					currentemployee.set_payment(finalsalary);
				}
				else System.out.println("Funcionario nao associado, operacao nao realizada!");
			}
			else System.out.println("Operacao nao realizada, selecione um funcionario comissionado!!!");
		}
}
