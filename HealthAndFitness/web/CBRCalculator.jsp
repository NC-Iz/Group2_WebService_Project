<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true" %>
<%
    String name = (String) session.getAttribute("name");
    Double weight = (Double) session.getAttribute("weight");
    Double height = (Double) session.getAttribute("height");
    String ic = (String) session.getAttribute("ic");
    String gender = (String) session.getAttribute("gender");
    Integer age = (Integer) session.getAttribute("age");

    String cbrResult = (String) request.getAttribute("cbrResult");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Calories Burned Calculator</title>
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
    </style>
</head>
<body class="bg-light">
    <div class="container py-5">
        <div class="card calculator-card">
            <div class="card-header bg-primary text-white">
                <h2 class="mb-0">Calories Burned Calculator</h2>
            </div>
            <div class="card-body">
                <form action="HealthFitnessClient" method="post">
                    <input type="hidden" name="formType" value="cbr">

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
                                <label for="weight" class="form-label">Weight (kg):</label>
                                <input type="number" class="form-control" id="weight" name="weight" step="0.1" 
                                       value="<%= weight != null ? weight : "" %>" required>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="activity" class="form-label">Activity:</label>
                                <select class="form-select" id="activity" name="activity" required>
                                    <option value="" disabled selected>Choose activity</option>
                                    <option value="walking">Walking</option>
                                    <option value="running">Running</option>
                                    <option value="cycling">Cycling</option>
                                    <option value="swimming">Swimming</option>
                                    <option value="weightlifting">Weightlifting</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="intensity" class="form-label">Intensity:</label>
                                <select class="form-select" id="intensity" name="intensity" required>
                                    <option value="" disabled selected>Select intensity</option>
                                    <option value="low">Low</option>
                                    <option value="moderate">Moderate</option>
                                    <option value="high">High</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="duration" class="form-label">Duration (minutes):</label>
                        <input type="number" class="form-control" id="duration" name="duration" step="1" min="1" required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Calculate Calories Burned</button>
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
                } else if (cbrResult != null) {
            %>
                <div class="alert alert-success">
                    <p class="mb-0"><%= cbrResult %></p>
                </div>
                
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
            <%
                }
            %>
            
            <%-- Calories Burned Information Section --%>
            <div class="card mt-4">
                <div class="card-body">
                    <h4 class="card-title">About Calories Burned</h4>
                    <p class="card-text">The Calories Burned Calculator estimates the energy expenditure for various physical activities based on your body weight, activity type, intensity level, and duration.</p>
                    
                    <h4 class="card-title mt-4">Calculation Formula</h4>
                    <div class="row">
                        <div class="col-md-12">
                            <p>\[ \text{Calories Burned} = \text{Time (hours)} \times \text{MET} \times \text{Weight (kg)} \]</p>
                            <p>Where:</p>
                            <ul>
                                <li><strong>MET</strong> (Metabolic Equivalent of Task) represents the energy cost of the activity</li>
                                <li>1 MET = 1 kcal/kg/hour (energy expended while sitting quietly)</li>
                            </ul>
                        </div>
                    </div>
                    
                    <h5 class="mt-4">MET Values by Activity and Intensity</h5>
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover mt-3">
                            <thead class="table-light">
                                <tr>
                                    <th>Activity</th>
                                    <th>Low Intensity</th>
                                    <th>Moderate Intensity</th>
                                    <th>High Intensity</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Walking</td>
                                    <td>2.5 (slow pace)</td>
                                    <td>3.5 (brisk pace)</td>
                                    <td>5.0 (power walk)</td>
                                </tr>
                                <tr>
                                    <td>Running</td>
                                    <td>6.0 (jogging)</td>
                                    <td>8.5 (running)</td>
                                    <td>12.0 (sprinting)</td>
                                </tr>
                                <tr>
                                    <td>Cycling</td>
                                    <td>4.0 (leisure)</td>
                                    <td>6.0 (moderate)</td>
                                    <td>10.0 (racing)</td>
                                </tr>
                                <tr>
                                    <td>Swimming</td>
                                    <td>5.0 (slow)</td>
                                    <td>7.0 (moderate)</td>
                                    <td>10.0 (vigorous)</td>
                                </tr>
                                <tr>
                                    <td>Weightlifting</td>
                                    <td>3.0 (light)</td>
                                    <td>4.5 (vigorous)</td>
                                    <td>6.0 (circuit)</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <p class="text-muted small">MET values based on Compendium of Physical Activities</p>
                    
                    <h4 class="mt-4">Factors Affecting Calories Burned</h4>
                    <p>Several factors influence how many calories you burn during exercise:</p>
                    <ul class="list-group list-group-flush mb-3">
                        <li class="list-group-item"><strong>Body Weight:</strong> Heavier individuals burn more calories performing the same activity</li>
                        <li class="list-group-item"><strong>Exercise Intensity:</strong> Higher intensity activities burn more calories per minute</li>
                        <li class="list-group-item"><strong>Duration:</strong> Longer exercise sessions burn more total calories</li>
                        <li class="list-group-item"><strong>Body Composition:</strong> Muscle burns more calories than fat at rest</li>
                        <li class="list-group-item"><strong>Fitness Level:</strong> Fit individuals often burn calories more efficiently</li>
                    </ul>
                    
                    <h4 class="mt-4">Exercise Intensity and Energy Sources</h4>
                    <div class="alert alert-info">
                        <p>Exercise intensity affects which energy sources your body uses:</p>
                        <ul class="mb-0">
                            <li><strong>Low Intensity:</strong> Primarily burns fat (good for endurance training)</li>
                            <li><strong>Moderate Intensity:</strong> Mix of fat and carbohydrates</li>
                            <li><strong>High Intensity:</strong> Primarily burns carbohydrates (good for improving cardiovascular fitness)</li>
                        </ul>
                    </div>
                    
                    <h4 class="mt-4">Limitations of Calorie Calculations</h4>
                    <div class="alert alert-secondary">
                        <p>These calculations are estimates with some limitations:</p>
                        <ul>
                            <li>MET values are averages and may not reflect your exact energy expenditure</li>
                            <li>Doesn't account for individual variations in metabolism</li>
                            <li>Assumes consistent intensity throughout the activity</li>
                            <li>Doesn't consider afterburn effect (EPOC) from intense exercise</li>
                        </ul>
                        <p class="mb-0">For most accurate measurements, specialized equipment like metabolic carts are needed.</p>
                    </div>
                    
                    <h4 class="mt-4">Tips for Maximizing Calorie Burn</h4>
                    <div class="alert alert-success">
                        <ul class="mb-0">
                            <li>Combine cardio and strength training</li>
                            <li>Try interval training (alternating high and low intensity)</li>
                            <li>Increase duration gradually</li>
                            <li>Vary your workouts to prevent plateaus</li>
                            <li>Stay hydrated and fuel properly before/after exercise</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>