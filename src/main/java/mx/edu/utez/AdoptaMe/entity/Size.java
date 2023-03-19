package mx.edu.utez.AdoptaMe.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "size")
@Data
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
