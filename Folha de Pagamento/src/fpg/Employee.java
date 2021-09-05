package fpg;

public class Employee {
	
	private String name;
	private String adress;
	private int id;
	private int id_s;
	private String week_day;
	private int day;
	private int month;
	private int year;
	private int payment_method;
	private char type;
	private boolean syndicate;
	private double syndicate_tax;
	private double service_tax;
	private double salary;


	//Gets
	
	public String get_name(){
		return name;
	}
	public String adress(){
		return adress;
	}
	public int get_id(){
		return id;
	}
	public int get_id_s(){
		return id_s;
	}
	public String get_week_day(){
		return week_day;
	}
	public int get_day(){
		return day;
	}
	public int get_month(){
		return month;
	}
	public int get_year(){
		return year;
	}
	public int get_payment_method(){
		return payment_method;
	}
	public char get_type(){
		return type;
	}
	public boolean get_syndicate(){
		return syndicate;
	}
	public double get_syndicate_tax(){
		return syndicate_tax;
	}
	public double get_service_tax(){
		return service_tax;
	}
	public double salary(){
		return salary;
	}
	//Sets
	
	public void set_name(String name) {
		this.name = name;
		
	}
	public void set_adress(String adress) {
		this.adress = adress;
		
	}
	public void set_id(int id) {
		this.id = id;
	}
	public void set_id_s(int id_s) {
		this.id_s = id_s;
	}
	
	public void set_week_day(String week_day){
		this.week_day = week_day;
	}
	public void set_day(int day){
		this.day = day;
		
	}
	public void set_month(int month) {
		this.month = month;
		
	}
	public void set_year(int year){
		this.year = year;
		
	}
	public void set_payment_method(int payment_method){
		this.payment_method = payment_method;
		
	}
	public void set_type(char type){
		this.type = type;
		
	}
	public void set_syndicate(boolean syndicate){
		this.syndicate = syndicate;
	}
	public void set_syndicate_tax(double syndicate_tax){
		this.syndicate_tax = syndicate_tax;
	}
	public void set_service_tax(double service_tax){
		this.service_tax = service_tax;
	}
	public void salary(double salary){
		this.salary = salary;
	}
	
	//Constructor
	
	public Employee(String name, String adress, char type, int payment_method, boolean syndicate) {
		
		this.id = 0;
		this.id_s = 0;
		this.name = name;
		this.adress = adress;
		this.type = type;
		this.payment_method = payment_method;
		this.syndicate = syndicate;
		this.week_day = "0";
		this.day = 0;
		this.month = 0;
		this.year = 0;
		this.syndicate_tax = 0;
		this.service_tax = 0;
		
	}
	
}

// parametros q não são fornecidos: setar como nulo; fazer o construtor do hourly e commissioned e as variáveis; na main: criar um loop infinito e dentro do loop colocar as opções(adicionar func, etc); guardar todos os funcionários; chamar o construtor na main e add o objeto à lista de empregados;