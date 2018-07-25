package registrationsystem;

import java.util.Scanner;

public class Main {
	static Student[] student  = new Student[400];
	static Course[] course = new Course[40] ;
	static Scanner scanner = null;
	
	public static void main(String[] args) {
		int mainOperation = 0 ;
		int addingOperations = 0 ;
		int deletingOperations = 0 ;
		int listingOperations = 0 ;
		scanner = new Scanner(System.in);
		for(int i=0 ; i<400 ; i++)
			student[i]=new Student();
		for(int i=0 ; i<40 ; i++)
			course[i]=new Course();

		do{ 
			System.out.println("1. Adding Operations ");
			System.out.println("2. Deleting Operations ");
			System.out.println("3. Listing Operations ");
			System.out.println("4. Exit");
			
			mainOperation = scanner.nextInt();
			switch (mainOperation) {
			case 1:
				do{
					System.out.println("");
					System.out.println("1. Add A Student");
					System.out.println("2. Add A Student To A Specified Course");
					System.out.println("3. Add A Course");
					System.out.println("4. Return To Main Menu");
					addingOperations = scanner.nextInt();
					switch (addingOperations){
					case 1:
						addingStudent();
						break;
					case 2:
						addStudentCourse();
						break; 
					case 3:
						addingCourse();   
						break;
					}
				}while (addingOperations != 4);
				break; 
			case 2:
				do{
					System.out.println("");
					System.out.println("1. Delete A Student By Student Id");
					System.out.println("2. Delete A Student By Student Id From A Specified Course");
					System.out.println("3. Delete A Course By Course Id");
					System.out.println("4. Return To Main Menu");
					deletingOperations = scanner.nextInt();
					switch(deletingOperations){
					case 1 :
						deletingStudent();	
						break;
					case 2 :
						deleteStudentCourse();
						break;
					case 3 :
						deletingCourse();	
						break;

					}
				}while(deletingOperations != 4);
				break ;
			case 3:
				do{
					System.out.println("");
					System.out.println("1. List All Students Who Registered A Specified Course");
					System.out.println("2. List All Courses That A Student Registered");
					System.out.println("3. List All Students");
					System.out.println("4. List All Courses");
					System.out.println("5. Return To Main Menu");
					listingOperations = scanner.nextInt();
					switch(listingOperations){
					case 1:
						listingStudentOfCourse();
						break;
					case 2:
						listingCourseOfStudent();
						break;
					case 3:
					    listingStudent();
						break;
					case 4:
						 listingCourse();
						break;

					}
				}while(listingOperations != 5);
				break;

			}
		}while(mainOperation != 4);
		scanner.close();
	}

	public static void addingStudent(){
		System.out.print("Enter Student Id: ");
		 
		int ID = scanner.nextInt();
		System.out.print("Enter Student Name : ");
		String Name = scanner.next() ;
		System.out.print("Enter Student Surname : ");
		String Surname = scanner.next();

		for(int i = 0 ; i<400 ; i++ ){
			if(student[i].getId() == ID){
				System.out.println("This Student Already Saved!");
				return;
			}
			else if(student[i].getId() == 0){
				student[i].setId( ID );
				student[i].setName( Name);
				student[i].setSurname( Surname);
				System.out.println("Done!");
				return;
			}
		}

	}

	public static int findStudent(int  sID){
		for(int i=0 ; i<400 ; i++){
			if(student[i].getId()== sID){
				return i;
			}
		}
		return -1;
	}

	public static int findCourse(String cID){
		for(int i=0; i<40; i++){
			if(course[i].getCourseId()!= null)  
				if(course[i].getCourseId().equalsIgnoreCase(cID)) return i;
		}
		return -1;
	}

	public static void listingStudent(){
		for(int i=0 ; i<400 ; i++){
			if(student[i].getId() != 0){
				System.out.println(student[i].getId()  +" "+ student[i].getName() +" "+ student[i].getSurname());
			}
		}
	}
	
	public static void addingCourse(){

		System.out.print("Enter Course Id :");
		 
		String ID = scanner.next();
		System.out.print("Enter Name :");		
		String Name = scanner.next();
		System.out.print("Enter Credit :");		
		int Credit = scanner.nextInt();
		System.out.print("Enter Quota :");		
		int Quota = scanner.nextInt();
		for(int i=0 ; i<40 ; i++){
			if(ID.equals(course[i].getCourseId())){
				System.out.println("This Course Already Saved!");
				break;
			} 
			else if(course[i].getCourseId() == null){
				course[i].setCourseId(ID);
				course[i].setCourseName(Name);
				course[i].setCredit(Credit);
				course[i].setQuota(Quota);
				System.out.println("Done!");
				break ;	
			}			
		}
	}

	public static void listingCourse(){
		for(int i=0 ; i<40 ; i++){
			if(course[i].getCourseId() != null){
				System.out.println(course[i].getCourseId() +" "+ course[i].getCourseName() +" "+ course[i].getCredit() +" "+ course[i].getQuota() );
			}
		}
	}

	public static void deletingStudent(){
		System.out.print("Enter Student Id :");
		int ID = scanner.nextInt();

		int i=findStudent(ID);

		if(i >= 0){
			student[i].removeCourses();
			student[i].setId(0);
			student[i].setName(null);
			student[i].setSurname(null);
			System.out.println("Done!");
		}
		else System.out.println("This Student Couldn't Find!");
	}

	public static void deletingCourse(){
		System.out.print("Enter Course Id :");
		String ID = scanner.next();
		
		int i=findCourse(ID);
			if(i>=0){
				course[i].removeStudents(i);
				course[i].setCourseId (null) ;
				course[i].setCourseName(null) ;
				course[i].setCredit(0) ;
				course[i].setQuota(0) ;
				System.out.println("Done!");
				return;
			}
		}
	

	public static void addStudentCourse(){
		System.out.print("Enter Student Id:");
		int sID=scanner.nextInt();
		System.out.print("Enter Course Id:");
		String cID=scanner.next();
		int i=findStudent(sID);
		int j=findCourse(cID);

		if(i<0){
			System.out.println("This Student Couldn't Find!");
		}
		else if(j<0){
			System.out.println("This Course Couldn't Find!");
		}
		else 
		{
			if(student[i].addCourse(course[j])){
			    if(course[j].addStudent(student[i])){
					System.out.println("Done!");
				}
				else
				{
					System.out.println("Quota Is Full!");
					student[i].removeCourse(course[j].getCourseId());
				}
			}
			else
				System.out.println("Credit Is Full!");
		}
 
	}
	public static void deleteStudentCourse(){
		System.out.print("Enter Student Id:");
		int sID=scanner.nextInt();
		System.out.print("Enter Course Id:");
		String cID=scanner.next();
 		int i=findStudent(sID);
		int j=findCourse(cID);

		if(i<0){
			System.out.println("This Student Couldn't Find!");
		}
		else if(j<0){
			System.out.println("This Course Couldn't Find!");
		}
		else {
			student[i].removeCourse(course[j].getCourseId());
			course[j].deleteStudent(student[i].getId());
			System.out.println("Done!");
		}
	}
	public static void listingStudentOfCourse(){
//düzgün çalýþmýyor.
		System.out.print("Enter Course Id:");
		String ID=scanner.next();
		int i=findCourse(ID);
		if(i>=0){
			System.out.println(course[i].getCourseName()+" "+course[i].getCredit()+" "+course[i].getQuota());
			for(int j=0;j<400;j++){
				if(student[j]!=null){
					course[i].listStudentCourse(student[j].getId());
				}
 			}
		}
		else System.out.println("This Course Couldn't Find!");
	}
	
	public static void listingCourseOfStudent(){
		System.out.print("Enter Student Id:");
		int ID=scanner.nextInt();
		int i=findStudent(ID);
		if(i>=0){
			System.out.println(student[i].getName()+" "+student[i].getSurname());
 			for(int j=0;j<40;j++){
 				if(course[j].getCourseId()!=(null)){
 					student[i].listCourseStudent(course[j].getCourseId());
				}
 			}
		}
		else System.out.println("This Student Couldn' Find!");
	}


}
