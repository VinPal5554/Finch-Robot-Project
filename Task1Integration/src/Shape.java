import java.util.ArrayList;

public class Shape { //store all properties of squares and triangles into array list

	private String shapeName;
	private ArrayList<Integer > lengths; // stores length/s of shapes
	
	public String getShapeName() { 
		return shapeName;
	}
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}
	public ArrayList<Integer> getLengths() {
		return lengths;
	}
	public void setLengths(ArrayList<Integer> lengths) {
		this.lengths = lengths;
	}
	
}
