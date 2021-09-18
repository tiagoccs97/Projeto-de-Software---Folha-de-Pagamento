package fpg;
import java.util.Scanner;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Hashtable;
import java.lang.*;

public class Main {	
	public static void main(String[] args) {		
		int sel = -1;
		int count_id = 0;
		int count_s = 0;
		int array_position=-1;
		int aux=0;
		int payment_method=1;
		int day=0;
		int month=0;
		int year=0;
		int week_day=0;
		int total_size=0;
		char type='A';
		boolean syndicate=false;
		double salary=0;
		double syndicate_tax=0;
		double aux_d=0;
		String name;
		String adress;
		String sell_date;
		@SuppressWarnings("resource")
		Scanner S = new Scanner(System.in);
		Hashtable<Integer, Employee> HT = new Hashtable<Integer, Employee>();	
		ArrayList<PaymentRoll> Payment_Schedules = new ArrayList<PaymentRoll>();
		
		Employee A = new Employee();
		//Calendario
		Calendar newCalendar = Calendar.getInstance();
		day = newCalendar.get(Calendar.DAY_OF_MONTH);
		month = newCalendar.get(Calendar.MONTH);
		month++;
		year = newCalendar.get(Calendar.YEAR);
		week_day = newCalendar.get(Calendar.DAY_OF_WEEK);
		PaymentRoll SetDefault0 = new PaymentRoll();
		SetDefault0.set_schedule("Semanalmente");
		SetDefault0.set_employee_type('H');
		Payment_Schedules.add(SetDefault0);
		
		PaymentRoll SetDefault1 = new PaymentRoll();
		SetDefault1.set_schedule("Mensalmente");
		SetDefault1.set_employee_type('A');
		Payment_Schedules.add(SetDefault1);
		
		PaymentRoll SetDefault2 = new PaymentRoll();
		SetDefault2.set_schedule("Bi-semanalmente");
		SetDefault2.set_employee_type('C');
		Payment_Schedules.add(SetDefault2);
		//Done
		Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);		
		while  (sel != 0) { //Comeca a rodar a folha e mostra as opcoes					
			System.out.println("Selecione uma das opcoes:");
			System.out.println();
			System.out.println("0 - Sair");
			System.out.println("1 - Adicionar um empregado");
			System.out.println("2 - Remover um empregado");
			System.out.println("3 - Lançar um cartão de ponto");
			System.out.println("4 - Lançar um resultado de venda");
			System.out.println("5 - Lançar uma taxa de servico");
			System.out.println("6 - Alterar detalhes de um empregado");
			System.out.println("7 - Rodar a folha de pagamento para hoje");
			System.out.println("8 - Undo/Redo:");
			System.out.println("9 - Agenda de pagamento");	
			System.out.println("10 - Criar agenda de pagamento");
			System.out.println("11 - Mostrar funcionarios cadastrados");
			System.out.println();			
			
				sel = S.nextInt();
				S.nextLine();
			
			
		
			//Baseado no numero recebido pelo sistema, o mesmo ira para a operacao desejada. Obs.: sel = selector
			if(sel == 1) { // Adicionar funcionario				
				System.out.println("Digite o Nome do empregado:");
				name = S.nextLine();				
				System.out.println("Digite o Endereço do funcionario:");
				adress = S.nextLine();					
				try {
					System.out.println("Digite o Tipo de funcionario:");
					System.out.println("A - Assalariado");
					System.out.println("H - Horista");
					System.out.println("C - Comissionado");
					type = S.next().charAt(0);						
				}		
				catch(NullPointerException e) {
					System.out.println("Digite um caractere valido!!!");
				}
				catch(InputMismatchException e) {
					System.out.println("Digite um caractere valido!!!");
				}
				try{
					System.out.println("Digite o Metodo de Pagamento do funcionario:");
					System.out.println("1 - Cheque pelos correios");
					System.out.println("2 - Cheque em mãos");
					System.out.println("3 - Deposito em conta bancaria");
					payment_method = S.nextInt();
				}	
				catch(NullPointerException e) {
					System.out.println("Digite um numero valido!!!");
				}
				catch(InputMismatchException e) {
					System.out.println("Digite um numero valido!!!");
				}
				catch(NumberFormatException e) {
					System.out.println("Digite um numero valido!!!");
				}
				
				
				try{
					System.out.println("O empregado pertence a um sindicato? (true - sim; false - não)");
					syndicate = S.nextBoolean();
				}	
				catch(NullPointerException e) {
					System.out.println("Digite uma alternativa valida!!!");

				}
				catch(InputMismatchException e) {
					System.out.println("Digite uma alternativa valida!!!");
				}
				if(type == 'A') {
					try{
						System.out.println("Digite o salario mensal:");
						aux_d = S.nextDouble();
					}
					catch(NullPointerException e) {
						System.out.println("Digite um numero valido!!!");
					}
					catch(InputMismatchException e) {
						System.out.println("Digite um numero valido!!!");
					}
					catch(NumberFormatException e) {
						System.out.println("Digite um numero valido!!!");
					}
					
					A = new Employee(name, adress, type, payment_method, "Mensalmente", count_id, salary, day, month, year, week_day,syndicate,syndicate_tax);
					A.set_salary(aux_d);							
					if(syndicate == true) {
						System.out.println("Digite a taxa sindical deste funcionario");
						syndicate_tax = S.nextDouble();
						A.set_syndicate_tax(syndicate_tax);
						A.set_id_s(count_s+100);
						count_s++;
					}
					HT.put(count_id, A);					
				}	
				else if(type == 'H') {
					try{						
						System.out.println("Digite o salario por hora trabalhada:");
						salary = S.nextDouble();
					}
					catch(NullPointerException e) {
						System.out.println("Digite um numero valido!!!");
					}
					catch(InputMismatchException e) {
						System.out.println("Digite um numero valido!!!");
					}
					catch(NumberFormatException e) {
						System.out.println("Digite um numero valido!!!");
					}
					
					A = new Hourly(-1, -1, 0, 0, name, adress, type, payment_method, "Semanalmente", count_id, salary,day, month, year, week_day,syndicate,syndicate_tax);
					if(syndicate == true) {
						System.out.println("Digite a taxa sindical deste funcionario");
						syndicate_tax = S.nextDouble();
						A.set_syndicate_tax(syndicate_tax);
						A.set_id_s(count_s+100);
						count_s++;
					}
					HT.put(count_id, A);							
				}	
				else if(type == 'C') {
					try{						
						System.out.println("Digite a porcentagem da commissao:");
						aux_d = S.nextDouble();
					}
					catch(NullPointerException e) {
						System.out.println("Digite um numero valido!!!");
					}
					catch(InputMismatchException e) {
						System.out.println("Digite um numero valido!!!");
					}
					catch(NumberFormatException e) {
						System.out.println("Digite um numero valido!!!");
					}					
					A = new Commissioned(0 , 0, name, adress, type, payment_method, "Bi-Semanalmente", count_id, salary, day, month, year, week_day,syndicate,syndicate_tax, aux_d);
					if(syndicate == true) {
						System.out.println("Digite a taxa sindical deste funcionario:");
						syndicate_tax = S.nextDouble();
						A.set_syndicate_tax(syndicate_tax);
						A.set_id_s(count_s+100);
						count_s++;
					}
					HT.put(count_id, A);									
				}				
				System.out.println();
				System.out.println("Os dados de " + name + " foram adicionados com sucesso!");
				System.out.println();
				Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
				count_id++;
			}
			if(sel == 2) { // Remover funcionario
				System.out.println("Lista de funcionários cadastrados:");
				System.out.println();
				for(Integer X : HT.keySet()) { HT.get(X).show_employee_data(); }
				System.out.println();
				System.out.println("Digite o ID do funcionario a ser removido:");
				aux = S.nextInt();
				if(aux < 0) {
					System.out.println("Digite um valor inteiro!!!");
					System.out.println("Operacao nao realizada, voltando ao menu...");
					S.next();
				}
				else { 
					HT.remove(aux);		
					Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
				}
			}
			if(sel == 3) { // lancar cartao de ponto
				int current_employee_id = -1;
				System.out.println("Lista de funcionários cadastrados:");
				System.out.println();
				for(Integer X : HT.keySet()) { HT.get(X).show_employee_data(); }
				System.out.println();
				System.out.println("Digite o ID do funcionario desejado: ");
				//Try
				try{
					current_employee_id = S.nextInt();
					
				}
				catch(NumberFormatException e){
					System.out.println("Valor no formato incorreto. Digite novamente:");
				}
				catch(InputMismatchException e) {
					System.out.println("Valor no formato incorreto. Digite novamente:");
				}
				S.nextLine();
				
				
				if(HT.containsKey(current_employee_id)) {
					if(HT.get(current_employee_id).get_type() == 'H') {
						int arrival = -1,exit = -1;
						if(HT.get(current_employee_id) instanceof Employee) {
							boolean acceptindex = true;
							Hourly current_employee = (Hourly) HT.get(current_employee_id);
							if(current_employee.get_check_setofday() == 0) {
								Hourly aux_hourly = new Hourly(-1,-1,0,0,current_employee.get_name(),current_employee.get_adress(),current_employee.get_type(),current_employee.get_payment_method(),current_employee.get_agenda(),current_employee.get_id(),current_employee.get_salary(),current_employee.get_first_payment_day(),current_employee.get_first_payment_month(),current_employee.get_first_payment_year(),current_employee.get_first_week_day(),current_employee.get_syndicate(),current_employee.get_syndicate_tax());
								aux_hourly.set_arrival_time(-1);
								aux_hourly.set_exittime(-1);
								aux_hourly.set_daily_payment(0);
								current_employee.get_PaymentDaily().add(aux_hourly);
								current_employee.set_check_setofday(1);
								array_position = current_employee.get_PaymentDaily().size()-1;
							}
							if(current_employee.get_check_setofday() == 1){
								//Try
								try {
									current_employee.get_PaymentDaily().get(array_position).get_arrival_time();
								}
								catch(IndexOutOfBoundsException e) {
									System.out.println("Nao foi possivel acessar.Necessario informar hora de chegada novamente.Selecione a opcao novamente.");
									array_position--;
									acceptindex = false;
								}
								if(acceptindex)
								{
									if((current_employee.get_PaymentDaily().get(array_position).get_arrival_time() == -1)) {
										System.out.println("Informe a hora de chegada do empregado: ");
										//Try
										
										try{
											arrival = S.nextInt();
											
										}
										catch(NumberFormatException e){
											System.out.println("Valor no formato incorreto. Digite novamente:");
										}
										catch(InputMismatchException e) {
											System.out.println("Valor no formato incorreto. Digite novamente:");
										}
										S.nextLine();
										
										
										current_employee.get_PaymentDaily().get(array_position).set_arrival_time(arrival);
										Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
									}
									else if(current_employee.get_PaymentDaily().get(array_position).get_exittime() == -1) {
											System.out.println("Informe a hora de saida do empregado: ");
											//Try
											
											try{
												exit = S.nextInt();
													
											}
											catch(NumberFormatException e){
												System.out.println("Valor no formato incorreto. Digite novamente:");
											}
											catch(InputMismatchException e) {
												System.out.println("Valor no formato incorreto. Digite novamente:");
											}
											S.nextLine();
											
											
											current_employee.get_PaymentDaily().get(array_position).set_exittime(exit);
											Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
									}
								}
							}
						}
						else System.out.println("Nao foi possivel associar empregado.");
					}
					else System.out.println("Empregado nao e horista.");
				}
				else System.out.println("Nao foi possivel encontrar o empregado.");
				
				System.out.println("Pressione enter para continuar.");
				S.nextLine();
			}
			if(sel == 4) { // lancar resultado de venda
				System.out.println("Lista de funcionários cadastrados:");
				System.out.println();
				for(Integer X : HT.keySet()) { HT.get(X).show_employee_data(); }
				System.out.println();
				System.out.println("Digite o ID do funcionario desejado:");
				aux = S.nextInt();
				S.nextLine();
				if(aux < 0) {
					System.out.println("Digite um valor inteiro!!!");
					System.out.println("Operacao nao realizada, voltando ao menu...");
					S.next();					
				}
				else if(HT.get(aux).get_type() != 'C') {
					System.out.println("Escolha um funcionario Comissionado!!!");
					System.out.println("Operacao nao realizada, voltando ao menu...");
					S.next();
				}
				else{	
					Commissioned aux_commissioned = (Commissioned) HT.get(aux);
					System.out.println("Digite a data da venda");
					sell_date = S.nextLine();
					System.out.println("Digite o valor da venda:");
					aux_d = S.nextDouble();
					aux_commissioned.set_sells(aux_d);
					System.out.println("Venda lancada com sucesso, confira os dados da venda:");
					System.out.println("Data da venda: " + sell_date + " Valor da venda: " + aux_d);
					System.out.println();
				}
				Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
			}
			if(sel == 5){ // lancar taxa de servico
				System.out.println("Lista de funcionarios cadastrados:");
				System.out.println();
				for(Integer X : HT.keySet()) { HT.get(X).show_employee_data(); }
				System.out.println();
				try{
					System.out.println("Digite o ID do funcionario desejado:");
					aux = S.nextInt();
				}
				catch(NullPointerException e) {
					System.out.println("Digite um numero valido!!!");
				}
				catch(InputMismatchException e) {
					System.out.println("Digite um numero valido!!!");
				}
				catch(NumberFormatException e) {
					System.out.println("Digite um numero valido!!!");
				}
				if(aux < 0){
					System.out.println("Digite um valor inteiro!!!");
					System.out.println("Operacao nao realizada, voltando ao menu...");
					S.next();					
				}
				else if(HT.get(aux).get_syndicate() == true) {
				
					if(HT.containsKey(aux)) {
						if(HT.get(aux)instanceof Employee) {
					
							System.out.println("Digite a porcentagem da taxa:");
							aux_d = S.nextDouble();		
							HT.get(aux).set_service_tax(aux_d);
						}
					}
				}
				Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);				
			}
			if(sel == 6){ // Alterar detalhes de um funcionario
				System.out.println();
				System.out.println("Lista de funcionarios cadastrados:");
				System.out.println();
				for(Integer X : HT.keySet()) { HT.get(X).show_employee_data(); }
				System.out.println();
				try {
					System.out.println("Digite o ID do funcionario a ser alterado:");
					aux = S.nextInt();
				}
				catch(NumberFormatException e){
					System.out.println("Digite um valor inteiro!!!");
					System.out.println("Operacao nao realizada, voltando ao menu...");
				}	
				catch(InputMismatchException e)
				{
					System.out.println("Digite um valor inteiro!!!");
					System.out.println("Operacao nao realizada, voltando ao menu...");
				}
				
				 HT.get(aux).change_employee_data(HT.get(aux));
				 Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
			}
			if(sel == 7){ // rodar folha para hoje
				for(int i = 0;i <= total_size;i++) {
					if(HT.containsKey(i)) {
						if(HT.get(i).get_type() == 'A') Employee.calculate_default_payment(HT,i);
						if(HT.get(i).get_type() == 'H') {
							Hourly currentemployee = (Hourly) HT.get(i);
							currentemployee.set_check_setofday(0);
							Hourly.calculate_hourly_payment(HT,i);
						}
						else if(HT.get(i).get_type() == 'C') Commissioned.calculate_commissioned_payment(HT,i);
					}
				}
				PaymentRoll.pass_day(HT, day, month, year, week_day,total_size);
				day++;
				week_day++;
				if(week_day == 8) week_day = 1;
				if(day == 29 && month == 2) {
					day = 1;
					month++;
				}
				else if(day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
					day = 1;
					month++;
				}
				else if(day == 32) {
					day = 1;
					month++;
				}
				if(month == 13) {
					month = 1;
					year++;
				}
				Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
				System.out.println("Finalizado. Digite enter para continuar.");
				S.nextLine();			
			}
			if(sel == 8){ // Undo/Redo
				String aux_string;
				System.out.println("Digite uma das opcoes: Undo ou Redo");
				aux_string = S.nextLine();
				if(aux_string.equals("Undo")) {
					Undo_Redo undo;
					undo = Undo_Redo.undo();
					if(undo != null) {
						Undo_Redo.SaveRedo(sel,day,month,year,week_day,total_size,array_position,HT);
						sel = undo.get_old_sel();
						day = undo.get_old_day();
						month = undo.get_old_month();
						year = undo.get_old_year();
						week_day = undo.get_old_week_day();
						HT.clear();
						double commission = 0;
						for(int Y = 1;Y <= total_size;Y++) {
							if(undo.get_old_hashtable().containsKey(Y)) {
								if(undo.get_old_hashtable().get(Y) instanceof Employee) {
									if(undo.get_old_hashtable().get(Y).get_type() == 'C') {
										Commissioned current = (Commissioned) undo.get_old_hashtable().get(Y);
										commission = current.get_commission_percentage();
									}
									Employee.add_employee(HT, undo.get_old_hashtable().get(Y).get_name(), undo.get_old_hashtable().get(Y).get_adress(), undo.get_old_hashtable().get(Y).get_type(), undo.get_old_hashtable().get(Y).get_payment_method(),undo.get_old_hashtable().get(Y).get_agenda(), Y, undo.get_old_hashtable().get(Y).get_salary(), undo.get_old_hashtable().get(Y).get_first_payment_day(), undo.get_old_hashtable().get(Y).get_first_payment_month(), undo.get_old_hashtable().get(Y).get_first_payment_year(), undo.get_old_hashtable().get(Y).get_first_week_day(), undo.get_old_hashtable().get(Y).get_syndicate(), undo.get_old_hashtable().get(Y).get_syndicate_tax(),commission);
								}
							}
						}
						array_position = undo.get_old_array_position();
						System.out.println("Undone.");
						Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
					}
					else System.out.println("Nao pode Undo.");
					
				}
				else if(aux_string.equals("Redo")) {
					Undo_Redo redo;
					redo = Undo_Redo.redo();
					if(redo != null) {
						sel = redo.get_old_sel();
						day = redo.get_old_day();
						month = redo.get_old_month();
						year = redo.get_old_year();
						week_day = redo.get_old_week_day();
						HT.clear();
						double commission = 0;
						for(int currentid = 0;currentid <= total_size;currentid++) {
							if(redo.get_old_hashtable().containsKey(currentid)) {
								if(redo.get_old_hashtable().get(currentid) instanceof Employee) {
									if(redo.get_old_hashtable().get(currentid).get_type() == 'C') {
										Commissioned current = (Commissioned) redo.get_old_hashtable().get(currentid);
										commission = current.get_commission_percentage();
									}
									Employee.add_employee(HT, redo.get_old_hashtable().get(currentid).get_name(), redo.get_old_hashtable().get(currentid).get_adress(), redo.get_old_hashtable().get(currentid).get_type(), redo.get_old_hashtable().get(currentid).get_payment_method(),redo.get_old_hashtable().get(currentid).get_agenda(), currentid, redo.get_old_hashtable().get(currentid).get_salary(), redo.get_old_hashtable().get(currentid).get_first_payment_day(), redo.get_old_hashtable().get(currentid).get_first_payment_month(), redo.get_old_hashtable().get(currentid).get_first_payment_year(), redo.get_old_hashtable().get(currentid).get_first_week_day(), redo.get_old_hashtable().get(currentid).get_syndicate(), redo.get_old_hashtable().get(currentid).get_syndicate_tax(),commission);
								}
							}
						}
						array_position = redo.get_old_array_position();
						System.out.println("Redone.");
						Undo_Redo.Save(sel,day,month,year,week_day,total_size,array_position,HT);
					}
					else System.out.println("Nao pode Redo.");
				}
				else System.out.println("Entrada Invalida.");
			}
				
			
			if(sel == 9) { // modificar agenda de pagamento				
				int y =-1;
				System.out.println("Lista de funcionários cadastrados:");
				System.out.println();
				for(Integer X : HT.keySet()) { HT.get(X).show_employee_data(); }
				System.out.println();
				System.out.println("Informe o Id do empregado: ");
				//Try
				
				try{
					y = S.nextInt();
				
				}
				catch(NumberFormatException e){
					System.out.println("Valor no formato incorreto. Digite novamente:");
				}
				catch(InputMismatchException e) {
					System.out.println("Valor no formato incorreto. Digite novamente:");
				}
				S.nextLine();
				
				if(HT.containsKey(y)) {
					if(HT.get(y) instanceof Employee) {
						String entry;
						System.out.println("Agenda de Pagamento atual - O Empregado e pago: " + HT.get(y).get_agenda());
						System.out.println("Deseja modificar a Agenda de Pagamento? Se sim, pressione 1. Se nao, pressione enter");
						entry = S.nextLine();
						if(entry.equals("1")) {
							boolean acceptindex = true;
							System.out.println("Informe uma das opcoes abaixo: ");
							for(int i = 0;i < Payment_Schedules.size();i++) {
								//Try
								try {
									Payment_Schedules.get(i);
								}
								catch(IndexOutOfBoundsException e) {
									acceptindex = false;
								}
								if(acceptindex) {
									if(HT.get(y).get_type() == Payment_Schedules.get(i).get_employee_type()) {
										System.out.println(Payment_Schedules.get(i).get_schedule());
									}
									acceptindex = true;
								}
							}
							acceptindex = true;
							boolean validateschedule = false;
							String schedule;
							schedule = S.nextLine();
							for(int i = 0;i < Payment_Schedules.size();i++) {
								//Try
								try {
									Payment_Schedules.get(i);
								}
								catch(IndexOutOfBoundsException e) {
									acceptindex = false;
								}
								if(acceptindex) {
									
									if(schedule.equals(Payment_Schedules.get(i).get_schedule())) {
										validateschedule = true;
										break;
									}
									acceptindex = true;
								}
							}
							if(validateschedule) {
								if(HT.get(y).get_type() == 'A' && (!(schedule.equals("Mensalmente") && !(schedule.equals("Mensal"))))) {
									String now = null;
									int DayOFMonth = 0;
									now = schedule.replaceAll("[^0-9]", "");
									DayOFMonth = Integer.parseInt(now);
									if(DayOFMonth > day) {
										HT.get(y).set_agenda(schedule);
										PaymentRoll.set_payment_schedule(HT, y);
									}
									else System.out.println("Nao foi possivel atualizar, data invalida");
								}
								else {
									HT.get(y).set_agenda(schedule);
									PaymentRoll.set_payment_schedule(HT, aux);
								}
							}
							else System.out.println("Agenda nao existe.");
						}
					}
					else System.out.println("Nao foi possivel associar empregado.");
				}
				else System.out.println("Nao foi possivel encontrar o empregado.");
				System.out.println("Agenda de " + HT.get(y).get_name() + " modificada com sucesso!! " + "Pressione enter para continuar.");
				S.nextLine();
			}
			if(sel == 10) // criar agenda de pagamento				
			{
				boolean verify = true,acceptindex = true;
				String new_payment_schedule;
				Character employeetype;
				System.out.println("Digite a nova Agenda de Pagamento(Segunda Semanal,Terça Semanal, Mensalmente, Bi-Semanalmente, Semanalmente, etc): ");
				new_payment_schedule = S.nextLine();
				System.out.println("Digite o tipo de empregado(A = Assalariado,H = Horista,C = Comissionado): ");
				employeetype = S.next().charAt(0);
				for(int i = 0;i < Payment_Schedules.size();i++) {
					//Try
					try {
						Payment_Schedules.get(i);
					}
					catch(IndexOutOfBoundsException e) {
						acceptindex = false;
					}
					if(acceptindex) {
						if(Payment_Schedules.get(i).get_schedule().equals(new_payment_schedule) && Payment_Schedules.get(i).get_employee_type() == employeetype) {
							System.out.println("Agenda ja existe");
							verify = false;
							break;
						}
					}					
				}
				if(verify) {
					PaymentRoll SetNew = new PaymentRoll();
					SetNew.set_schedule(new_payment_schedule);
					SetNew.set_employee_type(employeetype);
					Payment_Schedules.add(SetNew);
					System.out.println("Agenda criada com sucesso.");
				}
				System.out.println("Pressione enter para continuar.");
				S.nextLine();
			}		
			if(sel == 11) { //Mostrar funcionarios cadastrados
				System.out.println();
				System.out.println("Lista de funcionários cadastrados:");
				System.out.println();
				for(Integer X : HT.keySet()) { HT.get(X).show_employee_data(); }
				System.out.println();
			}
		//S.close();
		}
	}
}
	

//Character.toString

