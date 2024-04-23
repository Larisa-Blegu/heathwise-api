package com.healthwise.HealthwiseApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false,  columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false,  columnDefinition = "LONGTEXT")
    private String descriptionDisease;

    @JsonIgnoreProperties("specializations")
    @ManyToMany(mappedBy = "specializations", fetch = FetchType.EAGER)
    private List<Doctor> doctors = new ArrayList<>();
}
