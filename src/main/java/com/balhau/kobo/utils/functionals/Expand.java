package com.balhau.kobo.utils.functionals;

import java.util.List;

@FunctionalInterface
public interface Expand<T> {
	List<T> expand(T comp);
}
