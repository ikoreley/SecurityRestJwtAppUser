package ik.koresh.securityrestjwtappuser.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "events", indexes = @Index(name = "idx_owner", columnList="user_id"))
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserApp owner;

    @Column(nullable = false)
    private String action;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime actionDate;

    @PrePersist
    protected void onCreate(){
        this.actionDate = LocalDateTime.now();
    }


}
