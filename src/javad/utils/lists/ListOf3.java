package javad.utils.lists;

import java.util.ArrayList;

import javad.utils.onlyList.OnlyListOf3;

public class ListOf3<A,B,C> {

	private ArrayList<A> a = new ArrayList<>();
	private ArrayList<B> b = new ArrayList<>();
	private ArrayList<C> c = new ArrayList<>();
	

	
	public A getA(int index) {
		return a.get(index);
	}
	
	public C getC(int index) {
		return c.get(index);
	}
	
	public B getB(int index) {
		return b.get(index);
	}
	
	public void put(A a, B b, C c) {
		this.b.add(b);
		this.a.add(a);
		this.c.add(c);
	}
	
	public void remove(int index) {
		a.remove(index);
		b.remove(index);
		c.remove(index);
	}

	public OnlyListOf3<A, B, C> get(int index){
		return new OnlyListOf3<A, B, C>(a.get(index), b.get(index), c.get(index));
	}
	
	public boolean containsA(A aa) {
		return a.contains(aa);
	}

	public boolean containsB(B aa) {
		return b.contains(aa);
	}

	public boolean containsC(C aa) {
		return c.contains(aa);
	}
	
	public int size() {
		return b.size();
	}
	
	public OnlyListOf3<A, B, C> get(C s){
		OnlyListOf3<A, B, C> o = new OnlyListOf3<>(a.get(c.indexOf(s)), b.get(c.indexOf(s)), c.get(c.indexOf(s)));
		return o;
	}
	
	public ArrayList<A> getListA(){
		return a;
	}

	public ArrayList<B> getListB(){
		return b;
	}

	public ArrayList<C> getListC(){
		return c;
	}

	
	public int indexAOf(A aa) {
		return a.indexOf(aa);
	}
	
	public int indexBOf(B aa) {
		return b.indexOf(aa);
	}
	
	public int indexCOf(C aa) {
		return c.indexOf(aa);
	}
	
}
