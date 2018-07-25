package registrationsystem;


public class Course {
	private String courseId;
	private String courseName;
	private int credit;
	private int quota;
	private Student[] students = null; // = new int[40];

	public String getCourseId() {
		return  courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;	}

	public String getCourseName() {
		return  courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;	}

	public int getCredit() {
		return  credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;	}

	public int getQuota() {
		return  quota;		
	}

	public void setQuota(int quota) {
		if(quota>0)
		{
			this.quota = quota;	
			students = new Student[quota];
		}
	}

	public boolean addStudent(Student student){
		for(int i=0; i < quota ; i++){
			if(students[i] == null ) { 
				students[i]=student; 
				return true;
			}
		}
		return false;
	}

	public void deleteStudent(int id) {
		for(int i=0; i < quota ; i++){
			if(students[i] != null && students[i].getId()==id) { 
				students[i] = null; 
				return ;
			}
		}
	}

	public void removeStudents(int i) {
		for(int j=0 ; j<quota ;j++){
			if(students[i] != null){
				students[i].removeCourse(this.courseId);
			}
		}
	}
	
	public void listStudentCourse(int id){
		for(int j=0;j<students.length;j++){
			if(students[j] != null && students[j].getId()== id ){
				System.out.println(students[j].getId()+" "+students[j].getName()+" "+students[j].getSurname());
			}
		}
	}
}
