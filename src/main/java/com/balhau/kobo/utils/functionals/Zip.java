package com.balhau.kobo.utils.functionals;

import java.util.List;

@FunctionalInterface
public interface Zip<T,U>{
	Pair<T,U> zip(T a,U b);
}
