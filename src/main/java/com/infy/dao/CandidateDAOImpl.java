package com.infy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.infy.model.Candidate;
import com.infy.model.CandidateReport;

public class CandidateDAOImpl implements CandidateDAO {
	
	static Connection conc;
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/InfyAcademy";
			String userName = "root";
			String password = "Rishabh@8448";
			conc = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conc;
	}

	@Override
	public String add(Candidate candidate) {
		conc = createConnection();
		String q = "insert into infyAcademy_information(candidateId,candidateName,mark1,mark2,mark3,result,grade) values(?,?,?,?,?,?,null);";
		try {
			PreparedStatement prep = conc.prepareStatement(q);
			prep.setInt(1, candidate.getCandidateId());
			prep.setString(2, candidate.getCandidateName());
			prep.setInt(3, candidate.getMark1());
			prep.setInt(4, candidate.getMark2());
			prep.setInt(5, candidate.getMark3());
			prep.setString(6, String.valueOf(candidate.getResult()));
			prep.executeUpdate();
			prep.close();
			conc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} return "Candidate details are successfully added.";
	}

	@Override
	public List<CandidateReport> getGradesForAllCandidates() {
		conc = createConnection();
		String q = "SELECT * FROM infyAcademy_information";
		Statement st;
		List<CandidateReport> list = new ArrayList<> ();
		try {
			st = conc.createStatement();
			ResultSet rs = st.executeQuery(q);
			while(rs.next()) {
				CandidateReport cr = new CandidateReport(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6).charAt(0), rs.getString(7));
				list.add(cr);
			}
			rs.close();
			conc.close();
		} catch (Exception e) {
			e.printStackTrace();
		} return list;		
	}

}
