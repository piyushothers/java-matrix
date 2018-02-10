package components;

import java.util.List;

import exceptions.NotInvertibleException;

public interface Matrix<T extends Number> {
	List<? extends Row<T>> getRows();
	Matrix<T> invert() throws NotInvertibleException;
	Matrix<T> multiply(Matrix<T> operand);
	Matrix<T> conjugate();
	T determinant();
	static<U extends Number> Matrix<U> createMatrixFromRows(List<Row<U>> rows){
		return null;
	}
}
