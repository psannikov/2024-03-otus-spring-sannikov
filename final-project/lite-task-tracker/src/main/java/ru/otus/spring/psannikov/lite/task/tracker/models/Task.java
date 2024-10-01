package ru.otus.spring.psannikov.lite.task.tracker.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
@Entity
@Table(name = "tasks")
@NamedEntityGraph(name = "tasks-priorities-entity-graph",
        attributeNodes = {@NamedAttributeNode("priority")})
@NamedEntityGraph(name = "tasks-statuses-entity-graph",
        attributeNodes = {@NamedAttributeNode("status")})
@NamedEntityGraph(name = "tasks-works-entity-graph",
        attributeNodes = {@NamedAttributeNode("works")})
@NamedEntityGraph(name = "tasks-tasks-entity-graph",
        attributeNodes = {@NamedAttributeNode("parent")})
@NamedEntityGraph(name = "tasks-users-entity-graph",
        attributeNodes = {@NamedAttributeNode("owner")})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @ManyToOne(targetEntity = Priority.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne(targetEntity = Status.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(targetEntity = Work.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private List<Work> works;

    @ManyToOne(targetEntity = Task.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Task parent;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User owner;
}
