package com.infy.validator;

import java.time.LocalDate;

import com.infy.exception.InfyAcademyException;
import com.infy.model.Candidate;

public class Validator {
	public void validate(Candidate candidate) throws InfyAcademyException {
		if(!isValidCandidateId(candidate.getCandidateId())) throw new InfyAcademyException("The entered candidate ID is invalid.");
		if(!isValidCandidateName(candidate.getCandidateName())) throw new InfyAcademyException("The entered candidate name is invalid.");
		if(isValidExamMarks(candidate)) throw new InfyAcademyException("The entered exam marks are invalid.");
		if(!isValidResult(candidate.getResult())) throw new InfyAcademyException("The entered result is invalid.");
		if(!isValidDepartment(candidate.getDepartment())) throw new InfyAcademyException("The entered Department name is invalid.");
		if(!isValidExamDate(candidate.getExamDate())) throw new InfyAcademyException("The entered Exam Date is invalid.");
	}
	
	public boolean isValidCandidateName(String candidateName) {
		return candidateName.matches("^[A-Za-z]+$");
	}
	
	public boolean isValidCandidateId(Integer candidateId) {
		return candidateId.toString().matches("^[1-9]{1}[0-9]{4}$");
	}
	
	public boolean isValidDepartment(String department) {
		return department.matches("ECE")|department.matches("CSE")|department.matches("IT")|department.matches("EEE");
	}
	
	public boolean isValidExamDate(LocalDate examDate) {
		return LocalDate.now().isAfter(examDate);
	}
	
	public boolean isValidExamMarks(Candidate candidateTO) {
		return candidateTO.getMark1()<0 | candidateTO.getMark2()<0 | candidateTO.getMark3()<0;
	}
	
	public boolean isValidResult(Character result) {
		return result.equals('P')|result.equals('F');
	}
}
