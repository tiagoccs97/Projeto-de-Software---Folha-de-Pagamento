package fpg;

import java.util.Hashtable;
import java.util.ArrayList;

public class Hourly extends Employee{
	private int arrival_time;
	private int exit_time;
	private double daily_payment;
	private int check_setofday;
	private ArrayList<Hourly> Payment_Daily = new ArrayList<Hourly>();
	
	public Hourly(int arrival_time,int exit_time,double daily_payment,int check_setofday, String name, String adress, Character type, int payment_method, String agenda,int id, double salary, int first_day,int first_month, int first_year, int first_week_day,boolean syndicate,double syndicate_tax) {
		super(name, adress, type, payment_method, agenda, id, salary, first_day, first_month, first_year, first_week_day, syndicate, syndicate_tax);
		this.arrival_time = arrival_time;
		this.exit_time = exit_time;
		this.daily_payment = daily_payment;
		this.check_setofday = check_setofday;
	}
	
	//Gets
	
	public int get_arrival_time() {
		return arrival_time;
	}
	public int get_exittime() {
		return exit_time;
	}
	public double get_daily_payment() {
		return daily_payment;
	}
	public ArrayList<Hourly> get_PaymentDaily() {
		return Payment_Daily;
	}
	public int get_check_setofday() {
		return check_setofday;
	}
	
	//Sets

	public void set_arrival_time(int arrival_time) {
		this.arrival_time = arrival_time;
	}
	public void set_exittime(int exit_time) {
		this.exit_time = exit_time;
	}
	public void set_daily_payment(double daily_payment) {
		this.daily_payment = daily_payment;
	}
	public void set_PaymentDaily(ArrayList<Hourly> payment_daily) {
		this.Payment_Daily = payment_daily;
	}
	public void set_check_setofday(int check_setofday) {
		this.check_setofday = check_setofday;
	}
	





	
	public static void calculate_hourly_payment(Hashtable<Integer,Employee> employee,int employeeindex) {
		int verifypayment = 0;
		if(employee.get(employeeindex).get_type() == 'H') {
			if(employee.get(employeeindex) instanceof Employee) {
				boolean acceptindex = true;
				Hourly currentemployee = (Hourly) employee.get(employeeindex);
				for(int i = 0;i < currentemployee.get_PaymentDaily().size();i++) {
					//Try
					try {
						currentemployee.get_PaymentDaily().get(i).get_arrival_time();
						currentemployee.get_PaymentDaily().get(i).get_exittime();
					}
					catch(IndexOutOfBoundsException e){
						acceptindex = false;
					}
					if(acceptindex) {
						if((currentemployee.get_PaymentDaily().get(i).get_arrival_time() != -1) && (currentemployee.get_PaymentDaily().get(i).get_exittime() != -1)) {
							verifypayment++;
							int begin = currentemployee.get_PaymentDaily().get(i).get_arrival_time();
							int end = currentemployee.get_PaymentDaily().get(i).get_exittime();
							int totaltime = end-begin;
							if(totaltime <= 0) currentemployee.get_PaymentDaily().get(i).set_daily_payment(0);
							else if(totaltime <= 8) currentemployee.get_PaymentDaily().get(i).set_daily_payment(totaltime * (currentemployee.get_salary()));
							else {
								double currentpayment = 8 * (currentemployee.get_salary());
								int extrahours = totaltime - 8;
								double finalpayment = (extrahours*((currentemployee.get_salary())*1.5)) + currentpayment;
								currentemployee.get_PaymentDaily().get(i).set_daily_payment(finalpayment);
							}
						}
						else currentemployee.get_PaymentDaily().get(i).set_daily_payment(0);
						acceptindex = true;
					}
				}
				acceptindex = true;
				double finalpaymentsum = 0;
				if(verifypayment == 0) currentemployee.set_payment(0.0);
				else {
					for(int i = 0; i < currentemployee.get_PaymentDaily().size();i++) {
						//Try
						try {
							currentemployee.get_PaymentDaily().get(i).get_daily_payment();
						}
						catch(IndexOutOfBoundsException e){
							acceptindex = false;
						}
						if(acceptindex){
							finalpaymentsum += currentemployee.get_PaymentDaily().get(i).get_daily_payment();
							acceptindex = true;
						}
					}
					currentemployee.set_payment(finalpaymentsum);
				}
			}
			else System.out.println("Nao foi possivel associar empregado.");
		}
		else System.out.println("Nao e horista.");
	}

}
