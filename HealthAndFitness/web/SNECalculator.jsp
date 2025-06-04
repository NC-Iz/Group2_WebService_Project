<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    Integer age = (Integer) session.getAttribute("age");
    String ageRange = "";

    if (age != null) {
        if (age <= 0) ageRange = "0-3 months";
        else if (age <= 1) ageRange = "4-12 months";
        else if (age <= 2) ageRange = "1-2 years";
        else if (age <= 5) ageRange = "3-5 years";
        else if (age <= 12) ageRange = "6-12 years";
        else if (age <= 18) ageRange = "13-18 years";
        else if (age <= 60) ageRange = "18-60 years";
        else if (age <= 64) ageRange = "61-64 years";
        else ageRange = "65+ years";
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sleep Need Estimator</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .calculator-card {
            max-width: 600px;
            margin: 2rem auto;
        }
    </style>
</head>
<body class="bg-light">
    <div class="container py-5">
        <div class="card calculator-card shadow">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0 text-center">Sleep Need Estimator</h2>
            </div>
            <div class="card-body">
                <form action="HealthFitnessClient" method="post">
                    <input type="hidden" name="formType" value="sne" />

                    <div class="mb-3">
                        <label for="ageRange" class="form-label">Age Range</label>
                        <select class="form-select" id="ageRange" name="ageRange" required>
                            <option value="0-3 months" <%= "0-3 months".equals(ageRange) ? "selected" : "" %>>0–3 Months</option>
                            <option value="4-12 months" <%= "4-12 months".equals(ageRange) ? "selected" : "" %>>4–12 Months</option>
                            <option value="1-2 years" <%= "1-2 years".equals(ageRange) ? "selected" : "" %>>1–2 Years</option>
                            <option value="3-5 years" <%= "3-5 years".equals(ageRange) ? "selected" : "" %>>3–5 Years</option>
                            <option value="6-12 years" <%= "6-12 years".equals(ageRange) ? "selected" : "" %>>6–12 Years</option>
                            <option value="13-18 years" <%= "13-18 years".equals(ageRange) ? "selected" : "" %>>13–18 Years</option>
                            <option value="18-60 years" <%= "18-60 years".equals(ageRange) ? "selected" : "" %>>18–60 Years</option>
                            <option value="61-64 years" <%= "61-64 years".equals(ageRange) ? "selected" : "" %>>61–64 Years</option>
                            <option value="65+ years" <%= "65+ years".equals(ageRange) ? "selected" : "" %>>65+ Years</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Sleep Schedule Type</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="scheduleType" value="wakeUp" id="wakeUp" checked>
                            <label class="form-check-label" for="wakeUp">I want to wake up at...</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="scheduleType" value="goToBed" id="goToBed">
                            <label class="form-check-label" for="goToBed">I want to go to bed at...</label>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="timeInput" class="form-label">Time</label>
                        <input type="time" class="form-control" id="timeInput" name="timeInput" required>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary btn-lg">Estimate Sleep</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
