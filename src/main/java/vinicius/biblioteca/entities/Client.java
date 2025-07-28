package vinicius.biblioteca.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID client_id;

    @JsonProperty("clientName")
    @Column(name = "client_name")
    private String client_name;

    @JsonProperty("clientCPF")
    @Column(name = "client_cpf")
    private String client_cpf;

}
