package com.tw.twjpa.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Company {
    private String companyName;
    private String companyAddress;
}
