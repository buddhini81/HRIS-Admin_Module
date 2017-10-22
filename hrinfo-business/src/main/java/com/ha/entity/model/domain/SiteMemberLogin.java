/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.entity.model.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Buddhini
 */
public class SiteMemberLogin implements Serializable {

    private Long siteMemberDid;
    private Long userRoleDid;

    public Long getSiteMemberDid() {
        return siteMemberDid;
    }

    public void setSiteMemberDid(Long siteMemberDid) {
        this.siteMemberDid = siteMemberDid;
    }

    public Long getUserRoleDid() {
        return userRoleDid;
    }

    public void setUserRoleDid(Long userRoleDid) {
        this.userRoleDid = userRoleDid;
    }
    
}
