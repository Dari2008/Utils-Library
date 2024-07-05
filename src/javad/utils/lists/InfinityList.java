package javad.utils.lists;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class InfinityList{
	
	private SortedList<Column<?>> list = new SortedList<>();
	
	public InfinityList(Column<?>...cols) {
		for(Column<?> s : cols) {
			list.add(s);
		}
	}
	
	public InfinityList() {
	}
	
	public static void main(String[] args) {
		InfinityList li = new InfinityList();
		li.addColumn(new Column<String>("Test"));
		li.addColumn(new Column<String>("Test2"));
		li.put("Testtttt", "Darius");
		
		System.out.println(li.getRow(0));
		
	}
	
	public Row get(Object value, String colName) {
		for(Column<?> c : list) {
			if(c.getName().equals(colName)) {
				int i = c.getList().indexOf(value);
				if(i == -1)continue;
				return getRow(i);
			}
		}
		return null;
	}
	
	public void put(Object...obj) {
		for(int i = 0; i < list.size(); i++) {
			list.get(i).getList().add(obj[i]);
		}
	}
	
	public void addColumn(Column<?> col) {
		list.add(col);
	}
	
	public void addColumns(Column<?>... cols) {
		for(Column<?> c : cols) {
			list.add(c);
		}
	}
	
	public Column<?> getColumn(String name) {
		for(Column<?> c : list) {
			if(c.name.equals(name))return c;
		}
		return null;
	}
	
	public <T> T getValue(String colName, int index) {
		Column<?> c = getColumn(colName);
		return (T) c.getList().get(index);
	}
	
	public <T> Row getRow(int index) {
		Object[] objs = new Object[0];
		
		for(Column<?> c : list) {
			objs = Arrays.copyOf(objs, objs.length+1);
			objs[objs.length-1] = c.getList().get(index);
		}
		return new Row(objs);
	}
	
	public static class Row{
		
		private Object[] objects;
		
		public Row(Object...objects) {
			this.objects = objects;
		}
		
		public Object get(int i) {
			return objects[i];
		}
		
		@Override
		public String toString() {
			if(objects.length == 0)return "{}";
			String s = "{";
			for(Object obj : objects) {
				s += obj + ", ";
			}
			
			s = s.substring(0, s.length()-2);
			s += "}";
			return s;
		}
		
	}
	
	public static class Column<T>{
		
		private String name;
		private SortedList<T> list = new SortedList<>();
		
		public Column(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public SortedList<T> getList() {
			return list;
		}

		public void setList(SortedList<T> list) {
			this.list = list;
		}
	}
	
}