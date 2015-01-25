package com.balhau.kobo.utils.functionals;

@FunctionalInterface
public interface Transformation<T,U> {
	U transformOn(T element);
}
