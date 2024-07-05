package javad.utils.onlyList;

public class OnlyListOf10<A, B, C, D, E, F, G, H, I, J> {


	private A a = null;
	private B b = null;
	private C c = null;
	private D d = null;
	private E e = null;
	private F f = null;
	private G g = null;
	private H h = null;
	private I i = null;
	private J j = null;
	
	public OnlyListOf10(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {

		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
		this.j = j;
		
	}
	

	public A getA() {
		return a;
	}

	public B getB() {
		return b;
	}

	public C getC() {
		return c;
	}

	public D getD() {
		return d;
	}

	public E getE() {
		return e;
	}

	public F getF() {
		return f;
	}

	public G getG() {
		return g;
	}

	public H getH() {
		return h;
	}

	public I getI() {
		return i;
	}

	public J getJ() {
		return j;
	}
	
	@Override
	public String toString() {
		
		String tmp = a + ";" + b + ";" + c + ";" + d + ";" + e + ";" + f + ";" + g + ";" + h + ";" + i + ";" + j;
		
		return tmp;
	}
	
}
