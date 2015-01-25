package com.balhau.kobo.utils.functionals;

@FunctionalInterface
public interface Callback<T> {
	void callOn(T element);
}
