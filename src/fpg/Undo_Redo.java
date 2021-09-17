package fpg;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;

public class Undo_Redo {
	private static Stack<Undo_Redo> Undo = new Stack<Undo_Redo>();
	private static Stack<Undo_Redo> Redo = new Stack<Undo_Redo>();
	private int old_sel,old_day,old_month,old_year,old_week_day,old_total_size,old_array_position;
	private Hashtable<Integer,Employee> oldHT =  new Hashtable<Integer,Employee>();
	
	
	public Undo_Redo(int sel,int day,int month,int year,int dayofweek,int totalsize,int positioninarray,Hashtable<Integer,Employee> employee) {
		set_old_sel(sel);
		set_old_day(day);
		set_old_month(month);
		set_old_year(year);
		set_old_week_day(dayofweek);
		set_oldHT(employee,totalsize);
		set_old_array_position(positioninarray);
	}
	
	public static void Save(int sel,int day,int month,int year,int week_day,int total_size,int positioninarray,Hashtable<Integer,Employee> employee) {
		Undo_Redo undo_redo = new Undo_Redo(sel,day,month,year,week_day,total_size,positioninarray,employee);
		Undo.push(undo_redo);
	}
	
	public static void SaveRedo(int sel,int day,int month,int year,int week_day,int total_size,int positioninarray,Hashtable<Integer,Employee> employee) {
		Undo_Redo undo_redo = new Undo_Redo(sel,day,month,year,week_day,total_size,positioninarray,employee);
		Redo.push(undo_redo);
	}

	public static Undo_Redo undo() {
		Undo_Redo current = null;
		if(Undo.size() != 0) {
			current = Undo.pop();
			if(Undo.size() != 0) current = Undo.pop();
			Redo.push(current);
		}
		return current;
	}

	public static Undo_Redo redo() {
		Undo_Redo current = null;
		if(Redo.size() != 0) {
			current = Redo.pop();
			Undo.push(current);
		}
		return current;
	}
	//Gets
	public int get_old_sel() {
		return old_sel;
	}

	public int get_old_month() {
		return old_month;
	}
	public int get_old_year() {
		return old_year;
	}
	public int get_old_day() {
		return old_day;
	}
	public int get_old_week_day() {
		return old_week_day;
	}
	public int get_old_array_position() {
		return old_array_position;
	}
	public int get_old_total_size() {
		return old_total_size;
	}
	//Sets
	public void set_old_sel(int oldsel) {
		this.old_sel = oldsel;
	}
	public void set_old_month(int oldmonth) {
		this.old_month = oldmonth;
	}
	public void set_old_year(int oldyear) {
		this.old_year = oldyear;
	}
	public void set_old_day(int oldday) {
		this.old_day = oldday;
	}
	public void set_old_week_day(int olddayofweek) {
		this.old_week_day = olddayofweek;
	}
	public void set_old_array_position(int oldpositioninarray) {
		this.old_array_position = oldpositioninarray;
	}
	public void set_old_total_size(int oldtotalsize) {
		this.old_total_size = oldtotalsize;
	}	public Map<Integer,Employee> get_old_hashtable() {
		return oldHT;
	}
	public void set_oldHT(Map<Integer,Employee> HT,int total_size) {
		//Set the whole Map to save state
		double commission = 0;
		for(int currentid = 1;currentid <= total_size;currentid++) {
			if(HT.containsKey(currentid)) {
				if(HT.get(currentid) instanceof Employee) {
					if(HT.get(currentid).get_type() == 'C') {
						Commissioned current = (Commissioned) HT.get(currentid);
						commission = current.get_commission_percentage();
					}
					Employee.add_employee(this.oldHT, HT.get(currentid).get_name(), HT.get(currentid).get_adress(), HT.get(currentid).get_type(), HT.get(currentid).get_payment_method(), HT.get(currentid).get_agenda(), currentid, HT.get(currentid).get_salary(), HT.get(currentid).get_first_payment_day(), HT.get(currentid).get_first_payment_month(), HT.get(currentid).get_first_payment_year(), HT.get(currentid).get_first_week_day(), HT.get(currentid).get_syndicate(), HT.get(currentid).get_service_tax(),commission);
				}
			}
		}
	}
}