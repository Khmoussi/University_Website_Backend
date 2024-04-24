package com.example.issatc.Infrastructure.EntityMappers;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@AllArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)


public class UserMapper implements UserDetails {
    @Id
    private String email;

    private String firstName;
    private String lastName;
    private String password;

    private Role role;
    private  long phoneNumb;



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //   @OneToMany(mappedBy = "user")
 //   private List<Token> token;
    public UserMapper() {

    }

    public UserMapper(String email, String name, String lastname,Role role) {
        this.email = email;
        this.firstName = name;
        this.lastName = lastname;
        this.role=role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(this.role!=null)
        return this.role.getAuthorities();
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public Role getRole(){
        return this.role;
    }
}
