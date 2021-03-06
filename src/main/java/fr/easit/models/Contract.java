package fr.easit.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private Integer percentage;

    @OneToMany(mappedBy = "contract")
    private List<Client> clients;

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client){
        List<Client> clients = getClients();
        clients.add(client);
        setClients(clients);
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPercentage() {
        return percentage;
    }
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

}