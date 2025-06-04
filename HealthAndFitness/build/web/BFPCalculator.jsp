<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%
    String gender = (String) session.getAttribute("gender");
    Integer age = (Integer) session.getAttribute("age");
    Double weight = (Double) session.getAttribute("weight");
    Double height = (Double) session.getAttribute("height");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Body Fat Percentage Calculator</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container py-5">
        <div class="card mx-auto" style="max-width: 600px;">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">Body Fat Percentage Calculator</h2>
            </div>
            <div class="card-body">
                <form action="HealthFitnessClient" method="post">
                    <input type="hidden" name="formType" value="bfp">

                    <!-- Gender -->
                    <div class="mb-3">
                        <label class="form-label">Gender:</label><br>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="genderMale" value="male" required
                                   <%= "male".equalsIgnoreCase(gender) ? "checked" : "" %>>
                            <label class="form-check-label" for="genderMale">Male</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="female" required
                                   <%= "female".equalsIgnoreCase(gender) ? "checked" : "" %>>
                            <label class="form-check-label" for="genderFemale">Female</label>
                        </div>
                    </div>

                    <!-- Age -->
                    <div class="mb-3">
                        <label for="age" class="form-label">Age (years):</label>
                        <input type="number" class="form-control" id="age" name="age" min="1" max="120" required
                               value="<%= age != null ? age : "" %>">
                    </div>

                    <!-- Weight -->
                    <div class="mb-3">
                        <label for="weight" class="form-label">Weight (kg):</label>
                        <input type="number" class="form-control" id="weight" name="weight" step="0.1" min="1" required
                               value="<%= weight != null ? weight : "" %>">
                    </div>

                    <!-- Height -->
                    <div class="mb-3">
                        <label for="height" class="form-label">Height (cm):</label>
                        <input type="number" class="form-control" id="height" name="height" step="0.1" min="1" required
                               value="<%= height != null ? height : "" %>">
                    </div>

                    <!-- Neck -->
                    <div class="mb-3">
                        <label for="neck" class="form-label">Neck (cm):</label>
                        <input type="number" class="form-control" id="neck" name="neck" step="0.1" min="1" required>
                    </div>

                    <!-- Waist -->
                    <div class="mb-3">
                        <label for="waist" class="form-label">Waist (cm):</label>
                        <input type="number" class="form-control" id="waist" name="waist" step="0.1" min="1" required>
                    </div>

                    <!-- Hip (only for females) -->
                    <div class="mb-3" id="hipGroup" style="display: <%= "female".equalsIgnoreCase(gender) ? "block" : "none" %>;">
                        <label for="hip" class="form-label">Hip (cm):</label>
                        <input type="number" class="form-control" id="hip" name="hip" step="0.1" min="1">
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Calculate Body Fat</button>
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <!-- Dynamic display of hip field -->
    <script>
        document.querySelectorAll('input[name="gender"]').forEach(radio => {
            radio.addEventListener('change', function () {
                const hipGroup = document.getElementById("hipGroup");
                const hipInput = document.getElementById("hip");
                if (this.value === 'female') {
                    hipGroup.style.display = 'block';
                    hipInput.required = true;
                } else {
                    hipGroup.style.display = 'none';
                    hipInput.required = false;
                    hipInput.value = '';
                }
            });
        });
    </script>
</body>
</html>
