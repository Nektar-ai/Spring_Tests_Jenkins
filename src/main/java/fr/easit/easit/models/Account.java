package fr.easit.easit.models;


import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "accounts")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    private String email;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private AccountInfos info;


    public Account(String username, String password, String email, AccountInfos info){
        this.info = info;
        //this.info = info;
        setUsername(username);
        setPassword(password);
        setEmail(email);
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountInfos getInfo(){
        return this.info;
    }

    public void setInfo(AccountInfos info) {
        this.info = info;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password=").append(password).append('\'');
        sb.append(", email=").append(email).append('\'');
        //sb.append(", json=").append(info).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
