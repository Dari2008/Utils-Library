package javad.utils.lists;

import java.util.ArrayList;

import javad.utils.onlyList.OnlyListOf10;

public class ListOf10<A, B, C, D, E, F, G, H, I, J> {

	private ArrayList<A> a = new ArrayList<>();
	private ArrayList<B> b = new ArrayList<>();
	private ArrayList<C> c = new ArrayList<>();
	private ArrayList<D> d = new ArrayList<>();
	private ArrayList<E> e = new ArrayList<>();
	private ArrayList<F> f = new ArrayList<>();
	private ArrayList<G> g = new ArrayList<>();
	private ArrayList<H> h = new ArrayList<>();
	private ArrayList<I> i = new ArrayList<>();
	private ArrayList<J> j = new ArrayList<>();
	
	public void put(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {

		this.a.add(a);
		this.b.add(b);
		this.c.add(c);
		this.d.add(d);
		this.e.add(e);
		this.f.add(f);
		this.g.add(g);
		this.h.add(h);
		this.i.add(i);
		this.j.add(j);
		
	}
	
	public boolean remove1(A a) {

		for(int i = 0; i < this.a.size(); i++) {
			
			A s = this.a.get(i);
			
			if(s.equals(a)) {
				this.a.remove(i);
				this.b.remove(i);
				this.c.remove(i);
				this.d.remove(i);
				this.e.remove(i);
				this.f.remove(i);
				this.g.remove(i);
				this.h.remove(i);
				this.i.remove(i);
				this.j.remove(i);
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean remove2(B b) {

		for(int i = 0; i < this.a.size(); i++) {
			
			B s = this.b.get(i);
			
			if(s.equals(b)) {
				this.a.remove(i);
				this.b.remove(i);
				this.c.remove(i);
				this.d.remove(i);
				this.e.remove(i);
				this.f.remove(i);
				this.g.remove(i);
				this.h.remove(i);
				this.i.remove(i);
				this.j.remove(i);
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean remove3(C c) {

		for(int i = 0; i < this.c.size(); i++) {
			
			C s = this.c.get(i);
			
			if(s.equals(c)) {
				this.a.remove(i);
				this.b.remove(i);
				this.c.remove(i);
				this.d.remove(i);
				this.e.remove(i);
				this.f.remove(i);
				this.g.remove(i);
				this.h.remove(i);
				this.i.remove(i);
				this.j.remove(i);
				return true;
			}
			
		}
		return false;
		
	}
	
	
	public boolean remove4(D d) {

		for(int i = 0; i < this.a.size(); i++) {
			
			D s = this.d.get(i);
			
			if(s.equals(d)) {
				this.a.remove(i);
				this.b.remove(i);
				this.c.remove(i);
				this.d.remove(i);
				this.e.remove(i);
				this.f.remove(i);
				this.g.remove(i);
				this.h.remove(i);
				this.i.remove(i);
				this.j.remove(i);
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean remove5(E e) {

		for(int i = 0; i < this.a.size(); i++) {
			
			E s = this.e.get(i);
			
			if(s.equals(e)) {
				this.a.remove(i);
				this.b.remove(i);
				this.c.remove(i);
				this.d.remove(i);
				this.e.remove(i);
				this.f.remove(i);
				this.g.remove(i);
				this.h.remove(i);
				this.i.remove(i);
				this.j.remove(i);
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean remove6(F f) {

		for(int i = 0; i < this.a.size(); i++) {
			
			F s = this.f.get(i);
			
			if(s.equals(f)) {
				this.a.remove(i);
				this.b.remove(i);
				this.c.remove(i);
				this.d.remove(i);
				this.e.remove(i);
				this.f.remove(i);
				this.g.remove(i);
				this.h.remove(i);
				this.i.remove(i);
				this.j.remove(i);
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean remove7(G g) {

		for(int i = 0; i < this.a.size(); i++) {
			
			G s = this.g.get(i);
			
			if(s.equals(g)) {
				this.a.remove(i);
				this.b.remove(i);
				this.c.remove(i);
				this.d.remove(i);
				this.e.remove(i);
				this.f.remove(i);
				this.g.remove(i);
				this.h.remove(i);
				this.i.remove(i);
				this.j.remove(i);
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean remove8(H h) {

		for(int i = 0; i < this.a.size(); i++) {
			
			H s = this.h.get(i);
			
			if(s.equals(h)) {
				this.a.remove(i);
				this.b.remove(i);
				this.c.remove(i);
				this.d.remove(i);
				this.e.remove(i);
				this.f.remove(i);
				this.g.remove(i);
				this.h.remove(i);
				this.i.remove(i);
				this.j.remove(i);
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean remove9(I i) {

		for(int i1 = 0; i1 < this.a.size(); i1++) {
			
			I s = this.i.get(i1);
			
			if(s.equals(i)) {
				this.a.remove(i1);
				this.b.remove(i1);
				this.c.remove(i1);
				this.d.remove(i1);
				this.e.remove(i1);
				this.f.remove(i1);
				this.g.remove(i1);
				this.h.remove(i1);
				this.i.remove(i1);
				this.j.remove(i1);
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean remove10(J j) {

		for(int i1 = 0; i1 < this.a.size(); i1++) {
			
			J s = this.j.get(i1);
			
			if(s.equals(j)) {
				this.a.remove(i1);
				this.b.remove(i1);
				this.c.remove(i1);
				this.d.remove(i1);
				this.e.remove(i1);
				this.f.remove(i1);
				this.g.remove(i1);
				this.h.remove(i1);
				this.i.remove(i1);
				this.j.remove(i1);
				return true;
			}
			
		}
		return false;
		
	}
	
	public A getA(int index) {
		return a.get(index);
	}
	public B getB(int index) {
		return b.get(index);
	}
	public C getC(int index) {
		return c.get(index);
	}
	public D getD(int index) {
		return d.get(index);
	}
	public E getE(int index) {
		return e.get(index);
	}
	public F getF(int index) {
		return f.get(index);
	}
	public G getG(int index) {
		return g.get(index);
	}
	public H getH(int index) {
		return h.get(index);
	}
	public I getI(int index) {
		return i.get(index);
	}
	public J getJ(int index) {
		return j.get(index);
	}
	
	
	public int getIndexOfA(A a) {
		return this.a.indexOf(a);
	}
	public int getIndexOfB(B b) {
		return this.b.indexOf(b);
	}
	public int getIndexOfC(C c) {
		return this.c.indexOf(c);
	}
	public int getIndexOfD(D d) {
		return this.d.indexOf(d);
	}
	public int getIndexOfE(E e) {
		return this.e.indexOf(e);
	}
	public int getIndexOfF(F f) {
		return this.f.indexOf(f);
	}
	public int getIndexOfG(G g) {
		return this.g.indexOf(g);
	}
	public int getIndexOfH(H h) {
		return this.h.indexOf(h);
	}
	public int getIndexOfI(I i) {
		return this.i.indexOf(i);
	}
	public int getIndexOfJ(J j) {
		return this.j.indexOf(j);
	}
	
	public OnlyListOf10<A, B, C, D, E, F, G, H, I, J> getAll(int index){
		OnlyListOf10<A, B, C, D, E, F, G, H, I, J> is = new OnlyListOf10<A, B, C, D, E, F, G, H, I, J>(a.get(index), b.get(index), c.get(index), d.get(index), e.get(index), f.get(index), g.get(index), h.get(index), i.get(index), j.get(index));
		
		return is;
	}
	
	public OnlyListOf10<A, B, C, D, E, F, G, H, I, J> getByA(A a) {

		for(int i1 = 0; i1 < this.a.size(); i1++) {
			
			A s = this.a.get(i1);
			
			if(s.equals(a)) {
				OnlyListOf10<A, B, C, D, E, F, G, H, I, J> is = new OnlyListOf10<A, B, C, D, E, F, G, H, I, J>(this.a.get(i1), this.b.get(i1), c.get(i1), d.get(i1), e.get(i1), f.get(i1), g.get(i1), h.get(i1), i.get(i1), j.get(i1));
				
				return is;
			}
			
		}
		return null;
		
	}
	
	public OnlyListOf10<A, B, C, D, E, F, G, H, I, J> getByB(B b) {

		for(int i1 = 0; i1 < this.a.size(); i1++) {
			
			B s = this.b.get(i1);
			
			if(s.equals(b)) {
				OnlyListOf10<A, B, C, D, E, F, G, H, I, J> is = new OnlyListOf10<A, B, C, D, E, F, G, H, I, J>(this.a.get(i1), this.b.get(i1), c.get(i1), d.get(i1), e.get(i1), f.get(i1), g.get(i1), h.get(i1), i.get(i1), j.get(i1));
				
				return is;
			}
			
		}
		return null;
		
	}
	
	public OnlyListOf10<A, B, C, D, E, F, G, H, I, J> getByC(C c) {

		for(int i1 = 0; i1 < this.a.size(); i1++) {
			
			C s = this.c.get(i1);
			
			if(s.equals(c)) {
				OnlyListOf10<A, B, C, D, E, F, G, H, I, J> is = new OnlyListOf10<A, B, C, D, E, F, G, H, I, J>(this.a.get(i1), this.b.get(i1), this.c.get(i1), this.d.get(i1), this.e.get(i1), this.f.get(i1), this.g.get(i1), this.h.get(i1), this.i.get(i1), this.j.get(i1));
				
				return is;
			}
			
		}
		return null;
		
	}
	
	public OnlyListOf10<A, B, C, D, E, F, G, H, I, J> getByD(D d) {

		for(int i1 = 0; i1 < this.a.size(); i1++) {
			
			D s = this.d.get(i1);
			
			if(s.equals(d)) {
				OnlyListOf10<A, B, C, D, E, F, G, H, I, J> is = new OnlyListOf10<A, B, C, D, E, F, G, H, I, J>(this.a.get(i1), this.b.get(i1), this.c.get(i1), this.d.get(i1), this.e.get(i1), this.f.get(i1), this.g.get(i1), this.h.get(i1), this.i.get(i1), this.j.get(i1));
				
				return is;
			}
			
		}
		return null;
		
	}
	
	
}
