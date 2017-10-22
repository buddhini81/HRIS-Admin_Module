/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ha.presentation.util;

import java.io.Serializable;

/**
 *
 * @author Buddhini
 */
public class SelectBoxDataHolder implements Serializable {

    private Object value;
    private Object label;

    public SelectBoxDataHolder(Object value, Object label) {
        this.value = value;
        this.label = label;
    }



    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
   
}
