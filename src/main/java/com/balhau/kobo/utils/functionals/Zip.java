package com.balhau.kobo.utils.functionals;


@FunctionalInterface
public interface Zip<T,U>{
	Pair<T,U> zip(T a,U b);
}
