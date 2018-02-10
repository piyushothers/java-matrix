package components;

import java.util.Iterator;
import java.util.List;

public class ColumnImpl<T extends Number> implements Column<T> {
	private Row<T> row;
	public ColumnImpl(T[] seedArray){
		this.row = new RowImpl<>(seedArray);
	}
	public ColumnImpl(List<T> seedList){
		this.row = new RowImpl<>(seedList);
	}
	private ColumnImpl(Row<T> row){
		this.row = row;
	}

	@Override
	public Iterator<T> iterator() {
		return row.iterator();
	}

	@Override
	public int getSize() {
		return row.getSize();
	}

	@Override
	public T get(int index) {
		return row.get(index);
	}

	@Override
	public T set(int index, T element) {
		return row.set(index, element);
	}

	@Override
	public Column<T> getSubColumn(int startIndex) {
		return getSubColumn(startIndex, this.row.getSize());
	}

	@Override
	public Column<T> getSubColumn(int startIndex, int endIndex) {
		Row<T> subRow = this.row.getSubRow(startIndex,endIndex);
		return new ColumnImpl<>(subRow);
	}

}
