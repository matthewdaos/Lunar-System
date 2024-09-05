/**
 * The Course class initializes the course department, number, and semester in which it is being taken in.
 * This class implements Serializable, which will save the data of the courses
 * 
 * @author Matthew Daos
 * Homework #6
 * SBU ID: 115532944
 * email: matthew.daos@stonybrook.edu
 * CSE 214 Recitation 02 (Pooja Ginjupalli and Nicholas Stamatakis)
 */
import java.io.Serializable;

public class Course implements Serializable{
	private String department;
	private int number;
	private String semester;
	
	/**
	 * This is the default constructor that initializes the course
	 */
	public Course() {
		this.department = "";
		this.number = 0;
		this.semester = "";
	}
	
	/**
	 * This getter method returns the department of the course
	 * 
	 * @return department
	 */
	public String getDepartment() {
		return department;
	}
	
	/**
	 * This getter method returns the number of the course from the specific department
	 * 
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * This getter method returns the semester in which the course is being taken in
	 * 
	 * @return semester
	 */
	public String getSemester() {
		return semester;
	}
	
	/**
	 * This setter method sets the department of the course 
	 * 
	 * @param department is the value that is being changed
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * This setter method sets the number of the department course
	 * 
	 * @param number is the value that is being changed
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * This setter method sets the semester in which the course is being taken in
	 * 
	 * @param semester is the value that is being changed
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
}
