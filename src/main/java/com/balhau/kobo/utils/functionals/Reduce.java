package com.balhau.kobo.utils.functionals;

import java.util.List;

@FunctionalInterface
public interface Reduce<T>{
	T reduce(List<T> elements);
}
