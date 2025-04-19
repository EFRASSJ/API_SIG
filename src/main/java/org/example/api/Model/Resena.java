package org.example.api.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@Document(collection = "resenas")
public class Resena {
    @Id
    private String id;
    private String comentario;
    private Integer puntuacion;

    private String userId;

    @DBRef
    private Mesa mesaId;

    public Mesa getMesaId() {
        return mesaId;
    }

    public void setMesaId(Mesa mesaId) {
        this.mesaId = mesaId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}