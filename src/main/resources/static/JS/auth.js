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

const formCliente = document.getElementById('form-cliente');

if (formCliente) {
  formCliente.addEventListener('submit', (e) => {
    e.preventDefault();

    const nombre = document.getElementById('nombre').value;
    const documento = document.getElementById('documento').value;
    const telefono = document.getElementById('telefono').value;
    const direccion = document.getElementById('direccion').value;
    const correo = document.getElementById('emailCliente').value;

    if (nombre === '' || documento === '' || telefono === '' || direccion === '' || correo === '') {
      alert('Todos los campos son obligatorios.');
    } else {
      alert('Cliente guardado correctamente.');
      window.location.href = 'login.html';
    }
  });
}