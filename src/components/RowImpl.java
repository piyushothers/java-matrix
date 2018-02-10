package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RowImpl<T extends Number> implements Row<T> {
	private List<T> elements ;
	public RowImpl(T[] seedArray){
		this.elements = Arrays.asList(seedArray);
	}
	public RowImpl(List<T> seedList){
		this.elements = new ArrayList<>(seedList);
	}
	@Override
	public Iterator<T> iterator() {
		return elements.iterator();
	}

	@Override
	public int getSize() {
		return elements.size();
	}

	@Override
	public T get(int index) {
		return elements.get(index);
	}

	@Override
	public T set(int index, T element) {
		return elements.set(index, element);
	}

	@Override
	public Row<T> getSubRow(int startIndex) {
		return getSubRow(startIndex, this.elements.size());
	}

	@Override
	public Row<T> getSubRow(int startIndex, int endIndex) {
		List<T> subList = this.elements.subList(startIndex,endIndex);
		return new RowImpl<>(subList);
	}

}
