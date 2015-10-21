package org.terasoluna.securelogin.app.common.validation;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.google.common.base.Joiner;

public class StrongPasswordValidator implements
		ConstraintValidator<StrongPassword, Object> {

	@Resource(name = "characteristicPasswordValidator")
	PasswordValidator characteristicPasswordValidator;

	@Resource(name = "usernamePasswordValidator")
	PasswordValidator usernamePasswordValidator;

	private String usernamePropertyName;

	private String newPasswordPropertyName;

	@Override
	public void initialize(StrongPassword constraintAnnotation) {
		usernamePropertyName = constraintAnnotation.idPropertyName();
		newPasswordPropertyName = constraintAnnotation.newPasswordPropertyName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		String username = (String) beanWrapper.getPropertyValue(usernamePropertyName);
		String newPassword = (String) beanWrapper
				.getPropertyValue(newPasswordPropertyName);

		context.disableDefaultConstraintViolation();

		boolean result = checkCharacteristicsConstraints(newPassword, context);
		result = checkNotContainUsername(username, newPassword, context)
				&& result;

		return result;
	}

	private boolean checkCharacteristicsConstraints(String newPassword,
			ConstraintValidatorContext context) {
		RuleResult result = characteristicPasswordValidator
				.validate(new PasswordData(newPassword));
		if (result.isValid()) {
			return true;
		} else {
			context.buildConstraintViolationWithTemplate(
					Joiner.on("<br>")
							.join(characteristicPasswordValidator
									.getMessages(result)))
					.addPropertyNode(newPasswordPropertyName).addConstraintViolation();
			return false;
		}
	}

	private boolean checkNotContainUsername(String username,
			String newPassword, ConstraintValidatorContext context) {
		PasswordData passwordData = PasswordData.newInstance(newPassword,
				username, null);
		RuleResult result = usernamePasswordValidator.validate(passwordData);

		if (result.isValid()) {
			return true;
		} else {
			context.buildConstraintViolationWithTemplate(
					usernamePasswordValidator.getMessages(result).get(0))
					.addPropertyNode(newPasswordPropertyName).addConstraintViolation();
			return false;
		}
	}
}
