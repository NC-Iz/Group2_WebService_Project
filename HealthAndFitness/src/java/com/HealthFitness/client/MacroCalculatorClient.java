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

/**
 *
 * @author User
 */
public class MacroCalculatorClient extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/HealthAndFitness/MacroCalculatorService.wsdl")
    private MacroCalculatorService_Service service;

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
        try (PrintWriter out = response.getWriter()) {

            // Get form parameters
            String gender = request.getParameter("gender");
            float weight = Float.parseFloat(request.getParameter("weight"));
            float height = Float.parseFloat(request.getParameter("height"));
            int age = Integer.parseInt(request.getParameter("age"));
            String activity = request.getParameter("activity");
            String goal = request.getParameter("goal");

            // Call Web Service
            String bmrStr = basalMetabolicRate(weight, height, age, gender);
            float bmr = Float.parseFloat(bmrStr);

            String tdeeStr = totalDailyEnergyExpenditure(bmr, activity);
            float tdee = Float.parseFloat(tdeeStr);

            String goalCaloriesStr = caloricGoals(tdee, goal);

            String macroStr = macroDistribution(Float.parseFloat(goalCaloriesStr), goal);

            //Display Results
            out.println("<html><head><title>Macro Calculator Results</title></head><body>");
            out.println("<h2>Macro Calculator Results</h2>");
            out.println("<p><strong>Basal Metabolic Rate (BMR):</strong> " + bmrStr + " kcal/day</p>");
            out.println("<p><strong>Total Daily Energy Expenditure (TDEE):</strong> " + tdeeStr + " kcal/day</p>");
            out.println("<p><strong>Calories for Goal:</strong> " + goalCaloriesStr + " kcal/day</p>");
            out.println("<p><strong>Macronutrient Breakdown:</strong> " + macroStr + "</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            // Basic error handling
            try (PrintWriter out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<h3>Error: " + e.getMessage() + "</h3>");
                out.println("</body></html>");
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

    private String basalMetabolicRate(float weightKG, float heightCM, int ageYears, java.lang.String gender) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.HealthFitness.client.MacroCalculatorService port = service.getMacroCalculatorServicePort();
        return port.basalMetabolicRate(weightKG, heightCM, ageYears, gender);
    }

    private String caloricGoals(float tdee, java.lang.String goalsCategory) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.HealthFitness.client.MacroCalculatorService port = service.getMacroCalculatorServicePort();
        return port.caloricGoals(tdee, goalsCategory);
    }

    private String macroDistribution(float calories, java.lang.String goal) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.HealthFitness.client.MacroCalculatorService port = service.getMacroCalculatorServicePort();
        return port.macroDistribution(calories, goal);
    }

    private String totalDailyEnergyExpenditure(float bmr, java.lang.String activityLevel) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.HealthFitness.client.MacroCalculatorService port = service.getMacroCalculatorServicePort();
        return port.totalDailyEnergyExpenditure(bmr, activityLevel);
    }

}
