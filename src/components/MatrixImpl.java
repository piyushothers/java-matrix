package components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.NotInvertibleException;
import operator.Operator;

public class MatrixImpl<T extends Number> implements Matrix<T> {
	private Row<T> row ;
	private final int m;
	private final int n;
	private Operator<T> operator;
	public MatrixImpl(int m , int n , Class<T> clazz){
		this.m = m ;
		this.n = n;
		this.operator = (Operator<T>) Operator.getOperator(clazz);
		List<T> elements = new ArrayList<T>();
		row = new RowImpl<>(elements);
		
	}
	class RowView implements Row<T>{
		private final int start ; 
		private final int end;
		private final Row<T> innerImpl ;
		
		RowView(int start , int end){
			this.start = start ; 
			this.end = end ;
			innerImpl = MatrixImpl.this.row.getSubRow(start,end);
		}
		@Override
		public Iterator<T> iterator() {
			return innerImpl.iterator();
		}

		@Override
		public int getSize() {
			return innerImpl.getSize();
		}

		@Override
		public T get(int index) {
			return innerImpl.get(index);
		}

		@Override
		public T set(int index, T element) {
			return innerImpl.set(index, element);
		}

		@Override
		public Row<T> getSubRow(int startIndex) {
			return innerImpl.getSubRow(startIndex);
		}

		@Override
		public Row<T> getSubRow(int startIndex, int endIndex) {
			return innerImpl.getSubRow(startIndex, endIndex);
		}
		
	}
	@Override
	public List<Row<T>> getRows() {
		List<Row<T>> result = new ArrayList<>();
		for(int i=0;i<m*n;i=i+n){
			result.add(new RowView(i, i+n-1));
		}
		return result;
	}


	@Override
	public Matrix<T> invert() throws NotInvertibleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix<T> multiply(Matrix<T> operand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matrix<T> conjugate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T determinant() {
		return getDeterminant(this.getRows());
	}
	
	public  Row<T> merge(Row<T>... operand){
		List<Row<T>> operandList = Arrays.asList(operand);
		List<T> elements = new ArrayList<>();
		for(Row<T> row : operandList){
			for(T element : row){
				elements.add(element);
			}
		}
		return new RowImpl<>(elements);
	}
	
	private T getDeterminant(List<Row<T>> rows){
		if(rows.size() == 1){
			if(rows.get(0).getSize() !=1){
				throw new RuntimeException("Not a square matrix");
			}
			return rows.get(0).get(0);
		}
		Row<T> firstRow = rows.get(0); 
		List<Row<T>> otherRows = rows.subList(1,rows.size());
		T result = this.operator.additiveIdentity();
		T multiplier = this.operator.multiplicativeIdentity();
		for(int i=0;i<firstRow.getSize();i++){
			final int elementIndex = i;
			List<Row<T>> leftSideRows = otherRows.stream().map(row -> row.getSubRow(0, elementIndex)).collect(Collectors.toList());
			List<Row<T>> rightSideRows = otherRows.stream().map(row -> row.getSubRow(elementIndex+1, row.getSize())).collect(Collectors.toList());
			List<Row<T>> mergedRows = new ArrayList<>();
			for(int j=0;j<leftSideRows.size();j++){
				mergedRows.add(this.merge(leftSideRows.get(i),rightSideRows.get(i)));
			}
			T element = firstRow.get(i);
			T subDeterminant = getDeterminant(mergedRows);
			T product = operator.multiply(element, subDeterminant);
			product = operator.multiply(multiplier, product);
			result = operator.add(result,product);
			multiplier = operator.additiveInverse(multiplier);
		}
		
		return result;
	}

}
