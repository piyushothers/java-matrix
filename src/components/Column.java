package components;

public interface Column<T extends Number> extends Iterable<T> {
	int getSize();
	T get(int index);
	T set(int index , T element);
	Column<T> getSubColumn(int startIndex);
	Column<T> getSubColumn(int startIndex,int endIndex);
}
