
package com.HealthFitness.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TotalDailyEnergyExpenditure complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TotalDailyEnergyExpenditure">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bmr" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="activityLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TotalDailyEnergyExpenditure", propOrder = {
    "bmr",
    "activityLevel"
})
public class TotalDailyEnergyExpenditure {

    protected float bmr;
    protected String activityLevel;

    /**
     * Gets the value of the bmr property.
     * 
     */
    public float getBmr() {
        return bmr;
    }

    /**
     * Sets the value of the bmr property.
     * 
     */
    public void setBmr(float value) {
        this.bmr = value;
    }

    /**
     * Gets the value of the activityLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityLevel() {
        return activityLevel;
    }

    /**
     * Sets the value of the activityLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityLevel(String value) {
        this.activityLevel = value;
    }

}
