package com.microservice.client.domain;


import com.microservice.client.domain.enums.UF;
import com.microservice.client.utils.StringUtils;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "TB_ADDRESS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 8)
    @NotNull
    private String cep;

    @NotNull
    @Size(min = 3,max = 100)
    private String publicPlace;

    @NotNull
    @Size(min = 3,max = 100)
    private String neighborhood;

    @NotNull
    @Size(min = 3, max = 100)
    private String city;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Size(max = 254)
    private String complement;

    @PrePersist
    @PreUpdate
    public void removeMask() {
        this.cep = StringUtils.removeSpecialCaracter(this.cep);
    }

    public String getCep() {
        return StringUtils.addMaskFormatter(this.cep,"#####-###");
    }

}
