package fr.easit.easit.models;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.TypeDef;
import org.json.JSONObject;
@TypeDef(name = "json", typeClass = JsonType.class)
public class AccountInfos {
    private int age;
    private String sexe;

    public AccountInfos(int age, String sexe) {
        setAge(age);
        setSexe(sexe);
    }

    public AccountInfos(String infos){
        JSONObject json = new JSONObject(infos);
        setAge(Integer.getInteger(json.get("age").toString()));
        setSexe(json.get("sexe").toString());
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
