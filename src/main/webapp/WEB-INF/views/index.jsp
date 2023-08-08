<%@ page import="java.util.List" %>
<%@ page import="org.itstep.model.ToDoItem" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <link rel="stylesheet" href="<%=request.getContextPath()%>static/css/style.css">
        <script src="https://kit.fontawesome.com/fce2d9fc0e.js" crossorigin="anonymous"></script>

        <title>ToDo List</title>
    </head>
    <body>
        <div class="container">
            <h1>ToDo List</h1>
        </div>
        <div class="page-content page-container" id="page-content">
            <div class="padding">
                <div class="row container d-flex justify-content-center">
                    <div class="col-md-12">
                        <div class="card px-3">
                            <div class="card-body">
                                <h4 class="card-title">Awesome Todo list</h4>
                                <form class="add-items d-flex" method="post">
                                    <input name="description" required type="text" class="form-control todo-list-input"
                                           placeholder="What do you need to do today?">
                                    <button type="submit" class="add btn btn-primary font-weight-bold todo-list-add-btn">
                                        Add
                                    </button>
                                </form>
                                <div class="list-wrapper">
                                    <ul class="d-flex flex-column-reverse todo-list">
                                        <%
                                            List<ToDoItem> todos = (List<ToDoItem>) request.getAttribute("todos");
                                            for (ToDoItem todo : todos) {
                                        %>
                                        <li>
                                            <div class="form-check">
                                                <label class="form-check-label <%=todo.done() ? "completed" : ""%>">
                                                    <input class="checkbox" type="checkbox"
                                                           data-id="<%=todo.id()%>" <%=todo.done()?"checked":""%>>
                                                    <%=todo.description()%>
                                                    <i class="input-helper"></i>
                                                </label>
                                            </div>
                                            <a href="<%=request.getContextPath()%>/delete?id=<%=todo.id()%>" class="remove">
                                                <i class="fa-regular fa-trash-can"></i>
                                            </a>
                                        </li>
                                        <% } %>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
                integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
                integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
                crossorigin="anonymous"></script>
        <%--<script src="<%=request.getContextPath()%>static/js/script.js"></script>--%>
        <script>
            (function () {
                document.body.addEventListener('click', (e) => {
                    if (e.target.nodeName === 'INPUT' && e.target.classList.contains("checkbox")) {
                        e.preventDefault();
                        const id = e.target.getAttribute('data-id');
                        const xhr = new XMLHttpRequest();
                        xhr.open('GET', '<%=request.getContextPath()%>complete?id=' + id, false);
                        xhr.send();
                        location.reload()
                    }
                });
            })();
        </script>
    </body>
</html>