<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    String name = (String) session.getAttribute("name");
    Double weight = (Double) session.getAttribute("weight");
    Double height = (Double) session.getAttribute("height");
    String ic = (String) session.getAttribute("ic");
    String gender = (String) session.getAttribute("gender");
    Integer age = (Integer) session.getAttribute("age");

    // Results from calculation
    String bmrResult = (String) request.getAttribute("bmrResult");
    String tdeeResult = (String) request.getAttribute("tdeeResult");
    String caloriesResult = (String) request.getAttribute("caloriesResult");
    String macroResult = (String) request.getAttribute("macroResult");
    String error = (String) request.getAttribute("error");
    String selectedActivity = request.getParameter("activity");
    String selectedGoal = request.getParameter("goal");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Macro Calculator</title>
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
                <a class="nav-link" href="index.html">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="BMICalculator.jsp">BMI</a>
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
                <a class="nav-link active" aria-current="page" href="MacroCalculator.jsp">Macro</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>

    <div class="container py-5">
        <div class="card mx-auto" style="max-width: 800px;">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">Macro Calculator</h2>
            </div>
            <div class="card-body">
                <form action="MacroCalculatorClient" method="post">
                    <input type="hidden" name="formType" value="macro">

                    <div class="row">                      
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label class="form-label">Gender:</label><br>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="gender" id="male" value="male" <%= "male".equalsIgnoreCase(gender) ? "checked" : "" %>>
                                    <label class="form-check-label" for="male">Male</label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="gender" id="female" value="female" <%= "female".equalsIgnoreCase(gender) ? "checked" : "" %>>
                                    <label class="form-check-label" for="female">Female</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="mb-3">
                                <label for="name" class="form-label">Name:</label>
                                <input type="text" class="form-control" id="name" name="name" value="<%= name != null ? name : "" %>" readonly>
                            </div>
                        </div>                       
                    </div>
                                    
                    <div class="row">
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label for="age" class="form-label">Age:</label>
                                <input type="number" class="form-control" id="age" name="age" min="1" max="120" value="<%= age != null ? age : "" %>" required>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label for="weight" class="form-label">Weight (kg):</label>
                                <input type="number" class="form-control" id="weight" name="weight" step="0.1" min="0.1" max="700" value="<%= weight != null ? weight : "" %>" required>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="mb-3">
                                <label for="height" class="form-label">Height (cm):</label>
                                <input type="number" class="form-control" id="height" name="height" step="0.1" min="0.1" max="300" value="<%= height != null ? height : "" %>" required>
                            </div>
                        </div>
                    </div>
                            
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="activity" class="form-label">Activity Level:</label>
                                <select class="form-select" id="activity" name="activity" required>
                                    <option value="sedentary" <%= "sedentary".equals(selectedActivity) ? "selected" : "" %>>Sedentary (little or no exercise)</option>
                                    <option value="lightly active" <%= "lightly active".equals(selectedActivity) ? "selected" : "" %>>Lightly Active (light exercise 1-3 days/week)</option>
                                    <option value="moderately active" <%= "moderately active".equals(selectedActivity) ? "selected" : "" %>>Moderately Active (moderate exercise 3-5 days/week)</option>
                                    <option value="very active" <%= "very active".equals(selectedActivity) ? "selected" : "" %>>Very Active (hard exercise 6-7 days/week)</option>
                                    <option value="extra active" <%= "extra active".equals(selectedActivity) ? "selected" : "" %>>Extra Active (very hard exercise & physical job)</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="goal" class="form-label">Goal:</label>
                                <select class="form-select" id="goal" name="goal" required>
                                    <option value="maintain" <%= "maintain".equals(selectedGoal) ? "selected" : "" %>>Maintain Weight</option>
                                    <option value="mild weight loss" <%= "mild weight loss".equals(selectedGoal) ? "selected" : "" %>>Mild Weight Loss (0.25 kg/week)</option>
                                    <option value="weight loss" <%= "weight loss".equals(selectedGoal) ? "selected" : "" %>>Weight Loss (0.5 kg/week)</option>
                                    <option value="extreme weight loss" <%= "extreme weight loss".equals(selectedGoal) ? "selected" : "" %>>Extreme Weight Loss (1 kg/week)</option>
                                    <option value="mild weight gain" <%= "mild weight gain".equals(selectedGoal) ? "selected" : "" %>>Mild Weight Gain (0.25 kg/week)</option>
                                    <option value="weight gain" <%= "weight gain".equals(selectedGoal) ? "selected" : "" %>>Weight Gain (0.5 kg/week)</option>
                                    <option value="extreme weight gain" <%= "extreme weight gain".equals(selectedGoal) ? "selected" : "" %>>Extreme Weight Gain (1 kg/week)</option>
                                </select>
                            </div>
                        </div>
                    </div>


                    <button type="submit" class="btn btn-primary w-100">Calculate Macros</button>
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
                } else if (bmrResult != null) {
            %>
                <div class="card mb-4">
                    <div class="card-header" style="background-color: #28a745; color: white; padding: 15px;">
                        <h4 class="mb-0">Macro Calculation Results</h4>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <h5>Basic Metabolic Rate (BMR)</h5>
                                    <p class="lead"><%= bmrResult %> kcal/day</p>
                                    <p>Your BMR is the number of calories your body needs at complete rest.</p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <h5>Total Daily Energy Expenditure (TDEE)</h5>
                                    <p class="lead"><%= tdeeResult %> kcal/day</p>
                                    <p>Your TDEE accounts for your activity level on top of your BMR.</p>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row mt-3">
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <h5>Recommended Daily Calories</h5>
                                    <p class="lead"><%= caloriesResult %> kcal/day</p>
                                    <p>Based on your selected goal: <%= request.getParameter("goal") %></p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="mb-3">
                                    <h5>Macronutrient Distribution</h5>
                                    <p class="lead"><%= macroResult %></p>
                                    <p>Optimized for your goal and body composition.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                                    
                                    
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
            
            <%-- Macro Information Section --%>
            <div class="card mt-4">
                <div class="card-body">
                    <h4 class="card-title">About Macronutrients</h4>
                    <p class="card-text">Macronutrients (macros) are nutrients that provide calories or energy. The three primary macronutrients are proteins, fats, and carbohydrates. Each plays a unique role in your body's function and health.</p>
                    
                    <div class="row mt-4">
                        <div class="col-md-4">
                            <div class="card h-100">
                                <div class="card-header bg-info text-white">
                                    <h5>Protein</h5>
                                </div>
                                <div class="card-body">
                                    <p><strong>Calories per gram:</strong> 4</p>
                                    <p>Essential for building and repairing tissues, making enzymes and hormones, and is a building block of bones, muscles, cartilage, skin, and blood.</p>
                                    <p><strong>Sources:</strong> Meat, fish, eggs, dairy, legumes, nuts</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card h-100">
                                <div class="card-header bg-warning text-dark">
                                    <h5>Carbohydrates</h5>
                                </div>
                                <div class="card-body">
                                    <p><strong>Calories per gram:</strong> 4</p>
                                    <p>Your body's main source of energy. Carbs are broken down into glucose which fuels your brain and muscles.</p>
                                    <p><strong>Sources:</strong> Fruits, vegetables, grains, legumes, dairy</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card h-100">
                                <div class="card-header bg-danger text-white">
                                    <h5>Fats</h5>
                                </div>
                                <div class="card-body">
                                    <p><strong>Calories per gram:</strong> 9</p>
                                    <p>Needed for energy, absorbing certain vitamins, protecting organs, and maintaining cell membranes.</p>
                                    <p><strong>Sources:</strong> Oils, butter, nuts, seeds, fatty fish, avocado</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <h4 class="mt-4">How TDEE is Calculated</h4>
                    <div class="row">
                        <div class="col-md-6">
                            <h5>BMR Formulas</h5>
                            <p>For Men:</p>
                            <p>\[ \text{BMR} = 10 \times \text{weight (kg)} + 6.25 \times \text{height (cm)} - 5 \times \text{age (years)} + 5 \]</p>
                            <p>For Women:</p>
                            <p>\[ \text{BMR} = 10 \times \text{weight (kg)} + 6.25 \times \text{height (cm)} - 5 \times \text{age (years)} - 161 \]</p>
                        </div>
                        <div class="col-md-6">
                            <h5>Activity Multipliers</h5>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Activity Level</th>
                                        <th>Multiplier</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Sedentary</td>
                                        <td>1.2</td>
                                    </tr>
                                    <tr>
                                        <td>Lightly Active</td>
                                        <td>1.375</td>
                                    </tr>
                                    <tr>
                                        <td>Moderately Active</td>
                                        <td>1.55</td>
                                    </tr>
                                    <tr>
                                        <td>Very Active</td>
                                        <td>1.725</td>
                                    </tr>
                                    <tr>
                                        <td>Extra Active</td>
                                        <td>1.9</td>
                                    </tr>
                                </tbody>
                            </table>
                            <p>\[ \text{TDEE} = \text{BMR} \times \text{Activity Multiplier} \]</p>
                        </div>
                    </div>
                    
                    <h4 class="mt-4">Goal-Based Calorie Adjustments</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Goal</th>
                                <th>Calorie Adjustment</th>
                                <th>Approx. Weekly Change</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Extreme Weight Loss</td>
                                <td>-1100 kcal/day</td>
                                <td>-1 kg/week</td>
                            </tr>
                            <tr>
                                <td>Weight Loss</td>
                                <td>-550 kcal/day</td>
                                <td>-0.5 kg/week</td>
                            </tr>
                            <tr>
                                <td>Mild Weight Loss</td>
                                <td>-275 kcal/day</td>
                                <td>-0.25 kg/week</td>
                            </tr>
                            <tr>
                                <td>Maintain</td>
                                <td>0 kcal adjustment</td>
                                <td>0 kg/week</td>
                            </tr>
                            <tr>
                                <td>Mild Weight Gain</td>
                                <td>+275 kcal/day</td>
                                <td>+0.25 kg/week</td>
                            </tr>
                            <tr>
                                <td>Weight Gain</td>
                                <td>+550 kcal/day</td>
                                <td>+0.5 kg/week</td>
                            </tr>
                            <tr>
                                <td>Extreme Weight Gain</td>
                                <td>+1100 kcal/day</td>
                                <td>+1 kg/week</td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <h4 class="mt-4">Macronutrient Ratios by Goal</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Goal</th>
                                <th>Protein</th>
                                <th>Fat</th>
                                <th>Carbs</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Weight Loss</td>
                                <td>35%</td>
                                <td>25%</td>
                                <td>40%</td>
                            </tr>
                            <tr>
                                <td>Maintenance</td>
                                <td>30%</td>
                                <td>25%</td>
                                <td>45%</td>
                            </tr>
                            <tr>
                                <td>Weight Gain</td>
                                <td>25%</td>
                                <td>20%</td>
                                <td>55%</td>
                            </tr>
                        </tbody>
                    </table>
                    
                    <div class="alert alert-info mt-4">
                        <strong>Note:</strong> These recommendations are general guidelines. Individual needs may vary based on specific health conditions, activity types, and metabolic factors. Consult with a nutritionist or healthcare provider for personalized advice.
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>