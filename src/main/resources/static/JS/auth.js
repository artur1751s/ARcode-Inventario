console.log("auth.js cargado✅");

const login = document.getElementById("login");
const register = document.getElementById("register");

//Funcion para ver/ocultar la contraseña
const botonesPassword = document.querySelectorAll('.btn-ver-password');

botonesPassword.forEach((boton) => {
  boton.addEventListener('click', () => {
    const contenedor = boton.closest('.password-container');
    const passwordInput = contenedor.querySelector('.password-input');
    
    if (passwordInput.type === 'password') {
      passwordInput.type = 'text';
      boton.classList.add("btn-ocultar-password");
      boton.classList.remove("btn-ver-password");      
    } else {
      passwordInput.type = 'password';
      boton.classList.add("btn-ver-password");
      boton.classList.remove("btn-ocultar-password");
    }
  });
});

//funcion para evitar perdida de datos al recargar
let unload = false;
const registerForm = document.getElementById('form-cliente');

if (registerForm) {
  registerForm.addEventListener('submit', () => {
    if (registerForm.checkValidity()) {
      unload = true;
    }
  })
}

window.addEventListener("beforeunload", (e) => {
  if (login) {
    const correo = document.getElementById('correo')?.value.trim();
    const password = document.getElementById('password')?.value.trim();

    if (unload) {
      return;
    }

    if (correo || password) {
      e.preventDefault();
      e.returnValue = '';
    }
  }

  if (register) {
    const nombre = document.getElementById('nombre')?.value.trim();
    const contrasena = document.getElementById('contrasena')?.value.trim();
    const documento = document.getElementById('documento')?.value.trim();
    const telefono = document.getElementById('telefono')?.value.trim();
    const direccion = document.getElementById('direccion')?.value.trim();
    const correo = document.getElementById('emailCliente')?.value.trim();

    if (unload) {
      return;
    }

    if (nombre || contrasena || documento || telefono || direccion || correo) {
      e.preventDefault();
      e.returnValue = '';
    }
  }
});

const formLogin = document.getElementById('form-login');

if (formLogin) {
  formLogin.addEventListener('submit', async (e) => {
    e.preventDefault();

    const correo = document.getElementById('correo').value.trim();
    const password = document.getElementById('password').value.trim();

    try {
      const respuesta = await fetch("/api/clientes/login", {
        method: 'POST',
        headers: {
          'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
          correo: correo,
          contrasena: password
        })
      });
      
      const mensaje = await respuesta.text();

      if (respuesta.ok) {
        alert(mensaje);

        unload = true;

        window.location.href = '/main/dashboard';
      } else {
        alert(mensaje);
      }

    } catch (error) {
      console.error('Error al iniciar sesion: ', error);
      alert('No se pudo conectar con el servidor.');
    }
  });
}