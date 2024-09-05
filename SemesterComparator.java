/**
 * The SemesterComparator class compares the semester of the courses
 * This class uses Comparator which compares two different objects from the course class
 * 
 * @author Matthew Daos
 * Homework #6
 * SBU ID: 115532944
 * email: matthew.daos@stonybrook.edu
 * CSE 214 Recitation 02 (Pooja Ginjupalli and Nicholas Stamatakis)
 */
import java.util.Comparator;

public class SemesterComparator implements Comparator<Course>{
	/**
	 * This method compares the two objects and sees if they are similar or not.
	 * Return 0 if they are the same, 1 if the left is larger than the right, and -1 vice versa
	 */
	public int compare(Course left, Course right) {
		String sem1 = left.getSemester();
		String sem2 = right.getSemester();
		
		int year1 = Integer.parseInt(sem1.substring(1));
		int year2 = Integer.parseInt(sem2.substring(1));
		int yearCompare = Integer.compare(year1, year2);
		if(yearCompare != 0) {
			return yearCompare;
		}
		
		char term1 = sem1.charAt(0);
		char term2 = sem2.charAt(0);
		if(term1 == term2) {
			return 0;
		} else if (term1 == 'S') {
			return -1;
		} else {
			return 1;
		}
	}
}
