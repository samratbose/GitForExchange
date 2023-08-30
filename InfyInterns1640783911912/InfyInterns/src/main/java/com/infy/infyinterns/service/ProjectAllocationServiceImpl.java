package com.infy.infyinterns.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;

@Service(value = "projectService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private MentorRepository mentorRepository;

	@Override
	public Integer allocateProject(ProjectDTO project) throws InfyInternException {
		Optional<Mentor> opt=mentorRepository.findById(project.getMentorDTO().getMentorId());
		Mentor mentor=opt.orElseThrow(()->new InfyInternException("Service.MENTOR_NOT_FOUND"));
		if(mentor.getNumberOfProjectsMentored()>3) {
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Project pr=new Project();
		pr.setIdeaOwner(project.getIdeaOwner());
		pr.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
		pr.setProjectId(project.getProjectId());
		pr.setProjectName(project.getProjectName());
		pr.setReleaseDate(project.getReleaseDate());
		Project newPro=projectRepository.save(pr);
		return newPro.getProjectId();
	}

	
	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException {
		List<Mentor> opt=mentorRepository.findByNumberOfProjectsMentored(numberOfProjectsMentored);
		if(opt.isEmpty()) {
			throw new InfyInternException("Service.MENTOR_NOT_FOUND");
		}
		List<MentorDTO> mentorDTOs=new ArrayList<>();
		for(Mentor mentor:opt) {
		MentorDTO mDto= new MentorDTO();
		mDto.setMentorId(mentor.getMentorId());
		mDto.setMentorName(mentor.getMentorName());
		mDto.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
		mentorDTOs.add(mDto);
		}
		return mentorDTOs;
		
	}


	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException {
		Optional<Mentor> opt= mentorRepository.findById(mentorId);
		Mentor mentor=opt.orElseThrow (()->new InfyInternException("Service.MENTOR_NOT_FOUND"));
		if(mentor.getNumberOfProjectsMentored()>3){
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Optional<Project> pro= projectRepository.findById(projectId);
		Project project=pro.orElseThrow(()-> new InfyInternException("Service.PROJECT_NOT_FOUND"));
		project.setMentor(mentor);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
	}

	@Override
	public void deleteProject(Integer projectId) throws InfyInternException {
		Optional<Project> proj= projectRepository.findById(projectId);
		Project pro= proj.orElseThrow(()-> new InfyInternException("Service.PROJECT_NOT_FOUND") );
		Mentor men= pro.getMentor();
		men.setNumberOfProjectsMentored(men.getNumberOfProjectsMentored()-1);
		pro.setMentor(null);
		projectRepository.delete(pro);
		
	}
}