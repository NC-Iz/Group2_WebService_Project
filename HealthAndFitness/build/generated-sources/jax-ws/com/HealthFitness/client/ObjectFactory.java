
package com.HealthFitness.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.HealthFitness.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CalculateSleepTimesResponse_QNAME = new QName("http://service.HealthFitness.com/", "calculateSleepTimesResponse");
    private final static QName _CalculateBMIResponse_QNAME = new QName("http://service.HealthFitness.com/", "calculateBMIResponse");
    private final static QName _CalculateCaloriesBurnedResponse_QNAME = new QName("http://service.HealthFitness.com/", "calculateCaloriesBurnedResponse");
    private final static QName _CalculateBMI_QNAME = new QName("http://service.HealthFitness.com/", "calculateBMI");
    private final static QName _CalculateBFP_QNAME = new QName("http://service.HealthFitness.com/", "calculateBFP");
    private final static QName _CalculateCaloriesBurned_QNAME = new QName("http://service.HealthFitness.com/", "calculateCaloriesBurned");
    private final static QName _CalculateSleepTimes_QNAME = new QName("http://service.HealthFitness.com/", "calculateSleepTimes");
    private final static QName _CalculateBFPResponse_QNAME = new QName("http://service.HealthFitness.com/", "calculateBFPResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.HealthFitness.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateSleepTimesResponse }
     * 
     */
    public CalculateSleepTimesResponse createCalculateSleepTimesResponse() {
        return new CalculateSleepTimesResponse();
    }

    /**
     * Create an instance of {@link CalculateBMIResponse }
     * 
     */
    public CalculateBMIResponse createCalculateBMIResponse() {
        return new CalculateBMIResponse();
    }

    /**
     * Create an instance of {@link CalculateCaloriesBurnedResponse }
     * 
     */
    public CalculateCaloriesBurnedResponse createCalculateCaloriesBurnedResponse() {
        return new CalculateCaloriesBurnedResponse();
    }

    /**
     * Create an instance of {@link CalculateBMI }
     * 
     */
    public CalculateBMI createCalculateBMI() {
        return new CalculateBMI();
    }

    /**
     * Create an instance of {@link CalculateBFP }
     * 
     */
    public CalculateBFP createCalculateBFP() {
        return new CalculateBFP();
    }

    /**
     * Create an instance of {@link CalculateCaloriesBurned }
     * 
     */
    public CalculateCaloriesBurned createCalculateCaloriesBurned() {
        return new CalculateCaloriesBurned();
    }

    /**
     * Create an instance of {@link CalculateSleepTimes }
     * 
     */
    public CalculateSleepTimes createCalculateSleepTimes() {
        return new CalculateSleepTimes();
    }

    /**
     * Create an instance of {@link CalculateBFPResponse }
     * 
     */
    public CalculateBFPResponse createCalculateBFPResponse() {
        return new CalculateBFPResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateSleepTimesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "calculateSleepTimesResponse")
    public JAXBElement<CalculateSleepTimesResponse> createCalculateSleepTimesResponse(CalculateSleepTimesResponse value) {
        return new JAXBElement<CalculateSleepTimesResponse>(_CalculateSleepTimesResponse_QNAME, CalculateSleepTimesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateBMIResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "calculateBMIResponse")
    public JAXBElement<CalculateBMIResponse> createCalculateBMIResponse(CalculateBMIResponse value) {
        return new JAXBElement<CalculateBMIResponse>(_CalculateBMIResponse_QNAME, CalculateBMIResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateCaloriesBurnedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "calculateCaloriesBurnedResponse")
    public JAXBElement<CalculateCaloriesBurnedResponse> createCalculateCaloriesBurnedResponse(CalculateCaloriesBurnedResponse value) {
        return new JAXBElement<CalculateCaloriesBurnedResponse>(_CalculateCaloriesBurnedResponse_QNAME, CalculateCaloriesBurnedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateBMI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "calculateBMI")
    public JAXBElement<CalculateBMI> createCalculateBMI(CalculateBMI value) {
        return new JAXBElement<CalculateBMI>(_CalculateBMI_QNAME, CalculateBMI.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateBFP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "calculateBFP")
    public JAXBElement<CalculateBFP> createCalculateBFP(CalculateBFP value) {
        return new JAXBElement<CalculateBFP>(_CalculateBFP_QNAME, CalculateBFP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateCaloriesBurned }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "calculateCaloriesBurned")
    public JAXBElement<CalculateCaloriesBurned> createCalculateCaloriesBurned(CalculateCaloriesBurned value) {
        return new JAXBElement<CalculateCaloriesBurned>(_CalculateCaloriesBurned_QNAME, CalculateCaloriesBurned.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateSleepTimes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "calculateSleepTimes")
    public JAXBElement<CalculateSleepTimes> createCalculateSleepTimes(CalculateSleepTimes value) {
        return new JAXBElement<CalculateSleepTimes>(_CalculateSleepTimes_QNAME, CalculateSleepTimes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateBFPResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "calculateBFPResponse")
    public JAXBElement<CalculateBFPResponse> createCalculateBFPResponse(CalculateBFPResponse value) {
        return new JAXBElement<CalculateBFPResponse>(_CalculateBFPResponse_QNAME, CalculateBFPResponse.class, null, value);
    }

}
