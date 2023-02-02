package com.example.gestionEncheres.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;

@NoArgsConstructor
@Document("photos")
@AllArgsConstructor
@Setter
@Getter
public class Photo {
    @Id
    private String id;
    @Field("idEnchere")
    private Integer idEnchere;
    @Field("photos")
    private String[] photos;

    public Photo(Integer idEnchere, String[] photos) {
        this.idEnchere = idEnchere;
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", idEnchere=" + idEnchere +
                ", photos=" + Arrays.toString(photos) +
                '}';
    }
}
