package com.company.gum.entity.userImpl;

import java.time.LocalDateTime;

public class Trainer extends User {

    private LocalDateTime registerDate;
    private int phone;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
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
        if (this == o) return true;
        if (!(o instanceof Trainer)) return false;
        if (!super.equals(o)) return false;

        Trainer trainer = (Trainer) o;

        if (getPhone() != trainer.getPhone()) return false;
        if (getRegisterDate() != null ? !getRegisterDate().equals(trainer.getRegisterDate()) : trainer.getRegisterDate() != null)
            return false;
        return getMail() != null ? getMail().equals(trainer.getMail()) : trainer.getMail() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getRegisterDate() != null ? getRegisterDate().hashCode() : 0);
        result = 31 * result + getPhone();
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Trainer{");
        sb.append("registerDate=").append(registerDate);
        sb.append(", phone=").append(phone);
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
