package immutableTree;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;


/**
 * Tree
 * L'arbre decrit le choix de l'utilisateur dans les spinners
 * de l'UI dans l'app Android.
 * L'App Android chargera donc cet arbre en memoire et doit
 * prendre le moins de place possible, d'ou les methodes add 
 * et remove.
 * L'app n'aura pas besoin de modifier cette structure
 * @author david
 *
 */
public class ImmutableTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3998158696450451886L;
	
	private List<ImmutableTree> children;
	private ImmutableTree parent;
	private Object o;
	private int depth;
	
	public ImmutableTree(ImmutableTree mParent, Object pO) {
		super();
		setParent(mParent);
		setO(pO);
		depth = 0;
	}

	/**lazy init**/
	public void addChild(ImmutableTree c) {
		if (children == null) {
			children = new ArrayList<ImmutableTree>();
		}
		c.setDepth(this.getDepth() + 1);
		children.add(c);
	}

	public void removeChild(ImmutableTree c) {
		children.remove(c);
		if (children.isEmpty()) {
			children = null;
		}
	}
	
	public ImmutableTree getChild(int i) {
		return children.get(i);
	}
	
	public List<String> getChildrenString() {
		List<String> strings = new ArrayList<String>();
		for (ImmutableTree e : children) {
			strings.add(e.getO().toString());
		}
		
		return strings;
	}
	
	public ImmutableTree goUpBy(int count) {
		ImmutableTree current = this;
		while (count > 0) {
			current = current.getParent();
			count--;
		}
		return current;
	}
	
	public Boolean hasChildren() {
		return children != null;
	}
	
	public Boolean isRoot() {
		return parent == null;
	}
	
	public ImmutableTree getParent() {
		return parent;
	}

	private void setParent(ImmutableTree mParent) {
		this.parent = mParent;
	}

	public Object getO() {
		return o;
	}

	private void setO(Object o) {
		this.o = o;
	}
	
	public void print(int indent) {
		String tab = "";
		for (int i = 0; i < indent; i++) {
			tab += "   ";
		}
		
		System.out.println(tab + o.toString());
		if (hasChildren()) {
			for (ImmutableTree t : children) {
				t.print(indent + 1);
			}
		}
	}
	
	static public void writeToFile(Context a, String fileName, ImmutableTree t) {
		
		Object g = ComputeTree.computeTree();
		OutputStream outstream = null;
		try {
			outstream = a.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream ois;
			ois = new ObjectOutputStream(outstream);
			ois.writeObject(g);
		} catch (IOException e) {
			//never mind
			//the app will attempt to write the tree the next time
		}
	
	}
	
	static public ImmutableTree readFromFile(Context a, String fileName) {
		
		InputStream instream = null;
		ImmutableTree g = null;
		try {
			instream = a.openFileInput(fileName);
			ObjectInputStream ois;
			ois = new ObjectInputStream(instream);
			g = (ImmutableTree) ois.readObject();
		} catch (Exception e) {
			return null;
		}
		
		return g;
	}
	
	public int getDepth() {
		return depth;
	}
	
	private void setDepth(int a) {
		this.depth = a;
	}
	
	

}
