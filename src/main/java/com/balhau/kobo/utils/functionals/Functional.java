package com.balhau.kobo.utils.functionals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Static generic functional methods
 * @author balhau
 *
 */
public class Functional {
	
	
	public static <T> Collection<T> collection(T[] array){
		List<T> a=new ArrayList<T>();
		for(T el : array){
			a.add(el);
		}
		return a;
	}
	
	/**
	 * Executes a call on every element of a collection
	 * @param collection 
	 * @param callback
	 */
	public static <T> void callOn(List<T> collection,Callback<T> callback){
		for(T el : collection){
			callback.callOn(el);
		}
	}
	
	/**
	 * Zip the elements of two collections
	 * @param la
	 * @param lb
	 * @param zip
	 * @return
	 */
	public static <T,U> List<Pair<T,U>>  zip(List<T> la,List<U> lb,Zip<T, U> zip){
		List<Pair<T,U>> l=new ArrayList<>();
		int len=la.size()>lb.size() ? lb.size() : la.size();
		
		for(int i=0;i<len;i++){
			l.add(zip.zip(la.get(i), lb.get(i)));
		}
		
		return l;
	}
	
	/**
	 * Unzip the elements of two zipped collection
	 * @param zipped
	 * @param unzip
	 * @return
	 */
	public static <T,U> Pair<List<T>,List<U>> unzip(List<Pair<T,U>> zipped,Unzip<T, U> unzip){
		List<T> la=new ArrayList<T>();
		List<U> lb=new ArrayList<U>();
		Pair<List<T>,List<U>> pair=new Pair<List<T>, List<U>>(la, lb);
		Pair<T,U> aux;
		for(Pair<T,U> el : zipped){
			aux=unzip.unzip(el);
			la.add(aux.first());
			lb.add(aux.second());
		}
		return pair;
	}
	
	/**
	 * Map a collection 
	 * @param data
	 * @param trans
	 * @return
	 */
	public static <T,U> List<U> map(List<T> data,Transformation<T, U> trans){
		List<U> out= new ArrayList<U>();
		for(T el : data){
			out.add(trans.transformOn(el));
		}
		return out;
	}
}
