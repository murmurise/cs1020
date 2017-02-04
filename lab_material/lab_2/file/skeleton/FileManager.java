/*
Name: Zhang Yuan (alex)
Matric Number: a0157059x
 */

import java.util.*;

public class FileManager {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Vector<Folder> folderList=new Vector<Folder>();
		int operation_no=sc.nextInt();
		for (int n=0; n<operation_no;n++){
			sc.nextLine();
			String operation=sc.next();
			if(operation.equals("Createfolder")){
				String name=sc.next();
				folderList.add(new Folder(name));
			}
			else if(operation.equals("Createfile")){
				String name=sc.next();
				int fileSize=sc.nextInt();
				String directory=sc.next();

				for(int i=0;i<folderList.size();i++){
					if(folderList.get(i).getName().equals(directory)){
						folderList.get(i).addFile(new File(name,fileSize,directory));
					}
				}
			}
			else if(operation.equals("Deletefile")){
				String file=sc.next();
				for (int i=0;i<folderList.size();i++){
					if(folderList.get(i).contains(file)){
						folderList.get(i).removeFile(folderList.get(i).searchForFile(file));
					}		
				}
			}
			else if(operation.equals("Count")){
				String folder=sc.next();
				for(int i=0;i<folderList.size();i++){
					if(folderList.get(i).getName().equals(folder)){
						System.out.println(folderList.get(i).getSize());
						break;
					}
				}
			}
			else if(operation.equals("Movefile")){
				String file=sc.next();
				String destination=sc.next();
				File copy=new File();
				for(int i=0;i<folderList.size();i++){
					if(folderList.get(i).contains(file)){
						copy = folderList.get(i).searchForFile(file);
						folderList.get(i).removeFile(folderList.get(i).searchForFile(file));
						break;
					}
				}
				for(int i=0;i<folderList.size();i++){
					if(folderList.get(i).getName().equals(destination)){
						folderList.get(i).addFile(copy);
					}
				}
			}
			else if(operation.equals("Findlargest")){
				int largest_index=0;
				for (int i=1;i<folderList.size();i++){
					if(folderList.get(i).getSize()>folderList.get(largest_index).getSize())
						largest_index=i;
				}
				System.out.println(folderList.get(largest_index).getName());
			}
		}
	}
}

class File {
	private String _name;
	private int _size;
	private String _folder;
	public File(){}
	public File(String name, int size, String folder){
		_name=name;
		_size=size;
		_folder=folder;
	}
	public String getName(){
		return _name;
	}
	public int getSize(){
		return _size;
	}
	public String getFolder(){
		return _folder;
	}
}

class Folder {
	private String _name;
	private Vector<File> _list_of_files;
	public Folder(){}
	public Folder(String name){
		_name=name;
		_list_of_files=new Vector<File>();
	}
	public boolean addFile(File file_in){
		return _list_of_files.add(file_in);
	}
	public int getSize(){
		int size=0;
		for (int i=0; i<this._list_of_files.size(); i++){
			size+=this._list_of_files.get(i).getSize();
		}	
		return size;
	}
	public String getName(){
		return _name;	
	}
	public boolean removeFile(File file_in){
		return _list_of_files.remove(file_in);
	}
	public File searchForFile(String query){
		for (int i=0;i<this._list_of_files.size();i++){
			if (this._list_of_files.get(i).getName().equals(query))
				return this._list_of_files.get(i);
		}
		return new File();
	}
	public boolean contains(String query){
		for (int i=0;i<this._list_of_files.size();i++){
			if (this._list_of_files.get(i).getName().equals(query))
				return true;
		}
		return false;
	}
}
