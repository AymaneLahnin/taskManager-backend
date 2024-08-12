package emi.formation.tmbackend.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Table(name="users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @OneToMany(cascade =CascadeType.ALL)
    @JoinColumn(name = "task_user",referencedColumnName = "id")
    private List<Task> tasks;
    private String password;
}
