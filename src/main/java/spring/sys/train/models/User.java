package spring.sys.train.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name="_USER")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long Id;
    private String userName;
    private String email;

    private String password;
    private String phone;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Ticket> tickets= new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority= new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    public User addTickets(Ticket ticket){
        ticket.setUser(this);
        this.tickets.add(ticket);
        return this;
    }

}
