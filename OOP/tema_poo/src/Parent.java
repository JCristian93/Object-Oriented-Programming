import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Parent<T> {
	public T data;
	public List<Parent<T>> children;
	public Parent() {
		super();
		children = new ArrayList<Parent<T>>();
	}
	public Parent(T data) {
		this();
		setData(data);
	}
	public List<Parent<T>> getChildren() {
		return this.children;
	}
	public int getNumberOfChildren() {
		return getChildren().size();
	}
	public boolean hasChildren() {
		return (getNumberOfChildren() > 0);
	}
	public void setChildren(List<Parent<T>> children) {
		this.children = children;
	}
	public void addChild(Parent<T> child) {
		children.add(child);
	}
	public void addChildAt(int index, Parent<T> child) throws IndexOutOfBoundsException {
		children.add(index, child);
	}
	public void removeChildren() {
		this.children = new ArrayList<Parent<T>>();
	}
	public void removeChildAt(int index) throws IndexOutOfBoundsException {
		children.remove(index);
	}
	public Parent<T> getChildAt(int index) throws IndexOutOfBoundsException {
		return children.get(index);
	}
	public T getData() {
		return this.data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String toString() {
		return getData().toString();
	}
	public boolean equals(Parent<T> node) {
		return node.getData().equals(getData());
	}
	public int hashCode() {
		return getData().hashCode();
	}
	public String toStringVerbose() {
		String stringRepresentation = getData().toString() + ":[";
		for (Parent<T> node : getChildren()) {
			stringRepresentation += node.getData().toString() + ", ";
		}
		//Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
		Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(stringRepresentation);
		stringRepresentation = matcher.replaceFirst("");
		stringRepresentation += "]";
		return stringRepresentation;
	}
	public static void main(String[] args){
		Parent <String> obiect = new  Parent<String>();

		
	}
}