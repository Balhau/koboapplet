package com.balhau.kobo.utils.functionals;

import java.util.List;

@FunctionalInterface
public interface Unzip<T,U> {
	Pair<T,U> unzip(Pair<T,U> pairs);
}
