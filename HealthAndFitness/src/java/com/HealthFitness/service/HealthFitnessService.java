/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HealthFitness.service;

import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author faiza
 */
@WebService(serviceName = "HealthAndFitnessService")
public class HealthFitnessService {
    
    private static final Map<String, int[]> ageSleepMap = new HashMap<>();

    static {
        // Updated to exactly match the table with correct hours
        ageSleepMap.put("0-3 months", new int[]{14, 17});
        ageSleepMap.put("4-12 months", new int[]{12, 16});
        ageSleepMap.put("1-2 years", new int[]{11, 14});
        ageSleepMap.put("3-5 years", new int[]{10, 13});
        ageSleepMap.put("6-12 years", new int[]{9, 12});
        ageSleepMap.put("13-18 years", new int[]{8, 10});
        ageSleepMap.put("18-60 years", new int[]{7, 9});
        ageSleepMap.put("61-64 years", new int[]{7, 9});
        ageSleepMap.put("65+ years", new int[]{7, 8});
    }

    @WebMethod(operationName = "calculateBMI")
    public String calculateBMI(
            @WebParam(name = "name") String name,
            @WebParam(name = "weight") double weight,   // in kg
            @WebParam(name = "heightCm") double heightCm) { // in cm

        // Input validation
        if (heightCm <= 0 || weight <= 0) {
            return "SOAP Fault: Invalid input. Height and Weight must be positive numbers.";
        }

        // Convert cm to meters
        double heightInMeters = heightCm / 100.0;

        // Calculate BMI
        double bmi = weight / (heightInMeters * heightInMeters);
        String category;

        if (bmi < 16) {
            category = "Severe Thinness";
        } else if (bmi < 17) {
            category = "Moderate Thinness";
        } else if (bmi < 18.5) {
            category = "Mild Thinness";
        } else if (bmi < 25) {
            category = "Normal";
        } else if (bmi < 30) {
            category = "Overweight";
        } else if (bmi < 35) {
            category = "Obese Class I";
        } else if (bmi < 40) {
            category = "Obese Class II";
        } else {
            category = "Obese Class III";
        }

        return "Hello " + name + ", your BMI is " + String.format("%.2f", bmi) +
               " which is considered: " + category;
    }
    
    @WebMethod(operationName = "calculateCaloriesBurned")
public String calculateCaloriesBurned(
        @WebParam(name = "name") String name,
        @WebParam(name = "weight") double weight,
        @WebParam(name = "activity") String activity,
        @WebParam(name = "durationMinutes") int durationMinutes,
        @WebParam(name = "intensity") String intensity) {

    // Input validation
    if (weight <= 0 || durationMinutes <= 0 || 
        activity == null || activity.isEmpty() ||
        intensity == null || intensity.isEmpty()) {
        return "SOAP Fault: Invalid input. Please provide valid weight, duration, activity, and intensity (low/moderate/high).";
    }

    double met;
    switch (activity.toLowerCase()) {
        case "walking":
            switch (intensity.toLowerCase()) {
                case "low":      met = 2.5; break;
                case "moderate": met = 3.5; break;
                case "high":     met = 5.0; break;
                default:         return "SOAP Fault: Invalid intensity for walking. Use low/moderate/high.";
            }
            break;
            
        case "running":
            switch (intensity.toLowerCase()) {
                case "low":      met = 6.0; break;
                case "moderate": met = 8.5; break;
                case "high":     met = 12.0; break;
                default:        return "SOAP Fault: Invalid intensity for running. Use low/moderate/high.";
            }
            break;
            
        case "cycling":
            switch (intensity.toLowerCase()) {
                case "low":      met = 4.0; break;
                case "moderate": met = 6.0; break;
                case "high":     met = 10.0; break;
                default:         return "SOAP Fault: Invalid intensity for cycling. Use low/moderate/high.";
            }
            break;
            
        case "swimming":
            switch (intensity.toLowerCase()) {
                case "low":      met = 5.0; break;
                case "moderate": met = 7.0; break;
                case "high":     met = 10.0; break;
                default:        return "SOAP Fault: Invalid intensity for swimming. Use low/moderate/high.";
            }
            break;
            
        case "weightlifting":
            switch (intensity.toLowerCase()) {
                case "low":      met = 3.0; break;
                case "moderate": met = 4.5; break;
                case "high":     met = 6.0; break;
                default:        return "SOAP Fault: Invalid intensity for weightlifting. Use low/moderate/high.";
            }
            break;
            
        default:
            return "SOAP Fault: Unsupported activity. Supported activities: walking, running, cycling, swimming, weightlifting.";
    }

    // Calculate calories (using standard MET formula)
    double caloriesBurned = met * weight * (durationMinutes / 60.0);

    return String.format(
        "%s burned %.2f calories during %d minutes of %s (%s intensity).",
        name, caloriesBurned, durationMinutes, activity.toLowerCase(), intensity.toLowerCase()
    );
}

    @WebMethod
    public String calculateBFP(String gender, int age, double weight, double height, 
                             double waist, double neck, double hip) {
        try {
            // Calculate BMI
            double heightM = height / 100;
            double bmi = weight / (heightM * heightM);
            
            // Calculate all methods
            double bfpBMI = calculateBFPWithBMI(gender, age, bmi);
            double bfpUSC = calculateBFPWithUSC(gender, waist, neck, hip, height);
            double bfpSI = calculateBFPWithSI(gender, waist, neck, hip, height);
            
            // Calculate fat and lean mass
            double avgBFP = (bfpBMI + bfpUSC + bfpSI) / 3;
            double fatMass = (avgBFP / 100) * weight;
            double leanMass = weight - fatMass;
            
            // Get categories
            String categoryBMI = getBodyFatCategory(gender, bfpBMI);
            String categoryUSC = getBodyFatCategory(gender, bfpUSC);
            String categorySI = getBodyFatCategory(gender, bfpSI);
            
            return String.format(
                "BMI Method: %.2f%% (%s)\n" +
                "US Navy (USC): %.2f%% (%s)\n" +
                "US Navy (SI): %.2f%% (%s)\n\n" +
                "Average Body Fat: %.2f%%\n" +
                "Fat Mass: %.2f kg\n" +
                "Lean Mass: %.2f kg",
                bfpBMI, categoryBMI,
                bfpUSC, categoryUSC,
                bfpSI, categorySI,
                avgBFP, fatMass, leanMass);
                
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    private double calculateBFPWithBMI(String gender, int age, double bmi) {
        if (gender.equalsIgnoreCase("male")) {
            return (1.20 * bmi) + (0.23 * age) - 16.2;
        } else {
            return (1.20 * bmi) + (0.23 * age) - 5.4;
        }
    }
    
    private double calculateBFPWithUSC(String gender, double waist, double neck, double hip, double height) {
        if (gender.equalsIgnoreCase("male")) {
            double abdomenNeck = waist - neck;
            return 86.010 * Math.log10(abdomenNeck) - 70.041 * Math.log10(height) + 36.76;
        } else {
            double waistHipNeck = waist + hip - neck;
            return 163.205 * Math.log10(waistHipNeck) - 97.684 * Math.log10(height) - 78.387;
        }
    }
    
    private double calculateBFPWithSI(String gender, double waist, double neck, double hip, double height) {
        if (gender.equalsIgnoreCase("male")) {
            double waistNeck = waist - neck;
            return (495 / (1.0324 - 0.19077 * Math.log10(waistNeck) + 0.15456 * Math.log10(height))) - 450;
        } else {
            double waistHipNeck = waist + hip - neck;
            return (495 / (1.29579 - 0.35004 * Math.log10(waistHipNeck) + 0.22100 * Math.log10(height))) - 450;
        }
    }
    
    private String getBodyFatCategory(String gender, double bfp) {
        if (gender.equalsIgnoreCase("male")) {
            if (bfp < 5) return "Essential fat";
            if (bfp <= 13) return "Athletes";
            if (bfp <= 17) return "Fitness";
            if (bfp <= 24) return "Average";
            return "Obese";
        } else {
            if (bfp < 10) return "Essential fat";
            if (bfp <= 20) return "Athletes";
            if (bfp <= 24) return "Fitness";
            if (bfp <= 31) return "Average";
            return "Obese";
        }
    }
    
    @WebMethod(operationName = "calculateSleepTimes")
public String calculateSleepTimes(@WebParam(name = "ageRange") String ageRange,
                                @WebParam(name = "scheduleType") String scheduleType,
                                @WebParam(name = "timeInput") String timeInput) {

    StringBuilder result = new StringBuilder();
    result.append("<section class='sleep-results' style='font-family: Arial, sans-serif; margin-top: 20px;'>");
    result.append("<div style='border: 1px solid #ccc; border-radius: 10px; overflow: hidden;'>");
    result.append("<header style='background-color: #007bff; color: white; padding: 15px;'>");
    result.append("<h3 style='margin: 0;'>Sleep Calculation Results</h3>");
    result.append("</header>");
    result.append("<div style='padding: 20px;'>");
    result.append("<p style='color: #6c757d;'>It typically takes 15 minutes to fall asleep.</p>");

    int[] range = ageSleepMap.getOrDefault(ageRange, new int[]{7, 9});
    int minSleep = range[0];
    int maxSleep = range[1];

    String[] timeParts = timeInput.split(":");
    int hours = Integer.parseInt(timeParts[0]);
    int minutes = Integer.parseInt(timeParts[1]);
    int inputTotalMinutes = hours * 60 + minutes;

    if (scheduleType.equalsIgnoreCase("wakeUp")) {
        result.append("<h4 style='margin-top: 0;'>If you want to wake up at <strong>")
              .append(formatTime24to12(timeInput))
              .append("</strong>, aim to fall asleep at:</h4>");

        int earliestBedtime = inputTotalMinutes - (maxSleep * 60) - 15;
        int latestBedtime = inputTotalMinutes - (minSleep * 60) - 15;

        result.append("<ul style='list-style: none; padding-left: 0;'>");
        result.append("<li style='margin-bottom: 8px;'>🕒 <strong>").append(maxSleep).append(" hrs sleep</strong>: ")
              .append("<span style='color: #333;'>").append(formatTime(earliestBedtime)).append("</span></li>");
        result.append("<li style='margin-bottom: 8px;'>🕒 <strong>").append(minSleep).append(" hrs sleep</strong>: ")
              .append("<span style='color: #333;'>").append(formatTime(latestBedtime)).append("</span></li>");
        result.append("</ul>");

        int optimalSleep = (maxSleep + minSleep) / 2;
        int optimalBedtime = inputTotalMinutes - (optimalSleep * 60) - 15;
        result.append("<p><strong>✅ Optimal bedtime (").append(optimalSleep).append(" hrs):</strong> ")
              .append("<span style='color: #007bff;'>").append(formatTime(optimalBedtime)).append("</span></p>");

    } else {
        result.append("<h4 style='margin-top: 0;'>If you go to bed at <strong>")
              .append(formatTime24to12(timeInput))
              .append("</strong>, aim to wake up at:</h4>");

        int earliestWakeTime = inputTotalMinutes + (minSleep * 60) + 15;
        int latestWakeTime = inputTotalMinutes + (maxSleep * 60) + 15;

        result.append("<ul style='list-style: none; padding-left: 0;'>");
        result.append("<li style='margin-bottom: 8px;'>🕒 <strong>").append(minSleep).append(" hrs sleep</strong>: ")
              .append("<span style='color: #333;'>").append(formatTime(earliestWakeTime)).append("</span></li>");
        result.append("<li style='margin-bottom: 8px;'>🕒 <strong>").append(maxSleep).append(" hrs sleep</strong>: ")
              .append("<span style='color: #333;'>").append(formatTime(latestWakeTime)).append("</span></li>");
        result.append("</ul>");

        int optimalSleep = (maxSleep + minSleep) / 2;
        int optimalWakeTime = inputTotalMinutes + (optimalSleep * 60) + 15;
        result.append("<p><strong>✅ Optimal wake time (").append(optimalSleep).append(" hrs):</strong> ")
              .append("<span style='color: #007bff;'>").append(formatTime(optimalWakeTime)).append("</span></p>");
    }

    result.append("<div style='margin-top: 30px; border-top: 1px solid #eee; padding-top: 15px;'>");
    result.append("<h5>📌 Recommended Sleep Range for ").append(ageRange).append(":</h5>");
    result.append("<p style='margin: 0; font-weight: bold;'>").append(minSleep).append(" to ").append(maxSleep).append(" hours per day</p>");
    result.append("</div>");

    result.append("</div></div></section>");
    return result.toString();
}


    private String formatTime(int totalMinutes) {
        totalMinutes = (totalMinutes + 1440) % 1440;
        int hours = totalMinutes / 60;
        int mins = totalMinutes % 60;
        String period = (hours >= 12) ? "PM" : "AM";
        hours %= 12;
        if (hours == 0) hours = 12;
        return String.format("%02d:%02d %s", hours, mins, period);
    }

    private String formatTime24to12(String time24) {
        String[] parts = time24.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        String period = (hours >= 12) ? "PM" : "AM";
        hours %= 12;
        if (hours == 0) hours = 12;
        return String.format("%02d:%02d %s", hours, minutes, period);
    }
}
    


