window.onbeforeunload = () => "¿Estás seguro de salir?";

const formLogin = document.getElementById('form-login');

if (formLogin) {
  formLogin.addEventListener('submit', (e) => {
    e.preventDefault();

    const correo = document.getElementById('correo').value;
    const password = document.getElementById('password').value;

    if (correo === '' || password === '') {
      alert('Por favor complete el correo y la contraseña.');
    } else {
      alert('Inicio de sesión correcto.');
      window.location.href = '/src/main/resources/templates/Components/main.html';
    }
  });
}