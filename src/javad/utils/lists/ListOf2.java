package javad.utils.lists;

import java.util.ArrayList;

import javad.utils.onlyList.OnlyListOf2;

public class ListOf2<A,B> {

	private ArrayList<A> a = new ArrayList<>();
	private ArrayList<B> b = new ArrayList<>();
	

	
	public A getA(int index) {
		return a.get(index);
	}
	
	public B getB(int index) {
		return b.get(index);
	}
	
	public void put(A a, B b) {
		this.b.add(b);
		this.a.add(a);
	}
	
	public void remove(int index) {
		a.remove(index);
		b.remove(index);
	}
	
	public int size() {
		return b.size();
	}
	
	public OnlyListOf2<A, B> get(A s){
		
		OnlyListOf2<A, B> tmp = new OnlyListOf2<>(a.get(a.indexOf(s)), b.get(a.indexOf(s)));
		return tmp;
	}
	
}
