package mx.edu.utez.AdoptaMe.entity;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "personality")
@Data
public class Personality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 70)
    private String name;
}
