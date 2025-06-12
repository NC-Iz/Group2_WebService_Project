<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%
    String name = (String) session.getAttribute("name");
    String gender = (String) session.getAttribute("gender");
    Integer age = (Integer) session.getAttribute("age");
    Double weight = (Double) session.getAttribute("weight");
    Double height = (Double) session.getAttribute("height");
    String ic = (String) session.getAttribute("ic");

    String bfpResult = (String) request.getAttribute("bfpResult");
    String error = (String) request.getAttribute("error");
    String neck = request.getParameter("neck");
    String waist = request.getParameter("waist");
    String hip = request.getParameter("hip");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Body Fat Percentage Calculator</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- MathJax for formula rendering -->
    <script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
    <script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
    <style>
        .calculator-card {
            max-width: 800px;
            margin: 0 auto;
        }
        .table-hover tbody tr:hover {
            background-color: rgba(0, 0, 0, 0.05);
        }
        
        .method-section {
        box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
    }
    .method-header {
        color: #2c3e50;
        border-bottom: 1px solid #eee;
        padding-bottom: 0.5rem;
        margin-bottom: 1rem;
    }
    .method-description {
        color: #7f8c8d;
        margin-bottom: 1.5rem;
    }
    .formula-box {
        border: 1px solid #dee2e6;
    }
    .formula-title {
        color: #3498db;
        font-weight: 600;
    }
    .math-formula {
        font-size: 1rem;
        overflow-x: auto;
        padding: 0.5rem 0;
    }
    @media (max-width: 768px) {
        .formula-container {
            flex-direction: column;
        }
    }
    </style>
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
          <a class="navbar-brand" href="index.html">Health & Fitness Calculator</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
              <li class="nav-item">
                <a class="nav-link" href="index.html">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="BMICalculator.jsp">BMI</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="BFPCalculator.jsp">Body Fat</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="CBRCalculator.jsp">Calories Burned</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="SNECalculator.jsp">Sleep Need Estimator</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="MacroCalculator.jsp">Macro</a>
              </li>
            </ul>
          </div>
        </div>
    </nav>
    <div class="container py-5">
        <div class="card calculator-card">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">Body Fat Percentage Calculator</h2>
            </div>
            <div class="card-body">
                <form action="HealthFitnessClient" method="post">
                    <input type="hidden" name="formType" value="bfp">

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
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label for="age" class="form-label">Age (years):</label>
                                <input type="number" class="form-control" id="age" name="age" min="1" max="120" required
                                       value="<%= age != null ? age : "" %>">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label for="weight" class="form-label">Weight (kg):</label>
                                <input type="number" class="form-control" id="weight" name="weight" step="0.1" min="0.1" max="700" required
                                       value="<%= weight != null ? weight : "" %>">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label for="height" class="form-label">Height (cm):</label>
                                <input type="number" class="form-control" id="height" name="height" step="0.1" min="0.1" max="300" required
                                       value="<%= height != null ? height : "" %>">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label for="neck" class="form-label">Neck (cm):</label>
                                <input type="number" class="form-control" id="neck" name="neck" step="0.1" min="1"
                                       value="<%= neck != null ? neck : "" %>" required>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label for="waist" class="form-label">Waist (cm):</label>
                                <input type="number" class="form-control" id="waist" name="waist" step="0.1" min="1"
                                       value="<%= waist != null ? waist : "" %>" required>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mb-3" id="hipGroup" style="display: <%= "female".equalsIgnoreCase(gender) ? "block" : "none" %>;">
                                <label for="hip" class="form-label">Hip (cm):</label>
                                <input type="number" class="form-control" id="hip" name="hip" step="0.1" min="1"
                                       value="<%= hip != null ? hip : "" %>">
                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Calculate Body Fat Percentage</button>
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
                } else if (bfpResult != null) {
            %>             
                    <%= bfpResult %>               
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
            
            <%-- Body Fat Percentage Information Section --%>
            <div class="card mt-4">
                <div class="card-body">
                    <h4 class="card-title">About Body Fat Percentage</h4>
                    <p class="card-text">Body fat percentage is a measure of fitness level since it is the only body measurement which directly calculates a person's relative body composition without regard to height or weight.</p>
                    
                    <h4 class="card-title">Calculation Methods</h4>
        
            <!-- BMI Method -->
            <div class="method-section p-3 mb-4 border rounded bg-white">
                <h5 class="method-header">BMI Method</h5>
                <p class="method-description">Uses BMI, age and gender to estimate body fat percentage:</p>

                <div class="formula-container row">
                    <div class="col-md-6">
                        <div class="formula-box p-3 mb-3 bg-light rounded">
                            <h6 class="formula-title">For Men:</h6>
                            <div class="math-formula">
                                \[ \text{BFP} = (1.20 \times \text{BMI}) + (0.23 \times \text{Age}) - 16.2 \]
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formula-box p-3 mb-3 bg-light rounded">
                            <h6 class="formula-title">For Women:</h6>
                            <div class="math-formula">
                                \[ \text{BFP} = (1.20 \times \text{BMI}) + (0.23 \times \text{Age}) - 5.4 \]
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- US Navy (USC) Method -->
            <div class="method-section p-3 mb-4 border rounded bg-white">
                <h5 class="method-header">US Navy Method (USC)</h5>
                <p class="method-description">Uses circumference measurements in inches:</p>

                <div class="formula-container row">
                    <div class="col-md-6">
                        <div class="formula-box p-3 mb-3 bg-light rounded">
                            <h6 class="formula-title">For Men:</h6>
                            <div class="math-formula">
                                \[ \text{BFP} = 86.010 \times \log_{10}(\text{Waist} - \text{Neck}) - 70.041 \times \log_{10}(\text{Height}) + 36.76 \]
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formula-box p-3 mb-3 bg-light rounded">
                            <h6 class="formula-title">For Women:</h6>
                            <div class="math-formula">
                                \[ \text{BFP} = 163.205 \times \log_{10}(\text{Waist} + \text{Hip} - \text{Neck}) - 97.684 \times \log_{10}(\text{Height}) - 78.387 \]
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- US Navy (SI) Method -->
            <div class="method-section p-3 border rounded bg-white">
                <h5 class="method-header">US Navy Method (SI)</h5>
                <p class="method-description">Uses circumference measurements in centimeters:</p>

                <div class="formula-container row">
                    <div class="col-md-6">
                        <div class="formula-box p-3 mb-3 bg-light rounded">
                            <h6 class="formula-title">For Men:</h6>
                            <div class="math-formula">
                                \[ \text{BFP} = \frac{495}{1.0324 - 0.19077 \times \log_{10}(\text{Waist} - \text{Neck}) + 0.15456 \times \log_{10}(\text{Height})} - 450 \]
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="formula-box p-3 mb-3 bg-light rounded">
                            <h6 class="formula-title">For Women:</h6>
                            <div class="math-formula">
                                \[ \text{BFP} = \frac{495}{1.29579 - 0.35004 \times \log_{10}(\text{Waist} + \text{Hip} - \text{Neck}) + 0.22100 \times \log_{10}(\text{Height})} - 450 \]
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
            <h5 class="mt-4">Body Fat Categories</h5>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover mt-3">
                                <thead class="table-light">
                                    <tr>
                                        <th>Category</th>
                                        <th>Men</th>
                                        <th>Women</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Essential Fat</td>
                                        <td>2-5%</td>
                                        <td>10-13%</td>
                                    </tr>
                                    <tr>
                                        <td>Athletes</td>
                                        <td>6-13%</td>
                                        <td>14-20%</td>
                                    </tr>
                                    <tr>
                                        <td>Fitness</td>
                                        <td>14-17%</td>
                                        <td>21-24%</td>
                                    </tr>
                                    <tr>
                                        <td>Average</td>
                                        <td>18-24%</td>
                                        <td>25-31%</td>
                                    </tr>
                                    <tr>
                                        <td>Obese</td>
                                        <td>25%+</td>
                                        <td>32%+</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <h4 class="mt-4">Health Implications</h4>
                        <div class="alert alert-info">
                            <p>Maintaining a healthy body fat percentage is important for:</p>
                            <ul class="mb-0">
                                <li>Optimal metabolic function</li>
                                <li>Reduced risk of chronic diseases</li>
                                <li>Improved physical performance</li>
                                <li>Better hormonal balance</li>
                            </ul>
                        </div>

                        <div class="alert alert-warning">
                            <strong>Note:</strong> These calculations are estimates. For precise measurements, consider professional methods like DEXA scans or hydrostatic weighing.
                        </div>
                    </div>
                </div>    
            </div>
        </div>


    <!-- Bootstrap JS Bundle with Popper -->
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