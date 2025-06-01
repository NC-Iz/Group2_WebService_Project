
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

    private final static QName _BasalMetabolicRate_QNAME = new QName("http://service.HealthFitness.com/", "BasalMetabolicRate");
    private final static QName _TotalDailyEnergyExpenditureResponse_QNAME = new QName("http://service.HealthFitness.com/", "TotalDailyEnergyExpenditureResponse");
    private final static QName _BasalMetabolicRateResponse_QNAME = new QName("http://service.HealthFitness.com/", "BasalMetabolicRateResponse");
    private final static QName _CaloricGoalsResponse_QNAME = new QName("http://service.HealthFitness.com/", "CaloricGoalsResponse");
    private final static QName _MacroDistributionResponse_QNAME = new QName("http://service.HealthFitness.com/", "MacroDistributionResponse");
    private final static QName _CaloricGoals_QNAME = new QName("http://service.HealthFitness.com/", "CaloricGoals");
    private final static QName _TotalDailyEnergyExpenditure_QNAME = new QName("http://service.HealthFitness.com/", "TotalDailyEnergyExpenditure");
    private final static QName _MacroDistribution_QNAME = new QName("http://service.HealthFitness.com/", "MacroDistribution");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.HealthFitness.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BasalMetabolicRate }
     * 
     */
    public BasalMetabolicRate createBasalMetabolicRate() {
        return new BasalMetabolicRate();
    }

    /**
     * Create an instance of {@link TotalDailyEnergyExpenditureResponse }
     * 
     */
    public TotalDailyEnergyExpenditureResponse createTotalDailyEnergyExpenditureResponse() {
        return new TotalDailyEnergyExpenditureResponse();
    }

    /**
     * Create an instance of {@link BasalMetabolicRateResponse }
     * 
     */
    public BasalMetabolicRateResponse createBasalMetabolicRateResponse() {
        return new BasalMetabolicRateResponse();
    }

    /**
     * Create an instance of {@link CaloricGoalsResponse }
     * 
     */
    public CaloricGoalsResponse createCaloricGoalsResponse() {
        return new CaloricGoalsResponse();
    }

    /**
     * Create an instance of {@link MacroDistributionResponse }
     * 
     */
    public MacroDistributionResponse createMacroDistributionResponse() {
        return new MacroDistributionResponse();
    }

    /**
     * Create an instance of {@link CaloricGoals }
     * 
     */
    public CaloricGoals createCaloricGoals() {
        return new CaloricGoals();
    }

    /**
     * Create an instance of {@link TotalDailyEnergyExpenditure }
     * 
     */
    public TotalDailyEnergyExpenditure createTotalDailyEnergyExpenditure() {
        return new TotalDailyEnergyExpenditure();
    }

    /**
     * Create an instance of {@link MacroDistribution }
     * 
     */
    public MacroDistribution createMacroDistribution() {
        return new MacroDistribution();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BasalMetabolicRate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "BasalMetabolicRate")
    public JAXBElement<BasalMetabolicRate> createBasalMetabolicRate(BasalMetabolicRate value) {
        return new JAXBElement<BasalMetabolicRate>(_BasalMetabolicRate_QNAME, BasalMetabolicRate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TotalDailyEnergyExpenditureResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "TotalDailyEnergyExpenditureResponse")
    public JAXBElement<TotalDailyEnergyExpenditureResponse> createTotalDailyEnergyExpenditureResponse(TotalDailyEnergyExpenditureResponse value) {
        return new JAXBElement<TotalDailyEnergyExpenditureResponse>(_TotalDailyEnergyExpenditureResponse_QNAME, TotalDailyEnergyExpenditureResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BasalMetabolicRateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "BasalMetabolicRateResponse")
    public JAXBElement<BasalMetabolicRateResponse> createBasalMetabolicRateResponse(BasalMetabolicRateResponse value) {
        return new JAXBElement<BasalMetabolicRateResponse>(_BasalMetabolicRateResponse_QNAME, BasalMetabolicRateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CaloricGoalsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "CaloricGoalsResponse")
    public JAXBElement<CaloricGoalsResponse> createCaloricGoalsResponse(CaloricGoalsResponse value) {
        return new JAXBElement<CaloricGoalsResponse>(_CaloricGoalsResponse_QNAME, CaloricGoalsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MacroDistributionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "MacroDistributionResponse")
    public JAXBElement<MacroDistributionResponse> createMacroDistributionResponse(MacroDistributionResponse value) {
        return new JAXBElement<MacroDistributionResponse>(_MacroDistributionResponse_QNAME, MacroDistributionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CaloricGoals }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "CaloricGoals")
    public JAXBElement<CaloricGoals> createCaloricGoals(CaloricGoals value) {
        return new JAXBElement<CaloricGoals>(_CaloricGoals_QNAME, CaloricGoals.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TotalDailyEnergyExpenditure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "TotalDailyEnergyExpenditure")
    public JAXBElement<TotalDailyEnergyExpenditure> createTotalDailyEnergyExpenditure(TotalDailyEnergyExpenditure value) {
        return new JAXBElement<TotalDailyEnergyExpenditure>(_TotalDailyEnergyExpenditure_QNAME, TotalDailyEnergyExpenditure.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MacroDistribution }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.HealthFitness.com/", name = "MacroDistribution")
    public JAXBElement<MacroDistribution> createMacroDistribution(MacroDistribution value) {
        return new JAXBElement<MacroDistribution>(_MacroDistribution_QNAME, MacroDistribution.class, null, value);
    }

}
