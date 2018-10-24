package com.qiujintao.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class RefererRedirectionAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	public RefererRedirectionAuthenticationSuccessHandler(String defaultTargetUrl) {
		setDefaultTargetUrl(defaultTargetUrl);
	}
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		String redirectUrl = null;
//		if(session != null) {
//			redirectUrl = (String) session.getAttribute("url_prior_login");
//		}
//		if (redirectUrl != null) {
//			// we do not forget to clean this attribute from session
//			session.removeAttribute("url_prior_login");
//			// then we redirect
//			getRedirectStrategy().sendRedirect(request, response, redirectUrl);
//		} else {
//			super.onAuthenticationSuccess(request, response, authentication);
//		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
}