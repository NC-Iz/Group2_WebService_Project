
package com.HealthFitness.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BasalMetabolicRate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasalMetabolicRate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="weightKG" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="heightCM" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ageYears" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasalMetabolicRate", propOrder = {
    "weightKG",
    "heightCM",
    "ageYears",
    "gender"
})
public class BasalMetabolicRate {

    protected float weightKG;
    protected float heightCM;
    protected int ageYears;
    protected String gender;

    /**
     * Gets the value of the weightKG property.
     * 
     */
    public float getWeightKG() {
        return weightKG;
    }

    /**
     * Sets the value of the weightKG property.
     * 
     */
    public void setWeightKG(float value) {
        this.weightKG = value;
    }

    /**
     * Gets the value of the heightCM property.
     * 
     */
    public float getHeightCM() {
        return heightCM;
    }

    /**
     * Sets the value of the heightCM property.
     * 
     */
    public void setHeightCM(float value) {
        this.heightCM = value;
    }

    /**
     * Gets the value of the ageYears property.
     * 
     */
    public int getAgeYears() {
        return ageYears;
    }

    /**
     * Sets the value of the ageYears property.
     * 
     */
    public void setAgeYears(int value) {
        this.ageYears = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

}
