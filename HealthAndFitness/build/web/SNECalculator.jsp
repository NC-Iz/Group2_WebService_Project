<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    String name = (String) session.getAttribute("name");
    Integer age = (Integer) session.getAttribute("age");
    String ageRange = "";
    String ic = (String) session.getAttribute("ic");
    String gender = (String) session.getAttribute("gender");
    Double weight = (Double) session.getAttribute("weight");
    Double height = (Double) session.getAttribute("height");

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
    
    String sneResult = (String) request.getAttribute("sneResult");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sleep Need Estimator</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- MathJax for formula rendering -->
    <script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
    <script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
    <style>
        .calculator-card {
            max-width: 800px;
            margin: 0 auto;
        }
        .sleep-range-card {
            background-color: #f8f9fa;
            border-left: 4px solid #3498db;
        }
        .time-suggestion {
            font-size: 1.1rem;
            font-weight: 500;
        }
        .recommended-range {
            font-weight: 600;
            color: #2c3e50;
        }
    </style>
</head>
<body class="bg-light">
    <div class="container py-5">
        <div class="card calculator-card">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">Sleep Need Estimator</h2>
            </div>
            <div class="card-body">
                <form action="HealthFitnessClient" method="post">
                    <input type="hidden" name="formType" value="sne" />

                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="name" class="form-label">Name:</label>
                                <input type="text" class="form-control" id="name" name="name" 
                                       value="<%= name != null ? name : "" %>" readonly>
                            </div>
                        </div>
                        <div class="col-md-6">
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
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Sleep Schedule Type</label><br>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="scheduleType" value="wakeUp" id="wakeUp" checked>
                            <label class="form-check-label" for="wakeUp">I want to wake up at...</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="scheduleType" value="goToBed" id="goToBed">
                            <label class="form-check-label" for="goToBed">I want to go to bed at...</label>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="timeInput" class="form-label">Time</label>
                        <input type="time" class="form-control" id="timeInput" name="timeInput" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Estimate Sleep Times</button>
                </form>
            </div>
        </div>

        <%-- Result Display Section --%>
        <div class="mt-4">
            <%
                if (error != null) {
            %>
                <div class="alert alert-danger"><%= error %></div>
            <%
                } else if (sneResult != null) {
            %>
                <%= sneResult %>              
            <%
                }
            %>
            
            
            <div class="card mt-4">
                    <div class="card-header bg-secondary text-white">
                        <h5 class="mb-0">User Information</h5>
                    </div>
                    <div class="card-body">
                        <p><strong>Name:</strong> <%= name != null ? name : "N/A" %></p>
                        <p><strong>ID:</strong> <%= ic != null ? ic : "N/A" %></p>
                        <p><strong>Gender:</strong> <%= gender != null ? gender : "N/A" %></p>
                        <p><strong>Age:</strong> <%= age != null ? age : "N/A" %></p>
                        <p><strong>Weight:</strong> <%= weight != null ? weight + " kg" : "N/A" %></p>
                        <p><strong>Height:</strong> <%= height != null ? height + " cm" : "N/A" %></p>
                    </div>
                </div>
                    
            <%-- Sleep Information Section --%>
            <div class="card mt-4">
                <div class="card-body">
                    <h4 class="card-title">About Sleep Needs</h4>
                    <p class="card-text">Getting enough quality sleep at the right times helps protect your mental health, physical health, quality of life, and safety. The way you feel while you're awake depends in part on what happens while you're sleeping.</p>
                    
                    <h5 class="mt-4">Recommended Sleep Duration by Age</h5>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead class="table-light">
                                <tr>
                                    <th>Age Range</th>
                                    <th>Recommended Hours of Sleep</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>0-3 months</td>
                                    <td>14-17 hours</td>
                                </tr>
                                <tr>
                                    <td>4-12 months</td>
                                    <td>12-16 hours</td>
                                </tr>
                                <tr>
                                    <td>1-2 years</td>
                                    <td>11-14 hours</td>
                                </tr>
                                <tr>
                                    <td>3-5 years</td>
                                    <td>10-13 hours</td>
                                </tr>
                                <tr>
                                    <td>6-12 years</td>
                                    <td>9-12 hours</td>
                                </tr>
                                <tr>
                                    <td>13-18 years</td>
                                    <td>8-10 hours</td>
                                </tr>
                                <tr>
                                    <td>18-60 years</td>
                                    <td>7-9 hours</td>
                                </tr>
                                <tr>
                                    <td>61-64 years</td>
                                    <td>7-9 hours</td>
                                </tr>
                                <tr>
                                    <td>65+ years</td>
                                    <td>7-8 hours</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    
                    <h5 class="mt-4">Sleep Tips</h5>
                    <div class="alert alert-info">
                        <ul class="mb-0">
                            <li>Stick to a sleep schedule, even on weekends</li>
                            <li>Practice a relaxing bedtime ritual</li>
                            <li>Exercise daily</li>
                            <li>Evaluate your bedroom environment</li>
                            <li>Sleep on a comfortable mattress and pillows</li>
                            <li>Avoid alcohol, cigarettes, and heavy meals before bedtime</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>