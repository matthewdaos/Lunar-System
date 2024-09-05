/**
 * The LunarSystem creates a simulation of the class builder, both for the student and for the register.
 * This class uses a HashMap, and implements the Student and Course classes, which will save the progress
 * of any progress made, using Serializable.
 * 
 * @author Matthew Daos
 * Homework #6
 * SBU ID: 115532944
 * email: matthew.daos@stonybrook.edu
 * CSE 214 Recitation 02 (Pooja Ginjupalli and Nicholas Stamatakis)
 */
import java.util.*;
import java.io.*;

public class LunarSystem {
	public static HashMap<String, Student> database = new HashMap<>();
	
	/**
	 * This method contains switch cases that can perform different tasks with student registry.
	 * 
	 * @param args is for the main method in which all arguments are placed in
	 */
	public static void main(String[] args) {
		try {
            File databaseFile = new File("database2.ser");
            if (databaseFile.exists()) {
                FileInputStream fileIn = new FileInputStream(databaseFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                database = (HashMap<String, Student>) in.readObject();
                in.close();
                fileIn.close();
            } 
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		Scanner input = new Scanner(System.in);
		char choice;
		
		System.out.println("Welcome to the Lunar System, a second place course registration system for second rate courses at a second class school.");
		System.out.println("No previous data found.");
		do {
			System.out.println("Menu: "
					+ "\n\tL)Login "
					+ "\n\tX)Save state and quit"
					+ "\n\tQ)Quit without saving state.");
			System.out.println("Please select an option: ");
			choice = input.next().toUpperCase().charAt(0);
			switch(choice) {
			case 'L':
				char regChoice;
				char stuChoice;
				System.out.println("Please enter webid: ");
				String webid = input.next();
				if(webid.equalsIgnoreCase("REGISTRAR")) {
					System.out.println("Welcome Registrar."
							+ "\nMenu: "
							+ "\n\tR) Register a student"
							+ "\n\tD) De-register a student"
							+ "\n\tE) View course enrollment"
							+ "\n\tL) Logout");
					do {
						System.out.println("Please select an option: ");
						regChoice = input.next().toUpperCase().charAt(0);
						switch(regChoice) {
						case 'R':
							System.out.println("Please enter a webid for the new student: ");
							String stuID = input.next();
							if(database.containsKey(stuID)) {
								System.out.println(stuID + " is already registered.");
							} else {
								Student student = new Student();
								database.put(stuID, student);
								database.get(stuID).setWebID(stuID);
								System.out.println(stuID + " registered.");
							}
							break;
						case 'D':
							System.out.println("Please enter a webid for the student to be deregistered: ");
							String derID = input.next();
							if(database.containsKey(derID)) {
								Student student = database.get(derID);
								student.getCourses().clear();
								System.out.println(derID + " deregistered.");
							} else {
								System.out.println("Error: Could not find student in database");
							}
							break;
						case 'E':
							System.out.println("Please enter course department: ");
							String courseName = input.next();
							System.out.println("Please enter course department: ");
							int courseNumber = input.nextInt();
							System.out.println("Students Registered in " + courseName  + " " + courseNumber + ": ");
							System.out.println("Student   Semester  ");
							System.out.println("--------------------");
							Set<Map.Entry<String, Student>> studentSet = database.entrySet();
							for (Map.Entry<String, Student> entry : studentSet) {
								Student studentID = entry.getValue();
							    for (Course course : studentID.getCourses()) {
							        if (courseName.equalsIgnoreCase(course.getDepartment()) && courseNumber == course.getNumber()) {
							        	System.out.println(studentID.getWebID() + " " + course.getSemester());
							        }
							    }
							}
							break;
						case 'L':
							System.out.println("Registrar logged out.");
							break;
						}
					} while(regChoice != 'L');
				} else if(database.containsKey(webid)){
					System.out.println("Welcome " + webid + ". "
							+ "\nOptions: "
							+ "\n\tA) Add a class"
							+ "\n\tD) Drop a class"
							+ "\n\tC) View your classes sorted by course name/department"
							+ "\n\tS) View your courses sorted by semester");
					do {
						System.out.println("Please select an option: ");
						stuChoice = input.next().toUpperCase().charAt(0);
						switch(stuChoice) {
						case 'A':	
							System.out.println("Please enter course department: ");
						    String courseName = input.next();			   
						    System.out.println("Please enter course number: ");
						    int courseNum = input.nextInt();
						    System.out.println("Please select a semester: ");
						    String semester = input.next();
						    Course newCourse = new Course();
						    newCourse.setDepartment(courseName);
						    newCourse.setNumber(courseNum);
						    newCourse.setSemester(semester);
						    database.get(webid).getCourses().add(newCourse);
						    String term = semester.charAt(0) == 'F' ? "Fall" : "Spring";
						    System.out.println("Added course " + courseName  + " "  + courseNum + " for semester " + term + " " + semester.substring(1));
						    break;
						case 'D':
							System.out.println("Please enter course department: ");
							String deleteDepa = input.next();
							System.out.println("Please enter course number: ");
							int deleteNum = input.nextInt();
							Student removeClass = database.get(webid);
							List<Course> studentCourse = removeClass.getCourses();
							int classCount = -1;
							for(int i = 0; i < studentCourse.size(); i++) {
								Course course = studentCourse.get(i);
								if(course.getDepartment().equalsIgnoreCase(deleteDepa) && course.getNumber() == deleteNum) {
									classCount = i;
								}
							}
							if(classCount != -1) {
								System.out.println("removed 1 course(s) from student " + webid);
							} else {
								System.out.println("removed 0 course(s) from student " + webid);
							}
							break;
						case 'C':
							System.out.println("Dept. Course Semester\n-------------------------------\n");	
							Student courseStudent = database.get(webid);
							List<Course> coursesList = courseStudent.getCourses();
							Collections.sort(coursesList, new CourseNameComparator());
							for (Course course : coursesList) {
								System.out.println(course.getDepartment() + " " + course.getNumber() + "\t\t" + course.getSemester());
							}
							break;
						case 'S':
							System.out.println("Dept. Course Semester\n-------------------------------\n");
							Student semStudent = database.get(webid);
							List<Course> semesterList = semStudent.getCourses();
							Collections.sort(semesterList, new SemesterComparator());
							for (Course course : semesterList) {
								System.out.println(course.getDepartment() + " " + course.getNumber() + "\t\t" + course.getSemester());
							}
							break;
						case 'L':
							System.out.println(webid + " logged out.");
							break;
						}
					}while(stuChoice != 'L');
				} else {
					System.out.println("Could not find User");
				}
				break;
			case 'X':
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("database.ser"))) {
		            oos.writeObject(database);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				System.out.println("System state saved, system shut down for maintenance.");
				return;
			case 'Q':
				System.out.println("Good bye, please pick the right SUNY next time!");
				return;
			default:
				System.out.println("Invalid choice");
				break;
			}
			
		} while(choice != 'Q' || choice != 'X');
		input.close();

	}

}
