/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.model.domain;

import java.io.Serializable;

/**
 *
 * @author Buddhini
 */
public class ContractType implements Serializable {

    private Long contractTypeDid;
    private String name;
    private String description;

    public Long getContractTypeDid() {
        return contractTypeDid;
    }

    public void setContractTypeDid(Long contractTypeDid) {
        this.contractTypeDid = contractTypeDid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
