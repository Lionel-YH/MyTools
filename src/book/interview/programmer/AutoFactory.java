package book.interview.programmer;

/**
 *
 * @author lionel
 *
 * @source 170题
 *
 */
abstract class People{
	abstract String getName();
}
class Chinese extends People{
	private String name="Chinese";
	public String getName(){
		return this.name;
	}
}
class American extends People{
	private String name="American";
	public String getName(){
		return this.name;
	}
}

class PeopleFactory{
	public static People getPeople(Integer type){
		if(type==1){
			return new American();
		}
		if(type==2)
			return new Chinese();
	return null;
	}
}
public  class AutoFactory {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			People person1 = PeopleFactory.getPeople(1);
			People person2 = PeopleFactory.getPeople(2);
			System.out.println(person1.getName());
			System.out.println(person2.getName());
	}
}
	
