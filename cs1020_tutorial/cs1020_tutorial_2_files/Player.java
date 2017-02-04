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
  public Hider(String locn, String name) { Player(locn, name); }     
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