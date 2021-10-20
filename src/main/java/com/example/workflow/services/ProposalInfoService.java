package com.example.workflow.services;

import com.example.workflow.model.ProposalRequestDto;
import com.example.workflow.model.ProposalResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProposalInfoService {

	public ProposalResponseDto getProposal(ProposalRequestDto proposalRequestDto) {
		log.info("Inside >> {}",
				this.getClass().getName());
		var result = new ProposalResponseDto();
		result.setLoan(proposalRequestDto.getLoanNum());
		result.setCustomerId(proposalRequestDto.getCustId());
		result.setProposedTerm(20);
		result.setProposedInterestRate(2.1);
		return result;
	}
}
