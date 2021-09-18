package fpg;

import java.util.Hashtable;

public class PaymentRoll {
	String schedule;
	Character employee_type;
	
	//Gets
	public String get_schedule() {
		return schedule;
	}
	public int get_employee_type() {
		return employee_type;
	}
	//Sets
	public void set_schedule(String schedule) {
		this.schedule = schedule;
	}
	public void set_employee_type(Character employee_type) {
		this.employee_type = employee_type;
	}	
	
	public static void pass_day(Hashtable<Integer,Employee> HT,int day,int month,int year,int week_day,int totalsize) {
		System.out.println("Hoje: " + day + "/" + month + "/" + year + " Dia da Semana: " + week_day);
		int has_employees = 0;
		System.out.println("\nFOLHA DE PAGAMENTO:");
		for(int i = 0;i <= totalsize;i++) {
			if(HT.containsKey(i)) {
				if((HT.get(i).get_payment_day() == day) && (HT.get(i).get_payment_month() == month) && (HT.get(i).get_payment_year() == year) && (HT.get(i).get_payment_week_day() == week_day)) {
					has_employees++;
					System.out.println("Nome: " + HT.get(i).get_name());
					System.out.println("Endereco: " + HT.get(i).get_adress());
					System.out.println("Tipo: " + HT.get(i).get_type());
					System.out.println("Tipo de Pagamento: " + HT.get(i).get_payment_method());
					if(HT.get(i).get_syndicate() == true) {
						if(HT.get(i) instanceof Employee) {
							System.out.println("Faz parte do Sindicato");
							System.out.println("ID do Sindicato: " + HT.get(i).get_id_s());
							Employee.calculate_syndicate(HT, i);
							System.out.println("Taxa de Sindicato: " + HT.get(i).get_syndicate_tax());
							if(HT.get(i).get_service_tax() != 0) {
								System.out.println("Taxa de Servico do Sindicato: " + HT.get(i).get_service_tax());
								HT.get(i).set_service_tax(0);
							}
							else System.out.println("Nao ha Taxas de Servico do Sindicato");
						}
						else System.out.println("Nao foi possivel associar empregado.");
					}
					else System.out.println("Nao faz parte de Sindicato");
					System.out.println("Pagamento: " + HT.get(i).get_payment());
					System.out.println("---------------------------------------");
					HT.get(i).set_first_day(day);
					HT.get(i).set_first_month(month);
					HT.get(i).set_first_year(year);
					HT.get(i).set_first_week_day(week_day);
					PaymentRoll.set_payment_schedule(HT,i);
					if(HT.get(i).get_type() == 'H') {
						if(HT.get(i) instanceof Employee) {
							Hourly aux_employee = (Hourly) HT.get(i);
							aux_employee.get_PaymentDaily().clear();
						}
						else System.out.println("Funcionario nao associado, operacao nao realizada!");
					}
					else if(HT.get(i).get_type() ==  'C') {
						if(HT.get(i) instanceof Employee) {
							Commissioned aux_employee = (Commissioned) HT.get(i);
							double aux_sells = aux_employee.get_sells();
							aux_sells = aux_sells * -1;
							aux_employee.set_sells(aux_sells);
						}
						else System.out.println("Funcionario nao associado, operacao nao realizada!");	
					}
					HT.get(i).set_payment(0);
				}
			}
		}	
		if(has_employees == 0) {
			System.out.println("Nao ha nenhum funcionario a ser pago hoje");
		}
		System.out.println();
	}
	
	public static void set_payment_schedule(Hashtable<Integer,Employee> HT,int aux) {
		if(HT.get(aux).get_type() == 'H') {
			String current_schedule = HT.get(aux).get_agenda();
			int DayIWant = 0;
			if(current_schedule.equals("Segunda Semanal")) DayIWant = 2;
			else if(current_schedule.equals("Terca Semanal")) DayIWant = 3;
			else if(current_schedule.equals("Quarta Semanal")) DayIWant = 4;
			else if(current_schedule.equals("Quinta Semanal"))  DayIWant = 5;
			else if(current_schedule.equals("Sexta Semanal") || current_schedule.equals("Semanalmente")) DayIWant = 6;
				
			int dayp,monthp,yearp,week_dayp;
			dayp = HT.get(aux).get_first_payment_day();
			monthp = HT.get(aux).get_first_payment_month();
			yearp = HT.get(aux).get_first_payment_year();
			week_dayp = HT.get(aux).get_first_week_day(); 
				
			for(int i = 0;i < 7;i++) {
				dayp++;
				week_dayp++;
				if(week_dayp == DayIWant) {
					break;
				}
				if(week_dayp == 8) week_dayp = 1;
				if(dayp == 29 && monthp == 2) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 32) {
					dayp = 1;
					monthp++;
				}
				if(monthp == 13) {
					monthp = 1;
					yearp++;
				}
			}
			HT.get(aux).set_payment_day(dayp);
			HT.get(aux).set_payment_month(monthp);
			HT.get(aux).set_payment_year(yearp);
			HT.get(aux).set_payment_week_day(week_dayp);
		}
		else if(HT.get(aux).get_type() == 'A'){
			if(HT.get(aux).get_agenda() == "Mensalmente" || HT.get(aux).get_agenda() == "Mensal") {
				int dayp,monthp,yearp,week_dayp;
				dayp = HT.get(aux).get_first_payment_day();
				monthp = HT.get(aux).get_first_payment_month();
				yearp = HT.get(aux).get_first_payment_year();
				week_dayp = HT.get(aux).get_first_week_day(); 
				for(int i = 0;i < 31;i++) {
					dayp++;
					week_dayp++;
					if(week_dayp == 8) week_dayp = 1;
					if(dayp == 28 && monthp == 2) break;
					else if(dayp == 29 && monthp == 2) {
						dayp = 1;
						monthp++;
					}
					if(dayp == 30 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) break;
					else if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
						dayp = 1;
						monthp++;
					}
					else if(dayp == 31) break;
					else if(dayp == 32) {
						dayp = 1;
						monthp++;
					}
					if(monthp == 13) {
						monthp =1;
						yearp++;
					}
				}
				if(week_dayp == 7) {
					dayp--;
				}
				else if(week_dayp == 1) {
					dayp -= 2;
				}
				HT.get(aux).set_payment_day(dayp);
				HT.get(aux).set_payment_month(monthp);
				HT.get(aux).set_payment_year(yearp);
				HT.get(aux).set_payment_week_day(week_dayp);
			}
			else {
				String current_schedule = HT.get(aux).get_agenda();
				String now = null;
				int DayOFMonth = 0;
				now = current_schedule.replaceAll("[^0-9]", "");
				DayOFMonth = Integer.parseInt(now);
				
				int dayp,monthp,yearp,week_dayp;
				dayp = HT.get(aux).get_first_payment_day();
				monthp = HT.get(aux).get_first_payment_month();
				yearp = HT.get(aux).get_first_payment_year();
				week_dayp = HT.get(aux).get_first_week_day();
				
				
				
				for(int i = 0;i < 31;i++) {
					if(DayOFMonth == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) DayOFMonth = 30;
					else if(DayOFMonth == 31 && monthp == 2) DayOFMonth = 28;
					dayp++;
					week_dayp++;
					if(dayp == DayOFMonth) break;
					if(week_dayp == 8) week_dayp = 1;
					else if(dayp == 29 && monthp == 2) {
						dayp = 1;
						monthp++;
					}
					if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
						dayp = 1;
						monthp++;
					}
					else if(dayp == 32) {
						dayp = 1;
						monthp++;
					}
					if(monthp == 13) {
						monthp = 1;
						yearp++;
					}
				}
				HT.get(aux).set_payment_day(dayp);
				HT.get(aux).set_payment_month(monthp);
				HT.get(aux).set_payment_year(yearp);
				HT.get(aux).set_payment_week_day(week_dayp);
			}
		}
		else if(HT.get(aux).get_type() == 'C') {
			
			String current_schedule = HT.get(aux).get_agenda();
			int DayIWant = 0;
			if(current_schedule == "Segunda Semanal") DayIWant = 2;
			else if(current_schedule == "Terca Semanal") DayIWant = 3;
			else if(current_schedule == "Quarta Semanal") DayIWant = 4;
			else if(current_schedule == "Quinta Semanal")  DayIWant = 5;
			else if(current_schedule == "Sexta Semanal" || current_schedule == "Bi-Semanalmente") DayIWant = 6;
			
			int dayp,monthp,yearp,week_dayp,totaldays = 0;
			dayp = HT.get(aux).get_first_payment_day();
			monthp = HT.get(aux).get_first_payment_month();
			yearp = HT.get(aux).get_first_payment_year();
			week_dayp = HT.get(aux).get_first_week_day(); 
			
			for(int i = 0;i < 15;i++) {
				dayp++;
				week_dayp++;
				if(week_dayp == 8) week_dayp = 1;
				if(week_dayp == DayIWant) {
					totaldays++;
				}
				if(week_dayp == DayIWant && totaldays == 2) {
					break;
				}
				if(dayp == 29 && monthp == 2) {
					dayp = 1;
					monthp++;
				}
				if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 32) {
					dayp = 1;
					monthp++;
				}
				if(monthp == 13) {
					monthp = 1;
					yearp++;
				}
			}
			HT.get(aux).set_payment_day(dayp);
			HT.get(aux).set_payment_month(monthp);
			HT.get(aux).set_payment_year(yearp);
			HT.get(aux).set_payment_week_day(week_dayp);
		}
	}
}