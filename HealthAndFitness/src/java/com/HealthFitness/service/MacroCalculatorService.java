/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HealthFitness.service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author User
 */
@WebService(serviceName = "MacroCalculatorService")
public class MacroCalculatorService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "BasalMetabolicRate")
    public String BasalMetabolicRate(@WebParam(name = "weightKG") float weightKG, @WebParam(name = "heightCM") float heightCM, @WebParam(name = "ageYears") int ageYears, @WebParam(name = "gender") String gender) {
        //TODO write your implementation code here:
        //Input Validation
        if (weightKG <= 0 || heightCM <= 0 || ageYears <= 0) {
            return "Weight, height, and age must be positive values.";
        }

        if (gender == null || (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female"))) {
            return "Invalid gender specified. Please use 'male' or 'female'.";
        }
        float bmr;
        
        if (gender.equalsIgnoreCase("male")) {
        bmr = (10 * weightKG) + (6.25f * heightCM) - (5 * ageYears) + 5;
        } else if (gender.equalsIgnoreCase("female")) {
            bmr = (10 * weightKG) + (6.25f * heightCM) - (5 * ageYears) - 161;
        } else {
            return "Invalid gender specified. Please use 'male' or 'female'.";
        }

        return String.format("%.2f", bmr);
    }

    @WebMethod(operationName = "TotalDailyEnergyExpenditure")
    public String TotalDailyEnergyExpenditure(@WebParam(name = "bmr") float bmr, @WebParam(name = "activityLevel") String activityLevel) {
        //TODO write your implementation code here:
        //Input Validation
        if (bmr <= 0) {
            return "BMR must be a positive value.";
        }
        double activityFactor;
        switch (activityLevel.toLowerCase()) {
            case "sedentary": // Little or no exercise, desk job
                activityFactor = 1.2;
                break;
            case "lightly active": // Light exercise/sports 1-3 days/week
                activityFactor = 1.375;
                break;
            case "moderately active": // Moderate exercise/sports 3-5 days/week
                activityFactor = 1.55;
                break;
            case "very active": // Hard exercise/sports 6-7 days/week
                activityFactor = 1.725;
                break;
            case "extra active": // Very hard exercise/physical job/training 2x per day
                activityFactor = 1.9;
                break;
            default:
                throw new IllegalArgumentException("Invalid activity level.");
        }
        double tdee = bmr * activityFactor;
        return String.format("%.2f", tdee);
    }

    @WebMethod(operationName = "CaloricGoals")
    public String CaloricGoals(@WebParam(name = "tdee") float tdee, @WebParam(name = "goalsCategory") String goalsCategory) {
        //TODO write your implementation code here:
        //Input Validation
        if (tdee <= 0) {
            return "TDEE must be a positive value.";
        }

        if (goalsCategory == null || goalsCategory.trim().isEmpty()) {
            return "Goals category is required.";
        }
    
        double caloriesPerDay;
        switch (goalsCategory.toLowerCase()) {
            case "maintain": 
                caloriesPerDay = tdee;
                break;
            case "mild weight loss": 
                caloriesPerDay = tdee - 275;
                break;
            case "weight loss": 
                caloriesPerDay = tdee - 550;
                break;
            case "extreme weight loss": 
                caloriesPerDay = tdee - 1100;
                break;
            case "mild weight gain": 
                caloriesPerDay = tdee + 275;
                break;
            case "weight gain": 
                caloriesPerDay = tdee + 550;
                break;
            case "extreme weight gain": 
                caloriesPerDay = tdee + 1100;
                break;
            default:
                throw new IllegalArgumentException("Invalid Goal Category.");
        }
        return String.format("%.2f", caloriesPerDay);
    }

    @WebMethod(operationName = "MacroDistribution")
    public String MacroDistribution(@WebParam(name = "calories") float calories, @WebParam(name = "goal") String goal) {
        //TODO write your implementation code here:
        //Input Validation 
        if (calories <= 0) {
            return "Calories must be a positive value.";
        }

        if (goal == null || goal.trim().isEmpty()) {
            return "Goal is required.";
        }
        
        float proteinPercent, fatPercent, carbPercent;
        switch (goal.toLowerCase()) {
            case "maintain":
            case "balanced":
                proteinPercent = 0.30f;
                fatPercent = 0.25f;
                carbPercent = 0.45f;
                break;
            case "weight gain":
            case "mild weight gain":
            case "extreme weight gain":
                proteinPercent = 0.25f;
                fatPercent = 0.20f;
                carbPercent = 0.55f;
                break;
            case "weight loss":
            case "mild weight loss":
            case "extreme weight loss":
                proteinPercent = 0.35f;
                fatPercent = 0.25f;
                carbPercent = 0.40f;
                break;
            default:
                return "Invalid macro type. Use: balanced, high protein, low carb, etc.";
        }

        float proteinGrams = (calories * proteinPercent) / 4; // 4 kcal/g
        float fatGrams = (calories * fatPercent) / 9;         // 9 kcal/g
        float carbGrams = (calories * carbPercent) / 4;       // 4 kcal/g

        return String.format("Protein: %.1fg, Fat: %.1fg, Carbs: %.1fg", proteinGrams, fatGrams, carbGrams);

    }


   
}
