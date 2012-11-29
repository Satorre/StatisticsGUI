package immutableTree;

public class ComputeTree {

	static public ImmutableTree computeTree() {
		
		ImmutableTree root = new ImmutableTree(null, "prefix");

		ImmutableTree prefix_which = new ImmutableTree(root, "Which");
		ImmutableTree prefix_distribution = new ImmutableTree(root, "Distribution");
		ImmutableTree prefix_number = new ImmutableTree(root, "Number");
		root.addChild(prefix_which);
		root.addChild(prefix_distribution);
		root.addChild(prefix_number);

		/*--------------------------*/

		ImmutableTree number_course = new ImmutableTree(prefix_number, "Course");
		ImmutableTree number_student = new ImmutableTree(prefix_number, "Student");
		ImmutableTree number_self = new ImmutableTree(prefix_number, "Self");
		ImmutableTree number_professor = new ImmutableTree(prefix_number, "Professor");
		ImmutableTree number_section = new ImmutableTree(prefix_number, "Section");
		prefix_number.addChild(number_course);
		prefix_number.addChild(number_section);
		prefix_number.addChild(number_student);
		prefix_number.addChild(number_self);
		prefix_number.addChild(number_professor);



		/**add children of Course*/
		ImmutableTree c = number_course;
		c.addChild(new ImmutableTree(c, "Success (en fonction de l'année) as %"));
		c.addChild(new ImmutableTree(c, "Success (current semester) as %"));
		c.addChild(new ImmutableTree(c, "Rate"));
		c.addChild(new ImmutableTree(c, "Rate (current semester)"));
		c.addChild(new ImmutableTree(c, "grade obtained over rate given"));
		c.addChild(new ImmutableTree(c, "grade obtained in course A over grade obtained in course B"));

		/*----------------------------*/

		number_section.addChild(new ImmutableTree(number_section, "number of students (over the years)"));
		number_section.addChild(new ImmutableTree(number_section, "success rate"));


		/*-----------------------------------*/
		ImmutableTree which_course = new ImmutableTree(prefix_which, "Course");
		prefix_which.addChild(which_course);


		which_course.addChild(new ImmutableTree(which_course, "is hot ?"));
		which_course.addChild(new ImmutableTree(which_course, "has the best success rate ?"));


		return root;
	}

}
