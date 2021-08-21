package com.harish.ListingReportsGenerator.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "contacts")
@JsonPropertyOrder({"listingId", "contactDate"})
public class Contacts {
    @Id
    @NotNull
    private int listingId;
    @NotNull
    private Date contactDate;

    public Contacts(int listingId, Date contactDate) {
        this.listingId = listingId;
        this.contactDate = contactDate;
    }

    public Contacts() {
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }
}
