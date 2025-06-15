<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    String name = (String) session.getAttribute("name");
    Double weight = (Double) session.getAttribute("weight");
    Double height = (Double) session.getAttribute("height");
    String ic = (String) session.getAttribute("ic");
    String gender = (String) session.getAttribute("gender");
    Integer age = (Integer) session.getAttribute("age");

    String bmiResult = (String) request.getAttribute("bmiResult");
    String userName = (String) request.getAttribute("userName");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BMI Calculator</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- MathJax for formula rendering -->
    <script src="https://polyfill.io/v3/polyfill.min.js?features=es6"></script>
    <script id="MathJax-script" async src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
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
                <a class="nav-link" href="homepage.html">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="BMICalculator.jsp">BMI</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="BFPCalculator.jsp">Body Fat</a>
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
        <div class="card mx-auto" style="max-width: 800px;">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">BMI Calculator</h2>
            </div>
            <div class="card-body">
                <form action="HealthFitnessClient" method="post">
                    <input type="hidden" name="formType" value="bmi">

                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="name" class="form-label">Name:</label>
                                <input type="text" class="form-control" id="name" name="name" value="<%= name != null ? name : "" %>" readonly>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="mb-3">
                                <label for="weight" class="form-label">Weight (kg):</label>
                                <input type="number" class="form-control" id="weight" name="weight" step="0.1" min="0.1" max="700" value="<%= weight != null ? weight : "" %>" required>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="mb-3">
                                <label for="height" class="form-label">Height (cm):</label>
                                <input type="number" class="form-control" id="height" name="height" step="0.1" min="0.1" max="300" value="<%= height != null ? height : "" %>" required>
                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Calculate BMI</button>
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
                } else if (bmiResult != null) {
                    
 
            %>
                    <%= bmiResult %>      
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
                
                <%-- BMI Information Section --%>
               
                <div class="card mt-4">
                    <div class="card-body">
                        <h4 class="card-title">About BMI</h4>
                        <p class="card-text">The Body Mass Index (BMI) Calculator can be used to calculate BMI value and corresponding weight status while taking age into consideration. BMI is a measurement of a person's leanness or corpulence based on their height and weight, and is intended to quantify tissue mass.</p>
                        
                        <p class="card-text">It is widely used as a general indicator of whether a person has a healthy body weight for their height. Specifically, the value obtained from the calculation of BMI is used to categorize whether a person is underweight, normal weight, overweight, or obese depending on what range the value falls between.</p>
                        
                        <h4 class="card-title">BMI Formula</h4>
                        <div class="row">
                            <div class="col-md-6">
                                <h5>Metric Units</h5>
                                <p>\[ \text{BMI} = \frac{\text{weight (kg)}}{\text{height}^2 (\text{m})} \]</p>
                                <p>Example: \( \frac{70}{1.75^2} = 22.9 \)</p>
                            </div>
                            <div class="col-md-6">
                                <h5>US Units</h5>
                                <p>\[ \text{BMI} = 703 \times \frac{\text{weight (lbs)}}{\text{height}^2 (\text{in})} \]</p>
                                <p>Example: \( 703 \times \frac{160}{70^2} = 23.0 \)</p>
                            </div>
                        </div>
                        
                        <h5 class="mt-4">BMI Table for Adults</h5>
                        <p class="card-text">This is the World Health Organization's (WHO) recommended body weight based on BMI values for adults. It is used for both men and women, age 20 or older.</p>
                        
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover mt-3">
                                <thead class="table-light">
                                    <tr>
                                        <th>Classification</th>
                                        <th>BMI Range (kg/m²)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Severe Thinness</td>
                                        <td>&lt; 16</td>
                                    </tr>
                                    <tr>
                                        <td>Moderate Thinness</td>
                                        <td>16 - 17</td>
                                    </tr>
                                    <tr>
                                        <td>Mild Thinness</td>
                                        <td>17 - 18.5</td>
                                    </tr>
                                    <tr>
                                        <td>Normal</td>
                                        <td>18.5 - 25</td>
                                    </tr>
                                    <tr>
                                        <td>Overweight</td>
                                        <td>25 - 30</td>
                                    </tr>
                                    <tr>
                                        <td>Obese Class I</td>
                                        <td>30 - 35</td>
                                    </tr>
                                    <tr>
                                        <td>Obese Class II</td>
                                        <td>35 - 40</td>
                                    </tr>
                                    <tr>
                                        <td>Obese Class III</td>
                                        <td>&gt; 40</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        
                        <div class="alert alert-info mt-3">
                            <strong>Note:</strong> Being overweight or underweight can have significant health effects. While BMI is an imperfect measure of healthy body weight, it is a useful indicator of whether any additional testing or action is required.
                        </div>   

                        <h4 class="mt-4">Risks Associated With Being Overweight</h4>
                        <p>Being overweight increases the risk of serious diseases and health conditions:</p>
                        <ul class="list-group list-group-flush mb-3">
                            <li class="list-group-item">High blood pressure</li>
                            <li class="list-group-item">Higher LDL ("bad") cholesterol and lower HDL ("good") cholesterol</li>
                            <li class="list-group-item">Type II diabetes</li>
                            <li class="list-group-item">Coronary heart disease</li>
                            <li class="list-group-item">Stroke</li>
                            <li class="list-group-item">Gallbladder disease</li>
                            <li class="list-group-item">Osteoarthritis</li>
                            <li class="list-group-item">Sleep apnea and breathing problems</li>
                            <li class="list-group-item">Certain cancers (endometrial, breast, colon, kidney, gallbladder, liver)</li>
                            <li class="list-group-item">Low quality of life and mental health issues</li>
                            <li class="list-group-item">Increased risk of mortality</li>
                        </ul>
                        <div class="alert alert-info">
                            Generally, aim for BMI below 25 kg/m², but consult your doctor for personalized advice.
                        </div>

                        <h4 class="mt-4">Risks Associated With Being Underweight</h4>
                        <p>Being underweight also carries health risks:</p>
                        <ul class="list-group list-group-flush mb-3">
                            <li class="list-group-item">Malnutrition, vitamin deficiencies, anemia</li>
                            <li class="list-group-item">Osteoporosis (bone weakness)</li>
                            <li class="list-group-item">Decreased immune function</li>
                            <li class="list-group-item">Growth and development issues in children</li>
                            <li class="list-group-item">Reproductive issues in women</li>
                            <li class="list-group-item">Surgical complications</li>
                            <li class="list-group-item">Increased risk of mortality</li>
                        </ul>
                        <div class="alert alert-warning">
                            Underweight may indicate underlying conditions like anorexia nervosa. Consult a doctor if concerned.
                        </div>

                        <h4 class="mt-4">Limitations of BMI</h4>
                        <div class="alert alert-secondary">
                            <p>BMI doesn't account for body composition and has limitations:</p>
                            <ul>
                                <li>Measures excess weight, not excess fat</li>
                                <li>Affected by age, sex, ethnicity, muscle mass</li>
                                <li>May misclassify muscular individuals as overweight</li>
                                <li>Older adults may have more body fat at same BMI</li>
                                <li>Women typically have more body fat than men at same BMI</li>
                            </ul>
                            <p class="mb-0">BMI should be considered alongside other health measurements.</p>
                        </div>
                    </div>
                </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>