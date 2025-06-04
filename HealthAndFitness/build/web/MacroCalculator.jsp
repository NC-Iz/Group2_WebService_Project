<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%
    Integer age = (Integer) session.getAttribute("age");
    String gender = (String) session.getAttribute("gender");
    Double height = (Double) session.getAttribute("height");
    Double weight = (Double) session.getAttribute("weight");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Macro Calculator</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <div class="card-header bg-primary text-white text-center">
                <h2 class="mb-0">Macro Calculator</h2>
            </div>
            <div class="card-body">
                <form action="MacroCalculatorClient" method="post">
                    <div class="mb-3">
                        <label for="age" class="form-label">Age</label>
                        <input type="number" class="form-control" id="age" name="age" value="<%= age != null ? age : "" %>" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Gender</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="male" value="male" <%= "male".equalsIgnoreCase(gender) ? "checked" : "" %>>
                            <label class="form-check-label" for="male">Male</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="female" value="female" <%= "female".equalsIgnoreCase(gender) ? "checked" : "" %>>
                            <label class="form-check-label" for="female">Female</label>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="height" class="form-label">Height (cm)</label>
                        <input type="number" class="form-control" id="height" name="height" step="0.1" min="1" value="<%= height != null ? height : "" %>" required>
                    </div>

                    <div class="mb-3">
                        <label for="weight" class="form-label">Weight (kg)</label>
                        <input type="number" class="form-control" id="weight" name="weight" step="0.1" min="1" value="<%= weight != null ? weight : "" %>" required>
                    </div>

                    <div class="mb-3">
                        <label for="activity" class="form-label">Activity Level</label>
                        <select class="form-select" id="activity" name="activity" required>
                            <option value="sedentary">Sedentary</option>
                            <option value="lightly active">Lightly Active</option>
                            <option value="moderately active">Moderately Active</option>
                            <option value="very active">Very Active</option>
                            <option value="extra active">Extra Active</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <label for="goal" class="form-label">Goal</label>
                        <select class="form-select" id="goal" name="goal" required>
                            <option value="maintain">Maintain</option>
                            <option value="mild weight loss">Mild Weight Loss</option>
                            <option value="weight loss">Weight Loss</option>
                            <option value="extreme weight loss">Extreme Weight Loss</option>
                            <option value="mild weight gain">Mild Weight Gain</option>
                            <option value="weight gain">Weight Gain</option>
                            <option value="extreme weight gain">Extreme Weight Gain</option>
                        </select>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary btn-lg">Calculate Macros</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
