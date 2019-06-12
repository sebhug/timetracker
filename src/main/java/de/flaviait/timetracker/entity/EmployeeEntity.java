package de.flaviait.timetracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_1")
    private Long id;
    @Column(name = "object_id", nullable = false, updatable = false, unique = true)
    private String objectId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "creation_timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant creationTimestamp;
    @Column(name = "update_timestamp", nullable = false)
    @UpdateTimestamp
    private Instant updateTimestamp;
    @Column(name = "version", nullable = false)
    @Version
    private Long version;
}
