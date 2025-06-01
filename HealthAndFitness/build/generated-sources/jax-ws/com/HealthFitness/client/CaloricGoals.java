
package com.HealthFitness.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CaloricGoals complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CaloricGoals">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tdee" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="goalsCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CaloricGoals", propOrder = {
    "tdee",
    "goalsCategory"
})
public class CaloricGoals {

    protected float tdee;
    protected String goalsCategory;

    /**
     * Gets the value of the tdee property.
     * 
     */
    public float getTdee() {
        return tdee;
    }

    /**
     * Sets the value of the tdee property.
     * 
     */
    public void setTdee(float value) {
        this.tdee = value;
    }

    /**
     * Gets the value of the goalsCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGoalsCategory() {
        return goalsCategory;
    }

    /**
     * Sets the value of the goalsCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGoalsCategory(String value) {
        this.goalsCategory = value;
    }

}
