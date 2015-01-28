package com.balhau.kobo.utils.functionals;


@FunctionalInterface
public interface Unzip<T,U> {
	Pair<T,U> unzip(Pair<T,U> pairs);
}
