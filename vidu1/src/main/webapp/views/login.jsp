
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<style>
body {font-family: Arial; background: #f3f4f6; display: flex; justify-content:center; align-items:center; height:100vh;}
.login-box {background:#fff; padding:25px; border-radius:8px; width:320px; box-shadow:0 0 10px rgba(0,0,0,0.1);}
h2 {text-align:center; margin-bottom:15px;}
.input-group {display:flex; align-items:center; border:1px solid #ccc; border-radius:6px; margin-bottom:10px; padding:6px 10px;}
.input-group i {color:#888; margin-right:8px;}
.form-control {border:none; outline:none; flex:1;}
button {width:100%; padding:8px; border:none; background:#2563eb; color:#fff; border-radius:6px; font-weight:bold; cursor:pointer;}
.alert {color:#b91c1c; background:#fee2e2; padding:6px; border-radius:5px; text-align:center; margin-bottom:10px;}
.toggle {background:none; border:none; color:#666; cursor:pointer;}
</style>
</head>
<body>
<div class="login-box">
  <h2>Đăng nhập</h2>
  <form action="login" method="post">
    <div class="input-group">
      <i class="fa fa-user"></i>
      <input type="text" name="username" class="form-control" placeholder="Tên đăng nhập hoặc email" required>
    </div>

    <div class="input-group" style="position:relative;">
      <i class="fa fa-lock"></i>
      <input type="password" id="pwd" name="password" class="form-control" placeholder="Mật khẩu" required>
      <button type="button" class="toggle" id="showBtn">Hiện</button>
    </div>

    <button type="submit">Đăng nhập</button>
  </form>
</div>

<script>
const pwd = document.getElementById("pwd");
const btn = document.getElementById("showBtn");
btn.onclick = () => {
  if (pwd.type === "password") { pwd.type = "text"; btn.textContent = "Ẩn"; }
  else { pwd.type = "password"; btn.textContent = "Hiện"; }
};
</script>
</body>
</html>
