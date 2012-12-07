package immutableTree;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	/**with the hashmap, we can retreive the correct children
	 * according to the string selected.
	 */
	private HashMap<String, ImmutableTree> children;
	private List<String> listChoices;
	
	private ImmutableTree parent;
	/**contains the string selected from the parent's listChoices*/
	private String choice;
	private String label;
	
	
	public ImmutableTree(ImmutableTree mParent, String choice, List<String> pO, String pLabel) {
		setParent(mParent);
		setChoice(choice);
		setListChoices(pO);
		setLabel(pLabel);
	}
	
	
	/**lazy init**/
	public void addChild(ImmutableTree c) {
		if (children == null) {
			children = new HashMap<String, ImmutableTree>();
		}
		children.put(c.choice, c);
	}
	
	/**lazy init**/
	public void addDefaultChild(ImmutableTree c) {
		if (children == null) {
			children = new HashMap<String, ImmutableTree>();
		}
		children.put("DEFAULT", c);
	}

	public void removeChild(ImmutableTree c) {
		children.remove(c);
		if (children.isEmpty()) {
			children = null;
		}
	}
	
	public ImmutableTree getChild(String key) {
		if (children.containsKey(key)) {
			return children.get(key);
		} else {
			return children.get("DEFAULT");
		}
	}
	
	
	public ImmutableTree goUpBy(int count) {
		ImmutableTree current = this;
		while (count > 0) {
			if (current.getParent() != null) {
				current = current.getParent();
			} else {
				return current;
			}
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
	
	static public void writeToFile(Context a, String fileName, ImmutableTree t) {
		
		OutputStream outstream = null;
		try {
			outstream = a.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream ois;
			ois = new ObjectOutputStream(outstream);
			ois.writeObject(t);
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

	public String getLabel() {
		return label;
	}

	private void setLabel(String label) {
		this.label = label;
	}

	public List<String> getListChoices() {
		return new ArrayList<String>(listChoices);
	}

	public void setListChoices(List<String> listChoices) {
		this.listChoices = listChoices;
	}


	public String getChoice() {
		return choice;
	}


	public void setChoice(String choice) {
		this.choice = choice;
	}
	
}
