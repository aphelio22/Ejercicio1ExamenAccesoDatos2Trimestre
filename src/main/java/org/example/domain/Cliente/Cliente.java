package org.example.domain.Cliente;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Estado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Cliente implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private Long total;
    private Estado estado;

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                '}';
    }
}
