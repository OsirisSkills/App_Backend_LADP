package fr.jehann.app.controllers;

import fr.jehann.app.dto.LoginDTO;
import fr.jehann.app.dto.LoginResponseDTO;
import fr.jehann.app.dto.MemberDTO;
import fr.jehann.app.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @GetMapping()
    public ResponseEntity<List<MemberDTO>> findAll() {
        try {
            return ResponseEntity.ok(memberService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable("id") Long id) throws Exception {
        MemberDTO member = memberService.findById(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member);
    }

    @GetMapping("/project")
    public ResponseEntity<List<MemberDTO>> findAllWithProject() {
        try {
            return ResponseEntity.ok(memberService.findAllWithProject());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<MemberDTO> findByIdWithProject(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(memberService.findByIdWithProject(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @PostMapping()
    public ResponseEntity<MemberDTO> save(@RequestBody MemberDTO memberDTO) {
        try {
            MemberDTO savedMember = memberService.save(memberDTO);
            return ResponseEntity.ok(savedMember);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{memberId}/project/{projectId}")
    public ResponseEntity<MemberDTO> addProjectToMember(@PathVariable("memberId") Long memberId, @PathVariable("projectId") Long projectId) {
        try {
            MemberDTO savedProject = memberService.addProjectToMember(memberId, projectId);
            return ResponseEntity.ok(savedProject);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> update(@PathVariable("id") long id, @RequestBody MemberDTO memberDTO) {
        try {
            MemberDTO memberExist = memberService.findById(id);
            if (memberExist == null) {
                System.out.printf("Member with id %d not found\n", id);
                return ResponseEntity.notFound().build();
            }
            System.out.printf("Member with id %d found\n", id);
            MemberDTO savedMember = memberService.update(id, memberDTO);
            System.out.println("savedMember : " + savedMember);
            if (savedMember == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(savedMember);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        try {
            boolean delReturn = memberService.deleteById(id);
            if (delReturn) {
                return ResponseEntity.ok().build();
            }
            else
            {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping( value ="/login", produces ="application/json", consumes = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){
        try {
            LoginResponseDTO loginResponseDTO = memberService.checkLogin(loginDTO);
            return ResponseEntity.ok().body(loginResponseDTO);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }
}
