package registrationsystem;


public class Student {
	private String name ;
	private String surname ;
	private int id ;

	private Course[]  coursesTaken= new Course[8];

	public int getId() {
		return this.id;
	}
	public String getName() {	
		return name;
	}

	public String getSurname() {		
		return surname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean addCourse(Course course){
		int totalCredit=0;
		for (int i = 0; i < coursesTaken.length; i++) {
			if(coursesTaken[i] == course)
			return false;
		}
		for(int i=0; i<8 ; i++){
			if(coursesTaken[i] == null ) { 
				if(totalCredit + course.getCredit() <= 22)
				{
					coursesTaken[i] = course; 
					return true;
				}
				else
					return false;
			}
			totalCredit += coursesTaken[i].getCredit();
		}
		return false;
	}
	
	public void removeCourses() {
		//Öðrencinin aldýðý tüm dersleri gezip, ilgili derse kendisini sildirir
		for(int j=0; j<8; j++){
			if(coursesTaken[j] != null){
				coursesTaken[j].deleteStudent(this.id); 
			}
		}
	}
	public void removeCourse(String courseId) {
		for(int j=0; j<8; j++){
			if(coursesTaken[j] != null && coursesTaken[j].getCourseId().equalsIgnoreCase(courseId))
				coursesTaken[j]=null; 
		}
	}
	
	public void listCourseStudent(String courseId){
		for(int i=0;i<8;i++){
			if(coursesTaken[i] != null && coursesTaken[i].getCourseId().equalsIgnoreCase(courseId) ){
				System.out.println(coursesTaken[i].getCourseId()+" "+coursesTaken[i].getCourseName()+" "+coursesTaken[i].getCredit()+" "+coursesTaken[i].getQuota());
			}
		}
	}
}