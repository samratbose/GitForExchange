package com.infy.infyinterns.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;



public class ProjectDTO {

	private Integer projectId;
	
	@NotNull(message = "{project.projectname.absent}")
	private String projectName;
	
	//@Pattern(regexp = "[A-Za-z+( [A-Za-z]+)*]")    ///Another important annotation with blank space
	
	//@PastOrPresent(message = {""})   //For DOB 
	
	@NotNull(message = "{project.ideaowner.absent}")
	private Integer ideaOwner;
	
	@NotNull(message = "{project.releasedate.absent}")
	private LocalDate releaseDate;
	
	@NotNull(message = "{project.mentor.absent}")
	@Valid
	private MentorDTO mentorDTO;

	public ProjectDTO() {
		super();
	}
	
	public ProjectDTO(Integer projectId, String projectName,
			Integer ideaOwner, LocalDate releaseDate,
			MentorDTO mentorDTO) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.ideaOwner = ideaOwner;
		this.releaseDate = releaseDate;
		this.mentorDTO = mentorDTO;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getIdeaOwner() {
		return ideaOwner;
	}

	public void setIdeaOwner(Integer ideaOwner) {
		this.ideaOwner = ideaOwner;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public MentorDTO getMentorDTO() {
		return mentorDTO;
	}

	public void setMentorDTO(MentorDTO mentorDTO) {
		this.mentorDTO = mentorDTO;
	}

}
