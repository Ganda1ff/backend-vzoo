package ru.ulstu.is.server.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

    protected BaseEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Stable hash code: the generated UUID is assigned only after persist().
    // Changing a hash while the entity is in a HashSet breaks contains/remove.
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final BaseEntity other = (BaseEntity) obj;
        return id != null && id.equals(other.id);
    }
}
