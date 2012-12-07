package immutableTree;

import java.util.ArrayList;
import java.util.List;

public class ComputeTree {

	static public ImmutableTree computeTree() {
		
		List<String> cat = new ArrayList<String>();
		cat.add("1Course");
		cat.add("2Courses");
		cat.add("User");
		cat.add("Section");
		cat.add("Teacher");
		
		ImmutableTree root = new ImmutableTree(null, null, cat, "Categorie ?");
		
		List<String> listOfCourse = new ArrayList<String>();
		listOfCourse.add("list of courses");
		List<String> listOfSection = new ArrayList<String>();
		listOfSection.add("list of sections");
		List<String> listOfTeacher = new ArrayList<String>();
		listOfTeacher.add("list of teacher");
		List<String> listOfMode = new ArrayList<String>();
		listOfMode.add("Alone");
		listOfMode.add("Average");
		
/**depth 1*/
		ImmutableTree cat_1course = new ImmutableTree(root, "1Course", listOfCourse, "Course ?");
		root.addChild(cat_1course);
		
		ImmutableTree cat_2courses_1 = new ImmutableTree(root, "2Courses", listOfCourse, "Course ?");
		root.addChild(cat_2courses_1);
		
		ImmutableTree cat_section = new ImmutableTree(root, "Section", listOfSection, "Section ?");
		root.addChild(cat_section);
		
		ImmutableTree cat_teacher = new ImmutableTree(root, "Teacher", listOfTeacher, "Teacher ?");
		root.addChild(cat_teacher);
		
		ImmutableTree cat_user = new ImmutableTree(root, "User", listOfMode, "Mode ?");
		root.addChild(cat_user);
		
		
/**depth 2*/
		ImmutableTree cat_2courses_2 = new ImmutableTree(cat_2courses_1, "DEFAULT", listOfCourse, "Course ?");
		cat_2courses_1.addChild(cat_2courses_2);
		List<String> listOf2CoursesStat = new ArrayList<String>();
		listOf2CoursesStat.add("grade Coursx/grade Coursy");
		listOf2CoursesStat.add("rating Coursx/grade Coursy");
		ImmutableTree courses2_stat = new ImmutableTree(cat_2courses_2, "DEFAULT", listOf2CoursesStat, "Stat ?");
		cat_2courses_2.addChild(courses2_stat);
		
		
		List<String> listOfCommonStat = new ArrayList<String>();
		listOfCommonStat.add("Distribution of Sections");
		listOfCommonStat.add("SuccessRate");
		listOfCommonStat.add("Mean grades (received)");
		listOfCommonStat.add("Mean rates (given)");
		listOfCommonStat.add("Global rating view");
		ImmutableTree course1_stat = new ImmutableTree(cat_1course, "DEFAULT", listOfCommonStat, "Stat ?");
		cat_1course.addChild(course1_stat);
		
		List<String> listOfUserStat = new ArrayList<String>();
		listOfUserStat.add("Mean of Gardes obtained");
		ImmutableTree user_stat = new ImmutableTree(cat_user, "DEFAULT", listOfUserStat, "Stat ?");
		cat_user.addChild(user_stat);
		
		List<String> listOfTeacherStat = new ArrayList<String>();
		listOfTeacherStat.add("Success Rate");
		listOfTeacherStat.add("Mean Rates");
		listOfTeacherStat.add("Mean Grades");
		listOfTeacherStat.add("Grade/Rating");
		ImmutableTree teacher_stat = new ImmutableTree(cat_teacher, "DEFAULT", listOfTeacherStat, "Stat ?");
		cat_teacher.addChild(teacher_stat);
		
		List<String> listOfSectionStat = new ArrayList<String>();
		listOfSectionStat.add("Success Rate");
		ImmutableTree section_stat = new ImmutableTree(cat_section, "DEFAULT", listOfSectionStat, "Stat ?");
		cat_section.addChild(section_stat);
		
		return root;
	}

}
