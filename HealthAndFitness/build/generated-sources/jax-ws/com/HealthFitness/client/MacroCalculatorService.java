
package com.HealthFitness.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MacroCalculatorService", targetNamespace = "http://service.HealthFitness.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MacroCalculatorService {


    /**
     * 
     * @param gender
     * @param ageYears
     * @param heightCM
     * @param weightKG
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "BasalMetabolicRate")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "BasalMetabolicRate", targetNamespace = "http://service.HealthFitness.com/", className = "com.HealthFitness.client.BasalMetabolicRate")
    @ResponseWrapper(localName = "BasalMetabolicRateResponse", targetNamespace = "http://service.HealthFitness.com/", className = "com.HealthFitness.client.BasalMetabolicRateResponse")
    @Action(input = "http://service.HealthFitness.com/MacroCalculatorService/BasalMetabolicRateRequest", output = "http://service.HealthFitness.com/MacroCalculatorService/BasalMetabolicRateResponse")
    public String basalMetabolicRate(
        @WebParam(name = "weightKG", targetNamespace = "")
        float weightKG,
        @WebParam(name = "heightCM", targetNamespace = "")
        float heightCM,
        @WebParam(name = "ageYears", targetNamespace = "")
        int ageYears,
        @WebParam(name = "gender", targetNamespace = "")
        String gender);

    /**
     * 
     * @param goalsCategory
     * @param tdee
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "CaloricGoals")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "CaloricGoals", targetNamespace = "http://service.HealthFitness.com/", className = "com.HealthFitness.client.CaloricGoals")
    @ResponseWrapper(localName = "CaloricGoalsResponse", targetNamespace = "http://service.HealthFitness.com/", className = "com.HealthFitness.client.CaloricGoalsResponse")
    @Action(input = "http://service.HealthFitness.com/MacroCalculatorService/CaloricGoalsRequest", output = "http://service.HealthFitness.com/MacroCalculatorService/CaloricGoalsResponse")
    public String caloricGoals(
        @WebParam(name = "tdee", targetNamespace = "")
        float tdee,
        @WebParam(name = "goalsCategory", targetNamespace = "")
        String goalsCategory);

    /**
     * 
     * @param bmr
     * @param activityLevel
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "TotalDailyEnergyExpenditure")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "TotalDailyEnergyExpenditure", targetNamespace = "http://service.HealthFitness.com/", className = "com.HealthFitness.client.TotalDailyEnergyExpenditure")
    @ResponseWrapper(localName = "TotalDailyEnergyExpenditureResponse", targetNamespace = "http://service.HealthFitness.com/", className = "com.HealthFitness.client.TotalDailyEnergyExpenditureResponse")
    @Action(input = "http://service.HealthFitness.com/MacroCalculatorService/TotalDailyEnergyExpenditureRequest", output = "http://service.HealthFitness.com/MacroCalculatorService/TotalDailyEnergyExpenditureResponse")
    public String totalDailyEnergyExpenditure(
        @WebParam(name = "bmr", targetNamespace = "")
        float bmr,
        @WebParam(name = "activityLevel", targetNamespace = "")
        String activityLevel);

    /**
     * 
     * @param goal
     * @param calories
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "MacroDistribution")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "MacroDistribution", targetNamespace = "http://service.HealthFitness.com/", className = "com.HealthFitness.client.MacroDistribution")
    @ResponseWrapper(localName = "MacroDistributionResponse", targetNamespace = "http://service.HealthFitness.com/", className = "com.HealthFitness.client.MacroDistributionResponse")
    @Action(input = "http://service.HealthFitness.com/MacroCalculatorService/MacroDistributionRequest", output = "http://service.HealthFitness.com/MacroCalculatorService/MacroDistributionResponse")
    public String macroDistribution(
        @WebParam(name = "calories", targetNamespace = "")
        float calories,
        @WebParam(name = "goal", targetNamespace = "")
        String goal);

}
