package fr.jehann.app.services;

import fr.jehann.app.dto.LoginDTO;
import fr.jehann.app.dto.LoginResponseDTO;
import fr.jehann.app.dto.MemberDTO;

import java.util.List;

public interface MemberService {

    // Create a new method to get a member by its id with all its projects
    MemberDTO findByIdWithProject(long id) throws Exception;

    List<MemberDTO> findAll() throws Exception;

    List<MemberDTO> findAllWithProject() throws Exception;

    MemberDTO findById(long id) throws Exception;

    MemberDTO save(MemberDTO member) throws Exception;

    MemberDTO update(Long id, MemberDTO member) throws Exception;

    boolean deleteById(long id) throws Exception;

    MemberDTO addProjectToMember(Long memberId, Long projectId) throws Exception;

    LoginResponseDTO checkLogin(LoginDTO lDto) throws Exception;

}

