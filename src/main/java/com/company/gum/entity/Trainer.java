package com.company.gum.entity;

import java.time.LocalDateTime;

public class Trainer extends User {

    private LocalDateTime registerDate;
    private String phone;
    private String mail;

    public Trainer() {
        role = UserRole.TRAINER;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Trainer)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Trainer trainer = (Trainer) o;

        if (getRegisterDate() != null ? !getRegisterDate().equals(trainer.getRegisterDate()) : trainer.getRegisterDate() != null) {
            return false;
        }
        if (getPhone() != null ? !getPhone().equals(trainer.getPhone()) : trainer.getPhone() != null) {
            return false;
        }
        return getMail() != null ? getMail().equals(trainer.getMail()) : trainer.getMail() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRegisterDate() != null ? getRegisterDate().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trainer{");
        sb.append("registerDate=").append(registerDate);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
