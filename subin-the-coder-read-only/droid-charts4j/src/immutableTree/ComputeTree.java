package immutableTree;

public class ComputeTree {

	static public ImmutableTree computeTree() {
		
		ImmutableTree root = new ImmutableTree(null, "root", "Categorie");
/**depth 0*/
		ImmutableTree cat_1course = new ImmutableTree(root, "1Course", "Which Course ?");
		ImmutableTree cat_2courses = new ImmutableTree(root, "2Courses", "Which Course ?");
		ImmutableTree cat_user = new ImmutableTree(root, "User", "Alone or other");
		ImmutableTree cat_section = new ImmutableTree(root, "Section", "Which Section ?");
		ImmutableTree cat_teacher = new ImmutableTree(root, "Teacher", "Which Teacher ?");
		root.addChild(cat_1course);
		root.addChild(cat_2courses);
		root.addChild(cat_user);
		root.addChild(cat_section);
		root.addChild(cat_teacher);
		
/**depth 1*/	
		ImmutableTree values1 = new ImmutableTree(cat_1course, /**TODO fetch list courses*/"list of courses", "Choose stat");
		ImmutableTree values20 = new ImmutableTree(cat_2courses, /**TODO fetch list courses*/"list of courses", "Choose stat");
		ImmutableTree values21 = new ImmutableTree(cat_2courses, /**TODO fetch list courses*/"list of courses", "Choose stat");
		ImmutableTree values30 = new ImmutableTree(cat_user, "Alone", "Choose stat");
		ImmutableTree values31 = new ImmutableTree(cat_user, "Compare with others", "Choose stat");
		ImmutableTree values4 = new ImmutableTree(cat_teacher, /**TODO fetch list teachers*/"list of sections", "Choose stat");
		ImmutableTree values5 = new ImmutableTree(cat_section, /**TODO fetch list sections*/"list of teachers", "Choose stat");
		cat_1course.addChild(values1);
		/**2 spinners to select 2 courses*/
		cat_2courses.addChild(values20);
		values20.addChild(values21);
		
		cat_user.addChild(values30);
		cat_user.addChild(values31);
		cat_teacher.addChild(values4);
		cat_section.addChild(values5);
		
/**depth 2*/
		ImmutableTree c = values1;
		c.addChild(new ImmutableTree(c, "Distribution of Sections"));
		c.addChild(new ImmutableTree(c, "SuccessRate"));
		c.addChild(new ImmutableTree(c, "Mean grades (received)"));
		c.addChild(new ImmutableTree(c, "Mean rates (given)"));
		c.addChild(new ImmutableTree(c, "Global rating view"));
		
		c = values21;
		c.addChild(new ImmutableTree(c, "grade course X/grade course Y"));
		c.addChild(new ImmutableTree(c, "rating course X/rating course Y"));
		
		c = values30;
		c.addChild(new ImmutableTree(c, "Mean of grades obtained"));
		c = values31;
		c.addChild(new ImmutableTree(c, "Mean of grades obtained"));
		
		c = values4;
		c.addChild(new ImmutableTree(c, "Success rate"));
		
		c = values5;
		c.addChild(new ImmutableTree(c, "Distributions of Sections"));
		c.addChild(new ImmutableTree(c, "Grade/Rating"));
		c.addChild(new ImmutableTree(c, "SuccessRate"));
		c.addChild(new ImmutableTree(c, "Mean Grades (given)"));
		c.addChild(new ImmutableTree(c, "Mean Rates (received)"));
		
		
		return root;
	}

}
