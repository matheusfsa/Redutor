package redutor;

public abstract class Redutor {
	protected String arquivo;
	abstract public int[] reduz(); 
	public Redutor(String arquivo) {
		this.arquivo =arquivo;
	}
}
