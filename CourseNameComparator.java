/**
 * The CourseNameComparator class compares the department name and course number 
 * This class uses Comparator which compares two different objects from the course class
 * 
 * @author Matthew Daos
 * Homework #6
 * SBU ID: 115532944
 * email: matthew.daos@stonybrook.edu
 * CSE 214 Recitation 02 (Pooja Ginjupalli and Nicholas Stamatakis)
 */
import java.util.Comparator;

public class CourseNameComparator implements Comparator<Course>{
	/**
	 * This method compares the two objects and sees if they are similar or not.
	 * Return 0 if they are the same, 1 if the left is larger than the right, and -1 vice versa
	 */
	public int compare(Course left, Course right) {
		Course c1 = (Course) left;
		Course c2 = (Course) right;
		int nameCompare = c1.getDepartment().compareTo(c2.getDepartment());
		if(nameCompare != 0) {
			return nameCompare;
		}
		
		if(c1.getNumber() == c2.getNumber()) {
			return 0;
		} else if (c1.getNumber() > c2.getNumber()) {
			return 1;
		} else {
			return -1;
		}
		
	}
}
