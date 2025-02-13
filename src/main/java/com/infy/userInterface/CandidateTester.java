package com.infy.userInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Map.Entry;

import com.infy.exception.InfyAcademyException;
import com.infy.model.Candidate;
import com.infy.service.CandidateServiceImpl;

public class CandidateTester {

	public static void main(String[] args) throws InfyAcademyException, NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			boolean flag = false;
			System.out.println("Press 1 to add candidate");
			System.out.println("press 2 to get Candidates");
			System.out.println("Press 3 to exit");
			int choice = Integer.parseInt(br.readLine());
			
			switch(choice) {
				case 1 : {
					addCandidates(br);
					break;
				}
				case 2 : {
					getCandidateReport();
					break;
				}
				default : {
					flag = true;
					break;
				}
			} if(flag) {
				System.out.println("Thanks for using our application...");
				break;
			}
		}
	
	}

	private static void addCandidates(BufferedReader br) {
		try {
			CandidateServiceImpl candidateService = new CandidateServiceImpl();
			System.out.println("Enter candidate Id");
			int id = Integer.parseInt(br.readLine());
			
			System.out.println("Enter your name");
			String name = br.readLine();
			
			System.out.println("Enter marks1");
			int marks1 = Integer.parseInt(br.readLine());
			
			System.out.println("Enter marks2");
			int marks2 = Integer.parseInt(br.readLine());
			
			System.out.println("Enter marks3");
			int marks3 = Integer.parseInt(br.readLine());
			
			System.out.println("Enter the result");
			char result = (br.readLine()).charAt(0);
			
			System.out.println("Enter the department");
			String department = br.readLine();
			
			System.out.println("Enter the exam Date in the format YYYY-MM-DD:");
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String input = br.readLine();
			LocalDate examDate = LocalDate.parse(input,dateTimeFormatter);
			
			Candidate candidate = new Candidate(id,name,marks1,marks2,marks3,result,department,examDate);
			String message = candidateService.addCandidate(candidate);
			System.out.println(message);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void getCandidateReport() {
		try {
			CandidateServiceImpl candidateService = new CandidateServiceImpl();
			Map<Integer, String> result = candidateService.getGradesForAllCandidates();
			System.out.println("CandidateID\t\t\tResult");
			System.out.println("***********\t\t\t******");
			for(Entry<Integer, String> i : result.entrySet()) {
				System.out.println(" "+i.getKey()+"\t\t\t\t "+i.getValue());
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
