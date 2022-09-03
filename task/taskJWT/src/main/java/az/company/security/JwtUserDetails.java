package az.company.security;

import az.company.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class JwtUserDetails implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static JwtUserDetails create(Student student){
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(student.getRole()));
        return new JwtUserDetails(
                student.getStudentId(),
                student.getEmail(),
                student.getPassword(),
                authorityList
        );
    }








    @Override
    public boolean isAccountNonExpired() {return true;}
    @Override
    public boolean isAccountNonLocked() {return true;}
    @Override
    public boolean isCredentialsNonExpired() {return true;}
    @Override
    public boolean isEnabled() {return true;}
}
