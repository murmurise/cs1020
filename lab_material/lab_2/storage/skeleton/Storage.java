/**
 * Name         :
 * Matric No.   :
 * Plab Acct.   :
 */

import java.util.*;

public class Storage {

	public void run() {
		Scanner sc=new Scanner(System.in);
		int boxNum=sc.nextInt();
		int portableSize=sc.nextInt();
		int boxSize=sc.nextInt();
		Box portable = new Box(portableSize);
		ArrayList<Box> boxs=new ArrayList<Box>(boxNum);
		for(int i=0;i<boxNum;i++){
			boxs.add(new Box(boxSize));
		}
		//System.out.println("has "+boxs.size()+" boxs");

		int opNum=sc.nextInt();

		for (int o=0; o<opNum; o++){
			sc.nextLine();
			String operation=sc.next();
			//System.out.println("echo operation "+operation);
			if (operation.equals("purchase")){
				Item current = new Item (sc.next(),sc.nextInt());
				if (portable.deposit(current))
					System.out.println("item "+current.get_name()+" is now being held");
				else {
					for(int i=0;i<boxNum;i++){
						if(boxs.get(i).deposit(current)){
							System.out.println("item "+current.get_name()+" has been deposited to box "+(i+1));
							break;
						}
					}
				}
			}
			else if (operation.equals("deposit")){
				String item_name=sc.next();
				int box_index=searchStorage(boxs,item_name);
				if(box_index!=-1)
					System.out.println("item "+item_name+" is already in storage");
				else if(portable.search_for_item(item_name)==-1)
					System.out.println("item "+item_name+" does not exist");
				else{
					for(int i=0;i<boxNum;i++){
						if(boxs.get(i).is_not_full()){
							boxs.get(i).deposit(portable.withdraw(item_name));
							System.out.println("item "+item_name+" has been deposited to box "+(i+1));
							break;
						}
					}
				}
			}	
			else if (operation.equals("withdraw")){
				String item_name=sc.next();
				portable.get_registry();
				int box_index=searchStorage(boxs,item_name);

				if(portable.search_for_item(item_name)!=-1)
					System.out.println("item "+item_name+" is already being held");
				else if(box_index!=-1){
					if(portable.is_not_full()){
						portable.deposit(boxs.get(box_index).withdraw(item_name));
						System.out.println("item "+item_name+" has been withdrawn");
					}
					else
						System.out.println("cannot hold any more items");
				} else {
					System.out.println("item "+item_name+" does not exist");
				}
			}
			else if(operation.equals("location")){
				String item_name=sc.next();
				int box_index=searchStorage(boxs,item_name);
				if(portable.search_for_item(item_name)>-1)
					System.out.println("item "+item_name+" is being held");
				else if(box_index==-1)
					System.out.println("item "+item_name+" does not exist");
				else
					System.out.println("item "+item_name+" is in box "+(box_index+1));
			}
			else if(operation.equals("valuable")){
				Item most_value=null;
				for(int i=0;i<boxs.size();i++){
					if(boxs.get(i).most_value()!=null){
						most_value=boxs.get(i).most_value();
						break;
					}
				}
				if(most_value==null)
					most_value=portable.most_value();
				for(int i=1;i<boxs.size();i++){
					if(boxs.get(i).most_value()==null)
						continue;
					else if(boxs.get(i).most_value().has_more_value_than(most_value))
						most_value=boxs.get(i).most_value();
				}
				if(portable.most_value()!=null){
					if(portable.most_value().has_more_value_than(most_value))
						most_value=portable.most_value();

				}
				System.out.println(most_value.get_name());
			}
		}
	}
	public int searchStorage(ArrayList<Box> storage, String query){
		for(int i=0;i<storage.size();i++){
			if(storage.get(i).search_for_item(query)!=-1)
				return i;
		}
		return -1;

	}
	public static void main(String[] args) {
		Storage myStorageSystem = new Storage();
		myStorageSystem.run();
	}

}

class Box {
	private String[] _registry;
	private ArrayList<Item> _content;
	private int _capacity;

	public Box(){};
	public Box(int capacity){
		_capacity=capacity;
		_content=new ArrayList<Item>(capacity);
	}
	public boolean deposit(Item item){
		//return true if enough space to deposit
		if(this.is_not_full())
			return _content.add(item);
		else 
			return false;
	}
	public Item withdraw(String query){
		//search registry for the query/item name
		int index=search_for_item(query);
		if(index==-1)
			return null;
		else{
			Item copy=_content.get(index);
			_content.remove(index);
			return copy;
		}
	}
	public int search_for_item(String query){
		get_registry();
		//return index if exist, -1 if not
		for(int i=0;i<_content.size();i++){
			if(_registry[i].equals(query))
				return i;
		}
		return -1;
	}
	public String[] get_registry(){
		//update the registry accroding to the current content of the box;return a string array
		_registry=new String[_content.size()];
		for (int i=0; i<_content.size();i++){
			_registry[i]=_content.get(i).get_name();
		}
		return _registry;
	}
	public boolean is_not_full(){
		return (_capacity>_content.size());
	}
	public Item most_value(){
		if(_content.size()==0)
			return null;
		else{
			Item most_value=_content.get(0);
			for(int i=1;i<_content.size();i++){
				if(_content.get(i).has_more_value_than(most_value))
					most_value=_content.get(i);
			}
			return most_value;
		}
	}
}

class Item {
	private String _name;
	private int _value;

	public Item(){};
	public Item(String name,int value){
		_name=name;
		_value=value;
	}
	public int get_value(){
		return _value;
	}
	public String get_name(){
		return _name;
	}
	public boolean has_more_value_than(Item another){
		if(this.get_value()>another.get_value())
			return true;
		else if(this.get_value()==another.get_value() && this.get_name().compareTo(another.get_name())<0)
			return true;
		return false;
	}
}
