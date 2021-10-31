package com.example.workflow.servicedelegates;

import com.example.workflow.model.LoanModificationInfo;
import com.example.workflow.model.LoanStatus;
import com.example.workflow.services.IndividualProcessUtilityService;
import com.example.workflow.services.PersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RenewLoan implements JavaDelegate {

	@Autowired
	PersistenceService<LoanModificationInfo> persistenceService;

	@Autowired
	IndividualProcessUtilityService individualProcessUtilityService;

	@Override
	public void execute(DelegateExecution delegateExecution) {
		log.info("Inside >>> {}",
				delegateExecution.getCurrentActivityName());
		String loanNumber = individualProcessUtilityService.loanNumber(delegateExecution);
		String qualifiedFilePath = individualProcessUtilityService.getQualifiedLoanFilePath(loanNumber, LoanModificationInfo.class);
		LoanModificationInfo loanModificationInfo = persistenceService.get(
				qualifiedFilePath,
				individualProcessUtilityService.processInfoSha(delegateExecution),
				LoanModificationInfo.class);
		loanModificationInfo.setStatus(LoanStatus.RENEWED);
		individualProcessUtilityService.setBusinessKey(delegateExecution,
				loanNumber,
				persistenceService.save(
						individualProcessUtilityService.getBranchName(loanNumber),
						qualifiedFilePath,
						loanModificationInfo,
						individualProcessUtilityService.commitMessage(delegateExecution, false)).getSha());
	}

}