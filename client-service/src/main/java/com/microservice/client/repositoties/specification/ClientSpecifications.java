package com.microservice.client.repositoties.specification;

import com.microservice.client.domain.Client;
import com.microservice.client.domain.enums.UF;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Objects;

public class ClientSpecifications {


    private ClientSpecifications() {

    }

    public final static Specification<Client> likeName(String name) {
        return (root, query, builder) -> {
            String nameFilter = Objects.isNull(name) ? "" : name;
            builder.lower(root.get("name"));
            return builder.like(root.get("name"), "%" + nameFilter.toLowerCase() + "%");
        };
    }

    public final static Specification<Client> equalsCpf(String cpf) {

        return (root, query, builder) -> builder.equal(root.get("cpf"), cpf);
    }

    public final static Specification<Client> equalsCep(String cep) {
        return (root, query, builder) -> builder.equal(root.get("address").get("cep"), cep);
    }

    public final static Specification<Client> likePublicPlace(String publicPlace) {
        return (root, query, builder) -> {
            builder.lower(root.get("address").get("publicPlace"));
            return builder.like(root.get("address").get("publicPlace"), "%" + publicPlace.toLowerCase() + "%");
        };
    }

    public final static Specification<Client> likeNeighborhood(String neighborhood) {
        return (root, query, builder) -> {
            builder.lower(root.get("address").get("neighborhood"));
            return builder.like(root.get("address").get("neighborhood"), "%" + neighborhood.toLowerCase() + "%");
        };
    }

    public final static Specification<Client> likeCity(String city) {
        return (root, query, builder) -> {
            builder.lower(root.get("address").get("city"));
            return builder.like(root.get("address").get("city"), "%" + city.toLowerCase() + "%");
        };
    }

    public final static Specification<Client> equalsUF(UF uf) {
        return (root, query, builder) -> builder.equal(root.get("address").get("uf"), uf);
    }

    public final static Specification<Client> likeComplement(String complement) {
        return (root, query, builder) -> {
            builder.lower(root.get("address").get("complement"));
            return builder.like(root.get("address").get("complement"), "%" + complement.toLowerCase() + "%");
        };
    }

    public final static Specification<Client> equalsIdade(Integer idade) {
        return (root, query, builder) -> {
            LocalDate dataInicial = LocalDate.now().minusYears(idade).minusYears(1).plusDays(1);
            LocalDate dataFinal = LocalDate.now().minusYears(idade);
            return builder.between(root.get("nascimento"), dataInicial, dataFinal);
        };
    }
}
