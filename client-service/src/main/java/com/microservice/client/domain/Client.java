package com.microservice.client.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.microservice.client.utils.StringUtils;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "TB_CLIENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 150)
    @Pattern(regexp = "^[áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑa-zA-Z0-9_ ]*")
    @Column(name = "name_client")
    private String name;

    @NotNull
    @CPF
    @Size(min = 11)
    private String cpf;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @NotNull
    @Past(message = "Data de Nascimento não pode ser Futura")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate nascimento;

    @PrePersist
    @PreUpdate
    public void removeMask() {
        this.cpf = StringUtils.removeSpecialCaracter(this.cpf);
    }

    public String getCpf() {
        return StringUtils.addMaskFormatter(this.cpf,"###.###.###-##");
    }
}
