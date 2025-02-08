package com.infy.dao;

import java.util.List;

import com.infy.model.Candidate;
import com.infy.model.CandidateReport;

public interface CandidateDAO {
	String add(Candidate candidate);
	List<CandidateReport> getGradesForAllCandidates();
}
