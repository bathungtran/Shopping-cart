package com.hung.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {
	
	public static boolean isLogged() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return null != authentication && !("anonymousUser").equals(authentication.getName());
	}
}
