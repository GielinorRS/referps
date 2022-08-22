package org.necrotic.client;

public class SystemProps {

	public static final String separator = "/";

	public static String combinePath(String... parts) {
		return String.join(separator, parts);
	}
}