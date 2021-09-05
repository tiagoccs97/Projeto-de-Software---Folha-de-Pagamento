package fpg;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		
		double syndicate_tax = 100.00;
		double service_tax = 30.00;
		int sel = 0;
		int count_id = 0;
		
		while  (sel != 0) {
		
			System.out.println("Selecione uma das op��es:");
			System.out.println();
			System.out.println("0 - Sair");
			System.out.println("1 - Adicionar um empregado");
			System.out.println("2 - Remover um empregado");
			System.out.println("3 - Lan�ar um cart�o de ponto");
			System.out.println("4 - Lan�ar um resultado de venda");
			System.out.println("5 - Lan�ar uma taxa de servi�o");
			System.out.println("6 - Alterar detalhes de um empregado");
			System.out.println("7 - Rodar a folha de pagamento para hoje");
			System.out.println("8 - Undo/Redo:");
			System.out.println("9 - Agenda de pagamento");
			
			Scanner S = new Scanner(System.in);
			sel = S.nextInt();
			if(sel == 1) {
				
				System.out.println("Digite o Nome do empregado:");
				String name = S.nextLine();
				
				System.out.println("Digite o Endere�o do empregado:");
				String adress = S.nextLine();
				
				System.out.println("Digite o Tipo de empregado:");
				System.out.println("A - Assalariado");
				System.out.println("H - Horista");
				System.out.println("C - Comissionado");
				char type = S.next().charAt(0);
														
				System.out.println("Digite o sal�rio do empregado:");
				double salary = S.nextDouble();
				
				System.out.println("Digite o M�todo de Pagamento do empregado:");
				System.out.println("1 - Cheque pelos correios");
				System.out.println("2 - Cheque em m�os");
				System.out.println("3 - Dep�sito em conta banc�ria");
				int payment_method = S.nextInt();
				
				System.out.println("O empregado pertence a um sindicato? (1 - sim; 2 - n�o)");
				boolean syndicate = S.nextBoolean();
				
				Employee A = new Employee(name, adress, type, payment_method, syndicate);
				A.set_id(count_id);
				if(A.get_type() == 'A') {
					System.out.println("Digite o sal�rio mensal:");
					
				
				}	
				count_id++;
			}					
		}
	}
}
	

	

