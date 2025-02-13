package com.infy.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.infy.dao.CandidateDAOImpl;
import com.infy.exception.InfyAcademyException;
import com.infy.model.Candidate;
import com.infy.model.CandidateReport;
import com.infy.validator.Validator;

public class CandidateServiceImpl implements CandidateService {
	
	private CandidateDAOImpl candidateDaoImpl = new CandidateDAOImpl();
	Validator validator = new Validator();

	@Override
	public String addCandidate(Candidate candidate) throws InfyAcademyException {
		validator.validate(candidate);
		if((candidate.getMark1()<50 | candidate.getMark2()<50 | candidate.getMark3()<50) & candidate.getResult().equals('P')) {
			return "Result should be 'F' (Fail) if student scores less than 50 in any one subject";
		} return candidateDaoImpl.add(candidate); 
	}

	@Override
	public String calculateGrade(CandidateReport candidateReportTO) {
		if(candidateReportTO.getResult().equals("F")) return "NA";
		int avg = (candidateReportTO.getMark1()+candidateReportTO.getMark2()+candidateReportTO.getMark3())/3;
		if(avg >= 85) return "A";
		else if(avg >= 75) return "B";
		else return "C";
	}

	@Override
	public Map<Integer, String> getGradesForAllCandidates()  throws InfyAcademyException {
		List<CandidateReport> list = candidateDaoImpl.getGradesForAllCandidates();
		Map<Integer, String> map = new TreeMap<>();
		for(CandidateReport i : list) {
			map.put(i.getCandidateId(), this.calculateGrade(i));
		} return map;
	}

}
