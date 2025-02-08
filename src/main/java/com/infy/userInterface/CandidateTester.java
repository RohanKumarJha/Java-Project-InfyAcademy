package com.infy.userInterface;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

import com.infy.exception.InfyAcademyException;
import com.infy.model.Candidate;
import com.infy.service.CandidateServiceImpl;

public class CandidateTester {

	public static void main(String[] args) throws InfyAcademyException {
		addCandidates();
		getCandidateReport();
	}

	private static void addCandidates() {
		try {
			CandidateServiceImpl candidateService = new CandidateServiceImpl();
			LocalDate examDate = LocalDate.of(2014, 5, 29);
			Candidate candidate = new Candidate(12346,"Sam",51,66,78,'P',"ECE",examDate);
			String result = candidateService.addCandidate(candidate);
			System.out.println(result);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getCandidateReport() {
		try {
			CandidateServiceImpl candidateService = new CandidateServiceImpl();
			System.out.println("CandidateID\t\t\tResult");
			System.out.println("***********\t\t\t******");
			Map<Integer, String> result = candidateService.getGradesForAllCandidates();
			for(Entry<Integer, String> i : result.entrySet()) {
				System.out.println(" "+i.getKey()+"\t\t\t\t "+i.getValue());
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
