package com.software.software.domain;

import java.util.Date;

public class EntidadeDominio {
    private int id;
    private Date createdAt;

    public EntidadeDominio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
