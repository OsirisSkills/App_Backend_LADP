package fr.jehann.app.services;

import fr.jehann.app.dto.LoginDTO;
import fr.jehann.app.dto.LoginResponseDTO;
import fr.jehann.app.entities.Member;
import fr.jehann.app.entities.Project;

import fr.jehann.app.dto.MemberDTO;
import fr.jehann.app.enums.MemberRole;

import fr.jehann.app.repositories.MemberRepository;
import fr.jehann.app.repositories.ProjectRepository;


import fr.jehann.app.tools.DtoTools;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.sqm.sql.ConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.lang.reflect.Field;
import java.util.*;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final ProjectRepository projectRepository;

    private final Log log = LogFactory.getLog(MemberService.class);

    @Autowired
    private HttpServletRequest request;

    public MemberServiceImpl(MemberRepository memberRepository, ProjectRepository projectRepository) {
        this.memberRepository = memberRepository;
        this.projectRepository = projectRepository;
    }

    @Value("${app.ldap.url}")
    private String ldapUrl;

    @Value("${app.ldap.protocol}")
    private String ldapProtocol;

    @Value("${app.ldap.technical.dn}")
    private String ldapTechnicalAccDN;

    @Value("${app.ldap.technical.pwd}")
    private String ldapTechnicalAccPwd;


    // Create a new method to get a member by its id with all its projects
    @Override
    public MemberDTO findByIdWithProject(long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);

        if (member.isPresent()) {
            List<Project> projects = member.get().getProjectGroups();
            List<Long> projectIds = new ArrayList<>();
            for (Project project : projects) {
                projectIds.add(project.getId());
            }

            MemberDTO memberDTO = DtoTools.convert(member.get(), MemberDTO.class);
            if (memberDTO != null) {
                memberDTO.setProjectIds(projectIds);
            }

            return memberDTO;
        } else {
            return null;
        }
    }

    @Override
    public List<MemberDTO> findAll() throws Exception {
        List<Member> members = memberRepository.findAll();

        List<MemberDTO> result = new ArrayList<>();
        for (Member mem : members) {
            result.add(DtoTools.convert(mem, MemberDTO.class));
        }
        return result;
    }

    @Override
    public List<MemberDTO> findAllWithProject() throws Exception {
        List<Member> members = memberRepository.findAll();
        List<MemberDTO> result = new ArrayList<>();

        // TODO: Terminer la méthode en intégrant la recherche des IDs

        for (Member member : members) {
            List<Project> projects = member.getProjectGroups();
            System.out.println(projects.toString());
            List<Long> projectIds = new ArrayList<>();
            for (Project project : projects) {
                projectIds.add(project.getId());
            }
            MemberDTO memberDTO = DtoTools.convert(member, MemberDTO.class);
            if (memberDTO != null) {
                System.out.printf("MemberDTO - 1: %s\n", memberDTO);
                if (!projectIds.isEmpty())
                    memberDTO.setProjectIds(projectIds);

                System.out.printf("MemberDTO - 2: %s\n", memberDTO);
            }
            result.add(memberDTO);
        }
        System.out.println("------------------------------------");
        System.out.println(result);
        return result;
    }


    @Override
    public MemberDTO findById(long id) throws Exception {
        List<Member> members = memberRepository.findAll();

        for (Member mem : members) {
            if (mem.getId() == id) {
                String roleString = mem.getRole().toString();
                if (roleString != null) {
                    try {
                        MemberRole role = MemberRole.valueOf(roleString);
                        mem.setRole(role);
                    } catch (IllegalArgumentException e) {
                        mem.setRole(null);
                    }
                }
                return DtoTools.convert(mem, MemberDTO.class);
            }
        }
        return null;
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) throws Exception {
        Member member = DtoTools.convert(memberDTO, Member.class);
        if (member == null) {
            throw new ConversionException("Conversion to Member object failed");
        }
        Member savedMember = memberRepository.save(member);
        return DtoTools.convert(savedMember, MemberDTO.class);
    }

    @Override
    public MemberDTO update(Long id, MemberDTO memberToUpdate) throws Exception {
        Optional<Member> memberOptional = memberRepository.findById(id);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            Field[] fields = memberToUpdate.getClass().getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                System.out.println(field.getName());
                System.out.println(field.get(memberToUpdate));
                System.out.println("------------------------");
            }
            System.out.println("Hello");
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(memberToUpdate) != null) {
                    switch (field.getName()) {
                        case "version":
                            member.setVersion((Integer) field.get(memberToUpdate));
                            break;
                        case "availabilityDate":
                            member.setAvailabilityDate((Date) field.get(memberToUpdate));
                            break;
                        case "lastName":
                            member.setLastName((String) field.get(memberToUpdate));
                            break;
                        case "firstName":
                            member.setFirstName((String) field.get(memberToUpdate));
                            break;
                        case "email":
                            member.setEmail((String) field.get(memberToUpdate));
                            break;
                        case "role":
                            member.setRole((MemberRole) field.get(memberToUpdate));
                            break;
                        case "projectIds":
                            // TODO : Faire une méthode qui ajoute les project grace aux ids
                            break;
                        default:
                            return null;
                    }
                }
            }
            memberRepository.save(member);
            return DtoTools.convert(member, MemberDTO.class);
        }
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        if (!memberRepository.existsById(id)) {
            return false;
        }
        memberRepository.deleteById(id);
        return true;
    }

    @Override
    public MemberDTO addProjectToMember(Long memberId, Long projectId) throws Exception {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Project> project = projectRepository.findById(projectId);

        if (member.isPresent() & project.isPresent()) {
            member.get().getProjectGroups().add(project.get());
            memberRepository.save(member.get());
            return DtoTools.convert(member, MemberDTO.class);
        }
        return null;
    }

    /**
     * @param environment: LDAP Environment
     * @param lDTO: POST request login dto
     * @throws NamingException: Ldap exception
     */
    private void validateLDAPConnection(Hashtable<String, String> environment, LoginDTO lDTO) throws NamingException {
        DirContext ctx = new InitialDirContext(environment);
        this.log.info("Login of user " + lDTO.getEmail().toLowerCase() + " from : " + request.getRemoteAddr());
        ctx.close();
    }

    /**
     *
     * @param environment: LDAP Environment
     * @param loginDto: Post login dto
     * @param uid: LDAP uid
     * @param organizationUnit: LDAP organization unit
     * @return {@link Member}: The generated user
     * @throws Exception: Directory Error
     */
    private Member createUserFromLdapUser(
            Hashtable<String, String> environment, LoginDTO loginDto,
            String uid, String organizationUnit
    ) throws Exception {
        environment.put(Context.SECURITY_PRINCIPAL, ldapTechnicalAccDN);
        environment.put(Context.SECURITY_CREDENTIALS, ldapTechnicalAccPwd);
        System.out.println("ldap");

        try {
            DirContext ctx = new InitialDirContext(environment);
            SearchControls searchCtls = new SearchControls();
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String searchFilter = "objectClass=inetOrgPerson";
            NamingEnumeration<?> namingEnum = ctx.search("uid="+uid + ",ou="+organizationUnit+",ou=Utilisateurs,dc=dawan,dc=fr", searchFilter, searchCtls);
            while (namingEnum.hasMore()) {
                SearchResult result = (SearchResult) namingEnum.next();
                Attributes userAttr = result.getAttributes();

                Member newMember = new Member();
                newMember.setRole(MemberRole.CLIENT);

                newMember.setEmail(loginDto.getEmail().toLowerCase()); // email from login dto
                try {
                    newMember.setLastName(userAttr.get("sn").get(0).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    newMember.setFirstName(userAttr.get("givenName").get(0).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.log.info("Save new ldap user");
                return  this.memberRepository.saveAndFlush(newMember);
            }
            namingEnum.close();
            ctx.close();
        } catch (Exception e) {
            // e.printStackTrace(); // DEBUG ONLY
            throw new Exception("Error : Directory search Error !");
        }
        throw new Exception("Error : Directory search Error !");
    }

    /**
     * Check if the ldap user exist in the database. If the user exist, check if the account is active.
     * If the user do not exist generate a new entry.
     * @param loginDto: POST login dto
     * @return {@link Member}: User found in database
     */
    private Member checkLDAPLogin(LoginDTO loginDto) throws Exception {
        // login via LDAP

        Hashtable<String, String> environment = new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, ldapUrl);
        environment.put(Context.SECURITY_PROTOCOL, ldapProtocol);
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");

        String organizationUnit = "Dawan";
        String uid = loginDto.getEmail().toLowerCase().substring(0, loginDto.getEmail().indexOf('@'));
        // les utilisateurs jehann se loggent avec un compte : xxxx-jehann

        if (loginDto.getEmail().toLowerCase().contains("@jehann.fr")) {
            uid = uid + "-jehann";
            organizationUnit = "jehann";
        }
        environment.put(Context.SECURITY_PRINCIPAL,"uid=" + uid + ",ou=" + organizationUnit + ",ou=Utilisateurs,dc=dawan,dc=fr");
        environment.put(Context.SECURITY_CREDENTIALS, loginDto.getPassword());

        this.validateLDAPConnection(environment, loginDto);
        // check if user exist in application Db
        Member member;
        Optional<Member> m = memberRepository.findByEmail(loginDto.getEmail().toLowerCase());
        if (!m.isPresent()) {
            log.info("No user found for " + loginDto.getEmail() + ": creation new LDAP user");
            member = this.createUserFromLdapUser(environment, loginDto, uid, organizationUnit);
            this.log.info(member.toString());
        } else {
            log.info("user found for " + m.get().getEmail());
            member = m.get();
        }
        return member;
    }


    // TO DO : Implementer le JWT ( generation du token )
    @Override
    public LoginResponseDTO checkLogin(LoginDTO lDto) throws Exception {
        Member  m = this.checkLDAPLogin(lDto);
        return DtoTools.convert(m, LoginResponseDTO.class);

    }
}
