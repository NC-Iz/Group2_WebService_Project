/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HealthFitness.client;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import javax.servlet.http.HttpSession;


/**
 *
 * @author faiza
 */
public class HealthFitnessClient extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/HealthAndFitness/HealthAndFitnessService.wsdl")
    private HealthAndFitnessService service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
        String formType = request.getParameter("formType");
        String result = "";
        
        if ("userinfo".equalsIgnoreCase(formType)) {
            // This handles what your UserInfoServlet did:
            String name = request.getParameter("name");
            String ic = request.getParameter("ic");
            String gender = request.getParameter("gender");
            double weight = Double.parseDouble(request.getParameter("weight"));
            double height = Double.parseDouble(request.getParameter("height"));
            int age = calculateAgeFromIC(ic);

            session.setAttribute("name", name);
            session.setAttribute("gender", gender);
            session.setAttribute("age", age);
            session.setAttribute("ic", ic);
            session.setAttribute("weight", weight);
            session.setAttribute("height", height);

            // Redirect or show confirmation page
            response.sendRedirect("index.html");
            return;  // Stop further processing
        }

        // For other forms, fallback to session attributes if params missing
        String userName = (String) session.getAttribute("name");
        String userGender = (String) session.getAttribute("gender");
        Integer userAge = (Integer) session.getAttribute("age");

        switch (formType) {
            
            case "bmi":
                try {
            String nameBMI = request.getParameter("name");
            double weight = Double.parseDouble(request.getParameter("weight"));
            double height = Double.parseDouble(request.getParameter("height"));
            
            // Call the web service
            result = calculateBMI(nameBMI, weight, height);
            
            // Store values in request attributes for the JSP
            request.setAttribute("bmiResult", result);
            request.setAttribute("userName", nameBMI);
            
            // Also store in session for form repopulation
            session.setAttribute("weight", weight);
            session.setAttribute("height", height);
            
            // Forward back to the JSP
            request.getRequestDispatcher("BMICalculator.jsp").forward(request, response);
            return; // Important to return after forward
        } catch (Exception e) {
            request.setAttribute("error", "Error calculating BMI: " + e.getMessage());
            request.getRequestDispatcher("BMICalculator.jsp").forward(request, response);
            return;
        }
                
            case "bfp":
                try {
                    String gender = request.getParameter("gender");
                    int age = Integer.parseInt(request.getParameter("age"));
                    double weightBFP = Double.parseDouble(request.getParameter("weight"));
                    double heightBFP = Double.parseDouble(request.getParameter("height"));
                    double waist = Double.parseDouble(request.getParameter("waist"));
                    double neck = Double.parseDouble(request.getParameter("neck"));
                    double hip = gender.equalsIgnoreCase("female") ? Double.parseDouble(request.getParameter("hip")) : 0;

                    result = calculateBFPLocal(gender, age, weightBFP, heightBFP, waist, neck, hip);

                    // Store to session for repopulation if needed
                    session.setAttribute("gender", gender);
                    session.setAttribute("age", age);
                    session.setAttribute("weight", weightBFP);
                    session.setAttribute("height", heightBFP);

                    // Pass result back to JSP
                    request.setAttribute("bfpResult", result);
                    request.getRequestDispatcher("BFPCalculator.jsp").forward(request, response);
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error calculating BFP: " + e.getMessage());
                    request.getRequestDispatcher("BFPCalculator.jsp").forward(request, response);
                    return;
                }

            case "cbr":
                // Updated part to include intensity
                    String nameCal = request.getParameter("name");
                    double weightCal = Double.parseDouble(request.getParameter("weight"));
                    String activity = request.getParameter("activity");
                    int duration = Integer.parseInt(request.getParameter("duration"));
                    String intensity = request.getParameter("intensity");

                    result = calculateCaloriesBurned(nameCal, weightCal, activity, duration, intensity);

                    // Store to session for repopulation if needed
                    session.setAttribute("name", nameCal);
                    session.setAttribute("weight", weightCal);

                    // Pass result back to JSP
                    request.setAttribute("cbrResult", result);
                    request.getRequestDispatcher("CBRCalculator.jsp").forward(request, response);
                    return;
                

            case "sne":
                try {
                    String ageRange = request.getParameter("ageRange");
                    String scheduleType = request.getParameter("scheduleType");
                    String timeInput = request.getParameter("timeInput");

                    result = calculateSleepTimes(ageRange, scheduleType, timeInput);

                    // Pass result back to JSP
                    request.setAttribute("sneResult", result);
                    request.getRequestDispatcher("SNECalculator.jsp").forward(request, response);
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error calculating sleep times: " + e.getMessage());
                    request.getRequestDispatcher("SNECalculator.jsp").forward(request, response);
                    return;
                }

            default:
                result = "Invalid form type.";
        }
    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    // Add this at the top, inside your HealthFitnessClient class:

    private int calculateAgeFromIC(String ic) {
    try {
        // Remove all non-digit characters
        String cleanedIC = ic.replaceAll("\\D", "");
        if (cleanedIC.length() < 6) return 0; // invalid

        String yearStr = cleanedIC.substring(0, 2);
        String month = cleanedIC.substring(2, 4);
        String day = cleanedIC.substring(4, 6);

        int year = Integer.parseInt(yearStr);
        int fullYear = (year <= 25) ? 2000 + year : 1900 + year;

        java.time.LocalDate birthDate = java.time.LocalDate.of(fullYear, Integer.parseInt(month), Integer.parseInt(day));
        return java.time.Period.between(birthDate, java.time.LocalDate.now()).getYears();
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}



    private String calculateBFP(java.lang.String arg0, int arg1, double arg2, double arg3, double arg4, double arg5, double arg6) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.HealthFitness.client.HealthFitnessService port = service.getHealthFitnessServicePort();
        return port.calculateBFP(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    private String calculateBMI(java.lang.String name, double weight, double heightCm) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.HealthFitness.client.HealthFitnessService port = service.getHealthFitnessServicePort();
        return port.calculateBMI(name, weight, heightCm);
    }

    private String calculateCaloriesBurned(java.lang.String name, double weight, java.lang.String activity, int durationMinutes, java.lang.String intensity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.HealthFitness.client.HealthFitnessService port = service.getHealthFitnessServicePort();
        return port.calculateCaloriesBurned(name, weight, activity, durationMinutes, intensity);
    }

    private String calculateSleepTimes(java.lang.String ageRange, java.lang.String scheduleType, java.lang.String timeInput) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.HealthFitness.client.HealthFitnessService port = service.getHealthFitnessServicePort();
        return port.calculateSleepTimes(ageRange, scheduleType, timeInput);
    }
    
    private String calculateBFPLocal(String gender, int age, double weight, double height, double waist, double neck, double hip) {
    // Convert to inches for USC method
    double heightInches = height / 2.54;
    double waistInches = waist / 2.54;
    double neckInches = neck / 2.54;
    double hipInches = hip / 2.54;

    // Calculate BMI
    double heightM = height / 100.0;
    double bmi = weight / (heightM * heightM);

    // Calculate BFP using all three methods
    double bfpBMI = calculateBFPWithBMI(gender, age, bmi);
    double bfpUSC = calculateBFPWithUSC(gender, waistInches, neckInches, hipInches, heightInches);
    double bfpSI = calculateBFPWithSI(gender, waist, neck, hip, height);

    // Average body fat
    double avgBFP = (bfpBMI + bfpUSC + bfpSI) / 3.0;
    double fatMass = (avgBFP / 100.0) * weight;
    double leanMass = weight - fatMass;

    // Categories
    String categoryBMI = getBodyFatCategory(gender, bfpBMI);
    String categoryUSC = getBodyFatCategory(gender, bfpUSC);
    String categorySI = getBodyFatCategory(gender, bfpSI);

    // Build formatted HTML result string
    StringBuilder sb = new StringBuilder();
    sb.append("<h1>Body Fat Percentage Results</h1>");
    sb.append("<p>BMI Method: ").append(String.format("%.1f", bfpBMI)).append("% (").append(categoryBMI).append(")</p>");
    sb.append("<p>US Navy (USC): ").append(String.format("%.1f", bfpUSC)).append("% (").append(categoryUSC).append(")</p>");
    sb.append("<p>US Navy (SI): ").append(String.format("%.1f", bfpSI)).append("% (").append(categorySI).append(")</p>");
    sb.append("<p>Average Body Fat: ").append(String.format("%.1f", avgBFP)).append("%</p>");
    sb.append("<p>Body Fat Mass: ").append(String.format("%.1f", fatMass)).append(" kg</p>");
    sb.append("<p>Lean Body Mass: ").append(String.format("%.1f", leanMass)).append(" kg</p>");

    return sb.toString();
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
}
//try test