package com.samplebpm.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanModificationStageInfo {

	String dateTime;
	LoanModificationInfo loanModificationInfo;
}
