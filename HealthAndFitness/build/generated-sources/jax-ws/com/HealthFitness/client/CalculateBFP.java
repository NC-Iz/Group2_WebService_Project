
package com.HealthFitness.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for calculateBFP complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="calculateBFP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="waist" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="neck" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="hip" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateBFP", propOrder = {
    "gender",
    "age",
    "weight",
    "height",
    "waist",
    "neck",
    "hip"
})
public class CalculateBFP {

    protected String gender;
    protected int age;
    protected double weight;
    protected double height;
    protected double waist;
    protected double neck;
    protected double hip;

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

    /**
     * Gets the value of the age property.
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     */
    public void setWeight(double value) {
        this.weight = value;
    }

    /**
     * Gets the value of the height property.
     * 
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     */
    public void setHeight(double value) {
        this.height = value;
    }

    /**
     * Gets the value of the waist property.
     * 
     */
    public double getWaist() {
        return waist;
    }

    /**
     * Sets the value of the waist property.
     * 
     */
    public void setWaist(double value) {
        this.waist = value;
    }

    /**
     * Gets the value of the neck property.
     * 
     */
    public double getNeck() {
        return neck;
    }

    /**
     * Sets the value of the neck property.
     * 
     */
    public void setNeck(double value) {
        this.neck = value;
    }

    /**
     * Gets the value of the hip property.
     * 
     */
    public double getHip() {
        return hip;
    }

    /**
     * Sets the value of the hip property.
     * 
     */
    public void setHip(double value) {
        this.hip = value;
    }

}
