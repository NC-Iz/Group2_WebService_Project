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

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.soap.SOAPException;


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
    try {
        SOAPFault fault = SOAPFactory.newInstance().createFault(
            "Invalid input: Height and Weight must be positive.",
            new QName("Client")
        );
        throw new SOAPFaultException(fault);
    } catch (SOAPException e) {
        throw new RuntimeException("SOAP Fault could not be created", e);
    }
}

    // Convert cm to meters
    double heightInMeters = heightCm / 100.0;

    // Calculate BMI
    double bmi = weight / (heightInMeters * heightInMeters);
    String category;
    String suggestion = "";

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

    // Provide suggestion if outside normal BMI
    if (bmi < 18.5) {
        // Suggest minimum weight for normal BMI
        double targetWeight = 18.5 * heightInMeters * heightInMeters;
        suggestion = " You may consider gaining at least " + String.format("%.1f", (targetWeight - weight)) +
                     " kg to reach a normal BMI.";
    } else if (bmi >= 25) {
        // Suggest maximum weight for normal BMI
        double targetWeight = 24.9 * heightInMeters * heightInMeters;
        suggestion = " You may consider losing around " + String.format("%.1f", (weight - targetWeight)) +
                     " kg to reach a normal BMI.";
    } else {
        suggestion = "Great! Your BMI is in the normal range. Maintain your current lifestyle with regular exercise and balanced nutrition.";
    }

    StringBuilder result = new StringBuilder();

    result.append("<section class='bmi-results' style='font-family: Arial, sans-serif; margin-top: 20px;'>");
    result.append("<div style='border: 1px solid #ccc; border-radius: 10px; overflow: hidden;'>");

    // Header
    result.append("<header style='background-color: #28a745; color: white; padding: 15px;'>");
    result.append("<h3 style='margin: 0;'>BMI Calculation Results</h3>");
    result.append("</header>");

    // Body
    result.append("<div style='padding: 20px;'>");
    result.append("<p>Hello <strong>").append(name).append("</strong>, based on your input:</p>");
    result.append("<ul style='list-style: none; padding-left: 0;'>");
    result.append("<li style='margin-bottom: 8px;'>📏 <strong>Height:</strong> ")
          .append(String.format("%.1f", heightCm)).append(" cm</li>");
    result.append("<li style='margin-bottom: 8px;'>⚖️ <strong>Weight:</strong> ")
          .append(String.format("%.1f", weight)).append(" kg</li>");
    result.append("</ul>");

    // BMI Result
    result.append("<p><strong>✅ Your BMI is:</strong> ")
          .append(String.format("%.2f", bmi)).append("</p>");

    result.append("<p><strong>📊 Category:</strong> ")
          .append(category).append("</p>");

    // Suggestion
    result.append("<div style='margin-top: 20px; border-top: 1px solid #eee; padding-top: 15px;'>");
    result.append("<h5>💡 Suggestion:</h5>");
    result.append("<p style='margin: 0; font-style: italic; color: #6c757d;'>").append(suggestion).append("</p>");
    result.append("</div>");

    result.append("</div></div></section>");
    return result.toString();
}

    
    @WebMethod(operationName = "calculateCaloriesBurned")
public String calculateCaloriesBurned(
        @WebParam(name = "name") String name,
        @WebParam(name = "weight") double weight,
        @WebParam(name = "activity") String activity,
        @WebParam(name = "durationMinutes") int durationMinutes,
        @WebParam(name = "intensity") String intensity) {

    try {
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
                    case "low": met = 2.5; break;
                    case "moderate": met = 3.5; break;
                    case "high": met = 5.0; break;
                    default: return "SOAP Fault: Invalid intensity for walking. Use low/moderate/high.";
                }
                break;

            case "running":
                switch (intensity.toLowerCase()) {
                    case "low": met = 6.0; break;
                    case "moderate": met = 8.5; break;
                    case "high": met = 12.0; break;
                    default: return "SOAP Fault: Invalid intensity for running. Use low/moderate/high.";
                }
                break;

            case "cycling":
                switch (intensity.toLowerCase()) {
                    case "low": met = 4.0; break;
                    case "moderate": met = 6.0; break;
                    case "high": met = 10.0; break;
                    default: return "SOAP Fault: Invalid intensity for cycling. Use low/moderate/high.";
                }
                break;

            case "swimming":
                switch (intensity.toLowerCase()) {
                    case "low": met = 5.0; break;
                    case "moderate": met = 7.0; break;
                    case "high": met = 10.0; break;
                    default: return "SOAP Fault: Invalid intensity for swimming. Use low/moderate/high.";
                }
                break;

            case "weightlifting":
                switch (intensity.toLowerCase()) {
                    case "low": met = 3.0; break;
                    case "moderate": met = 4.5; break;
                    case "high": met = 6.0; break;
                    default: return "SOAP Fault: Invalid intensity for weightlifting. Use low/moderate/high.";
                }
                break;

            default:
                return "SOAP Fault: Unsupported activity. Supported activities: walking, running, cycling, swimming, weightlifting.";
        }

        // Calculate calories
        double caloriesBurned = met * weight * (durationMinutes / 60.0);

        // Format result in HTML
        StringBuilder result = new StringBuilder();
        result.append("<section class='calories-results' style='font-family: Arial, sans-serif; margin-top: 20px;'>");
        result.append("<div style='border: 1px solid #ccc; border-radius: 10px; overflow: hidden;'>");

        // Header
        result.append("<header style='background-color: #28a745; color: white; padding: 15px;'>");
        result.append("<h3 style='margin: 0;'>Calories Burned Result</h3>");
        result.append("</header>");

        // Body
        result.append("<div style='padding: 20px;'>");
        result.append("<p style='color: #6c757d;'>Based on your input:</p>");
        result.append("<ul style='list-style: none; padding-left: 0;'>");
        result.append("<li style='margin-bottom: 8px;'>🧍 <strong>Name:</strong> ").append(name).append("</li>");
        result.append("<li style='margin-bottom: 8px;'>⚖️ <strong>Weight:</strong> ").append(String.format("%.1f", weight)).append(" kg</li>");
        result.append("<li style='margin-bottom: 8px;'>🏃 <strong>Activity:</strong> ").append(activity.toLowerCase()).append("</li>");
        result.append("<li style='margin-bottom: 8px;'>📈 <strong>Intensity:</strong> ").append(intensity.toLowerCase()).append("</li>");
        result.append("<li style='margin-bottom: 8px;'>⏱️ <strong>Duration:</strong> ").append(durationMinutes).append(" minutes</li>");
        result.append("</ul>");

        // Output
        result.append("<h4 style='margin-top: 20px;'>🔥 Calories Burned:</h4>");
        result.append("<p><strong>").append(String.format("%.2f", caloriesBurned)).append(" kcal</strong></p>");

        result.append("</div></div></section>");
        return result.toString();

    } catch (Exception e) {
        return "SOAP Fault: " + e.getMessage();
    }
}

    @WebMethod(operationName = "calculateBFP")
    public String calculateBFP(
            @WebParam(name = "gender") String gender,
            @WebParam(name = "age") int age,
            @WebParam(name = "weight") double weight,
            @WebParam(name = "height") double height,
            @WebParam(name = "waist") double waist,
            @WebParam(name = "neck") double neck,
            @WebParam(name = "hip") double hip) {

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

            StringBuilder result = new StringBuilder();
            result.append("<section class='bfp-results' style='font-family: Arial, sans-serif; margin-top: 20px;'>");
            result.append("<div style='border: 1px solid #ccc; border-radius: 10px; overflow: hidden;'>");

            // Header
            result.append("<header style='background-color: #28a745; color: white; padding: 15px;'>");
            result.append("<h3 style='margin: 0;'>Body Fat Percentage (BFP) Results</h3>");
            result.append("</header>");

            // Body
            result.append("<div style='padding: 20px;'>");
            result.append("<p style='color: #6c757d;'>Based on your input:</p>");
            result.append("<ul style='list-style: none; padding-left: 0;'>");
            result.append("<li style='margin-bottom: 8px;'>👤 <strong>Gender:</strong> ").append(gender).append("</li>");
            result.append("<li style='margin-bottom: 8px;'>🎂 <strong>Age:</strong> ").append(age).append(" years</li>");
            result.append("<li style='margin-bottom: 8px;'>📏 <strong>Height:</strong> ").append(String.format("%.1f", height)).append(" cm</li>");
            result.append("<li style='margin-bottom: 8px;'>⚖️ <strong>Weight:</strong> ").append(String.format("%.1f", weight)).append(" kg</li>");
            result.append("<li style='margin-bottom: 8px;'>📐 <strong>Waist:</strong> ").append(String.format("%.1f", waist)).append(" cm</li>");
            result.append("<li style='margin-bottom: 8px;'>📏 <strong>Neck:</strong> ").append(String.format("%.1f", neck)).append(" cm</li>");
            if (gender.equalsIgnoreCase("female")) {
                result.append("<li style='margin-bottom: 8px;'>💃 <strong>Hip:</strong> ").append(String.format("%.1f", hip)).append(" cm</li>");
            }
            result.append("</ul>");

            // Method Results
            result.append("<h4 style='margin-top: 20px;'>📊 BFP by Method:</h4>");
            result.append("<ul style='list-style-type: square; padding-left: 20px;'>");
            result.append("<li><strong>BMI Method:</strong> ").append(String.format("%.2f", bfpBMI)).append("% (").append(categoryBMI).append(")</li>");
            result.append("<li><strong>US Navy (USC):</strong> ").append(String.format("%.2f", bfpUSC)).append("% (").append(categoryUSC).append(")</li>");
            result.append("<li><strong>US Navy (SI):</strong> ").append(String.format("%.2f", bfpSI)).append("% (").append(categorySI).append(")</li>");
            result.append("</ul>");

            // Averages
            result.append("<h4 style='margin-top: 20px;'>🧮 Averages:</h4>");
            result.append("<p><strong>Average Body Fat:</strong> ").append(String.format("%.2f", avgBFP)).append("%</p>");
            result.append("<p><strong>Fat Mass:</strong> ").append(String.format("%.2f", fatMass)).append(" kg</p>");
            result.append("<p><strong>Lean Mass:</strong> ").append(String.format("%.2f", leanMass)).append(" kg</p>");

            result.append("</div></div></section>");
            return result.toString();

        } catch (Exception e) {
            return "SOAP Fault: " + e.getMessage();
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
    result.append("<header style='background-color: #28a745; color: white; padding: 15px;'>");
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
              .append("<span>").append(formatTime(optimalBedtime)).append("</span></p>");

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
              .append("<span>").append(formatTime(optimalWakeTime)).append("</span></p>");
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
    


