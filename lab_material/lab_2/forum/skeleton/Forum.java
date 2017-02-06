//Zhang yuan(alex)
//a0157059x

import java.util.*;

public class Forum {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int numOfUsers=sc.nextInt();
		User[] users=new User[numOfUsers];
		sc.nextLine();
		for(int i=0; i<numOfUsers;i++)
			users[i]=new User(sc.nextLine());
		int numOfThreads=sc.nextInt();
		Thread[] threads=new Thread[numOfThreads];
		sc.nextLine();
		String[] usernames=new String[numOfUsers];
		for(int i=0;i<numOfUsers;i++){
			usernames[i]=users[i].get_name();
		}
		for(int i=0;i<numOfThreads;i++){
			threads[i]=new Thread(sc.nextLine(),numOfUsers);
			threads[i].give_user_name(usernames);
		}
		int numOfQuereis=sc.nextInt();
		for(int q=0; q<numOfQuereis; q++){
			//sc.nextLine();
			String operation=sc.next();

			if(operation.equals("post")){
				String thread_name=sc.next();
				String username=sc.next();
				Post curr=new Post(sc.nextLine().trim());//create temp new msg
				boolean posted=false;
				for(int i=0;i<numOfThreads;i++){
					if (threads[i].is_thread(thread_name)){//search for the thread
						if(!threads[i].post(curr, username)){	//posting to the thread, verify username
							System.out.println("no such user");
							posted=true;//no need to run :42
						}
						else{
							for(int j=0;j<numOfUsers;j++){//posted. user post count++
								if(users[j].is_user(username)){
									users[j].post();
									System.out.println(curr.get_message());
									posted=true;
									break;
								}
							}
						}
					}
				}
				if(!posted)
					System.out.println("no such thread");
			}
			else if(operation.equals("count")){
				int count_threads=sc.nextInt();
				int total_count=0;
				for(int i=0;i<count_threads;i++){
					String thread_name=sc.next();
					int thread_index=locateThread(thread_name,threads);
					if(thread_index!=-1)
						total_count+=threads[thread_index].count_post();
				}
				System.out.println(total_count);	
			}
			else if(operation.equals("numpost")){
				String username=sc.next();
				boolean query_done=false;
				for(int i=0;i<numOfUsers;i++){
					if(users[i].is_user(username)){
						System.out.println(users[i].get_post_count());
						query_done=true;
					}
				}
				if(!query_done)
					System.out.println("no such user");
			}
			else if(operation.equals("maxpost")){
				String thread_name=sc.next();
				int thread_index=locateThread(thread_name,threads);
				if(thread_index==-1){
				        System.out.println("no such thread");
				}
				else{
						System.out.println(threads[thread_index].get_user_names()[threads[thread_index].most_active()]);
				}
			}
			else if(operation.equals("content")){
				String thread_name=sc.next();
				int post_index=sc.nextInt();
				int thread_index=locateThread(thread_name,threads);
				if(thread_index==-1){
					System.out.println("no such thread");
				}
				else{
					Thread curr=threads[locateThread(thread_name,threads)];
					if(curr.count_post()<post_index)
						System.out.println("no such post");
					else{
						System.out.println(threads[thread_index].get_post(post_index-1));
					}
				}

			}
		}
	}
	public static int locateThread(String thread_name, Thread[] threads){//returns the index of a thread given name
		for (int i=0;i<threads.length;i++){
			if(threads[i].is_thread(thread_name)){
				return i;
			}
		}
		return -1;

	}
}
class Thread {
	private String _name;
	private ArrayList<Post> _posts;
	private int[] _users_post_count;
	private String[] _users;
	private int _post_count;

	public Thread(String name, int user_number){
		_name=name;
		_posts=new ArrayList<Post>(100);
		_users_post_count=new int[user_number];
	}

	public void give_user_name(String[] usernames){
		_users=usernames;
	}
	public boolean post(Post post, String username){
		int user_index=-1;
		for (int i=0;i<_users.length;i++){
			if(_users[i].equals(username)){
				user_index=i;
				break;
			}
		}
		if(user_index==-1)
			return false;
		else{
			_posts.add(post);
			_users_post_count[user_index]++;
			return true;
		}
	}
	public String get_post(int post_index){
		return _posts.get(post_index).get_message();
	}
	public int count_post(){
		int count=0;
		for(int i=0;i<_users.length;i++){
			count+=_users_post_count[i];
		}
		return count;
	} 
	public int most_active(){
		int most_active=0;
		for (int i=0;i<_users.length;i++){
			if(_users_post_count[i]>_users_post_count[most_active])
				most_active=i;
			else if(_users_post_count[i]==_users_post_count[most_active]){
				if(_users[i].compareTo(_users[most_active])<0)
					most_active=i;
			}			
		}
		return most_active;
	}
	public String[] get_user_names(){
		return _users;
	}
	public boolean is_thread(String query){
		return this._name.equals(query);
	}
}

class Post {
	private String _message;
	private int _user_index;//who posts the message

	public Post(String message){
		_message=message;
	}

	public int get_user_index(){
		return _user_index;
	}

	public String get_message(){
		return _message;
	}
}

class User {
	private String _name;
	private int _post_count;
	//	private ArrayList<Post> _posts;

	public User(String name){
		_name=name;
	}

	public boolean post(){
		//		_posts.add(post);
		_post_count++;
		return true;
	}

	public int get_post_count(){
		return _post_count;
	}
	public boolean is_user(String query){
		return this._name.equals(query);
	}
	public String get_name(){
		return _name;
	}
}
