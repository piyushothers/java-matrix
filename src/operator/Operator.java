package operator;

public interface Operator<T extends Number> {
	T additiveIdentity();
	T multiplicativeIdentity();
	T multiply(T op1 , T op2);
	T subtract(T op1 , T op2);
	T additiveInverse(T op1);
	T add(T op1 , T op2);
	static  Operator<? extends Number> getOperator(Class<? extends Number> clazz){
		if(clazz == Integer.class){
			return new IntegerOperator();
		}
		return null;
	}
	
}
class IntegerOperator implements Operator<Integer>{

	@Override
	public Integer additiveIdentity() {
		return 0;
	}

	@Override
	public Integer multiply(Integer op1, Integer op2) {
		return op1*op2;
	}

	@Override
	public Integer multiplicativeIdentity() {
		return 1;
	}

	@Override
	public Integer subtract(Integer op1, Integer op2) {
		return op1-op2;
	}

	@Override
	public Integer additiveInverse(Integer op1) {
		return -1 * op1;
	}

	@Override
	public Integer add(Integer op1, Integer op2) {
		return op1 + op2 ;
	}
	
}

