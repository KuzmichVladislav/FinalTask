package com.company.gum.entity;

// TODO: Auto-generated Javadoc

/**
 * The Class AbstractEntity.
 */
public class AbstractEntity {

    /**
     * The id.
     */
    private Integer id;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractEntity)) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
