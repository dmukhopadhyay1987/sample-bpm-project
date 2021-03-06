package com.samplebpm.workflow.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OfferGenerationErrorHandler implements JavaDelegate {
	@Override
	public void execute(DelegateExecution delegateExecution) {
		log.info("Inside >>> {}",
				delegateExecution.getCurrentActivityName());
	}
}
