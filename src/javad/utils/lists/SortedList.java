package javad.utils.lists;

import java.util.Arrays;

import javax.swing.text.html.HTMLDocument.Iterator;

@SuppressWarnings("unchecked")
public class SortedList<T> implements Iterable<T>{

	private Object[] array = new Object[0];
	
	public SortedList() {
	}
	
	public void add(Object obj) {
		array = Arrays.copyOf(array, array.length+1);
		array[array.length-1] = obj;
	}
	
	public T get(int index) {
		return (T) array[index];
	}
	
	public void add(T t, int i) {
		array = Arrays.copyOf(array, array.length);
		Object tmp = t;
		Object tmp2;
		for(int in = i; in < array.length; in++){
			tmp2 = array[in];
			array[in] = tmp;
			tmp = tmp2;
		}
	}

	@Override
	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T>() {

			int pos = 0;
			
			@Override
			public boolean hasNext() {
				return array.length > pos;
			}

			@Override
			public T next() {
				Object tmp = array[pos];
				pos++;
				return (T)tmp;
			}
		};
	}

	public int size() {
		return array.length;
	}

	public int indexOf(Object value) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == value)return i;
		}
		return -1;
	}
	
}
