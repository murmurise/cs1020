class Player {      
	protected String _location; // e.g. COM2-02-20      
	protected String _name; // e.g. ictm      

	public Player(String locn, String name) {           
		_location = locn; 
		_name = name;      
	} 
	public String getName() {
		return _name; 
	}      
	public void ping() {          
		System.out.println(_name + " is at " + _location);      
	}
}

class Hider extends Player {    
	protected boolean _isHiding;     
	public Hider(String locn, String name) { super(locn, name); }     
	public void ping() {           
		if(_isHiding) System.out.println(getName() + " is hiding!");          
		else super.ping();     
	}      
	public void hide() { _isHiding = true; }      
	public void expose() { _isHiding = false; } 
} 

class Stealther extends Hider {      
	private boolean _isInvisible;      
	public Stealther(String locn, String name) { super(locn, name); }      
	public void ping() { if(!_isInvisible) super.ping(); }      
	public void activateStealth() { _isInvisible = true; }      
	public void expose() { _isHiding = false; _isInvisible = false; }
}

public class testPlayer{
	public static void main(String[] args){
		Player _alex = new Player("com1", "alex");
		_alex.ping();


		Hider _kc = new Hider("com1", "kc");
		_kc.hide();
		_kc.ping();
		_kc.expose();
		_kc.ping();

		Stealther _victor = new Stealther ("com1", "victor");
		_victor.activateStealth();
		_victor.ping();
		_victor.expose();
		_victor.ping();
	}
}
