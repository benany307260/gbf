package com.bentest.gbf.util;

import java.util.UUID;

public class TokenUtil {
	public static String createToken() {
		return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
	}
}
