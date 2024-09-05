/**
 * The Student class initializes their web id and the list of courses they are taking.
 * This class implements Serializable, which will save the data of the student.
 * 
 * @author Matthew Daos
 * Homework #6
 * SBU ID: 115532944
 * email: matthew.daos@stonybrook.edu
 * CSE 214 Recitation 02 (Pooja Ginjupalli and Nicholas Stamatakis)
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable{
	private String webID;
	private List<Course> courses;
	
	/**
	 * This is the default constructor that sets up a student 
	 */
	public Student() {
		this.webID = " ";
		courses = new ArrayList<>();
	}
	
	/**
	 * This getter method returns the webID of the student
	 * 
	 * @return webID
	 */
	public String getWebID() {
		return webID;
	}
	
	/**
	 * This getter method returns the list of courses of a student
	 * 
	 * @return courses
	 */
	public List<Course> getCourses(){
		return courses;
	}
	
	/**
	 * This setter method sets the webID of a student
	 * 
	 * @param webID is the value that is being changed
	 */
	public void setWebID(String webID) {
		this.webID = webID;
	}
	
	/**
	 * This setter method sets the courses that the student is taking
	 * 
	 * @param courses is the value that is being changed/added
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
