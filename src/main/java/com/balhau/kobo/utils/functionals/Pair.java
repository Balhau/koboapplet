package com.balhau.kobo.utils.functionals;

/**
 * Imutable class with pairs of elements
 * @author balhau
 *
 * @param <U>
 * @param <T>
 */
public class Pair<T,U>{

	T a;
	U b;
	
	public Pair(T a,U b){
		this.a=a;this.b=b;
	}
	
	public T first(){
		return a;
	}
	
	public U second(){
		return b;
	}
	
	public String toString(){
		return "("+a+","+b+")";
	}
}
