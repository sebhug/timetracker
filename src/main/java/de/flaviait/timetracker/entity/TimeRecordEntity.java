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
@Entity(name = "time_record")
public class TimeRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_record_seq_1")
    private Long id;
    @Column(name = "object_id", nullable = false, updatable = false, unique = true)
    private String objectId;
    @Column(name = "minutes", nullable = false)
    private Integer minutes;
    @Column(name = "record_date", nullable = false)
    private Instant recordDate;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
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
