package com.parkinglot.utils;

import java.util.Iterator;

import org.apache.commons.lang3.ObjectUtils;

public class StringUtils {
	public static String join(Iterator<?> iterator, String separator) {
		if (iterator == null) {
			return null;
		}
		if (!(iterator.hasNext())) {
			return "";
		}
		Object first = iterator.next();
		if (!(iterator.hasNext())) {
			return ObjectUtils.toString(first);
		}

		StringBuilder buf = new StringBuilder(256);
		if (first != null) {
			buf.append(first);
		}

		while (iterator.hasNext()) {
			if (separator != null) {
				buf.append(separator);
			}
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
		}
		return buf.toString();
	}
}
