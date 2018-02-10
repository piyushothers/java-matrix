package components;

public interface Row<T extends Number> extends Iterable<T>{
	int getSize();
	T get(int index);
	T set(int index , T element);
	Row<T> getSubRow(int startIndex);
	Row<T> getSubRow(int startIndex , int endIndex);
	
}
