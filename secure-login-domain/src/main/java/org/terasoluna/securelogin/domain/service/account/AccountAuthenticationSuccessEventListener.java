package org.terasoluna.securelogin.domain.service.account;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.terasoluna.securelogin.domain.model.SuccessfulAuthentication;
import org.terasoluna.securelogin.domain.service.authenticationevent.AuthenticationEventSharedService;
import org.terasoluna.securelogin.domain.service.userdetails.LoggedInUser;

@Component
public class AccountAuthenticationSuccessEventListener implements
		ApplicationListener<AuthenticationSuccessEvent> {

	@Inject
	AuthenticationEventSharedService authenticationEventSharedService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		LoggedInUser details = (LoggedInUser) event.getAuthentication()
				.getPrincipal();

		SuccessfulAuthentication successEvent = new SuccessfulAuthentication();
		successEvent.setUsername(details.getUsername());
		successEvent.setAuthenticationTimestamp(LocalDateTime.now());

		authenticationEventSharedService.insertSuccessEvent(successEvent);
	}

}
