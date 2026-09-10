console.log("main.js cargado✅");

const main = document.querySelector("main");

//Carga inicial de la pagina, comprueba el ultimo estado y lo carga
window.addEventListener('load', () => {
    let ruta = window.location.pathname.split("/").pop();

    if (ruta === "main") {
        ruta = "dashboard";
    }
    
    cargarSeccion("/main/module/"+ruta);
    
    const buttons = document.querySelectorAll("aside nav button");
    
    buttons.forEach(button => {
        button.classList.remove("selected");
        
        const section = button.dataset.url.split("/").pop();

        if (ruta === section) {
            moverSelector(button.offsetTop);
            button.classList.add("selected");
        }
    });
    
    const darkMode = document.getElementById("darkMode");

    if (localStorage.getItem("darkMode") === "on") {
        document.body.classList.add("dark");
        darkMode.textContent = "☀️";
    }
});

//Funcionalidad del boton para ocultar y mostrar el menu lateral
const asideBtn = document.querySelector(".aside-btn");
const aside = document.querySelector("aside");

asideBtn.addEventListener('click', () => {
    aside.classList.toggle("disable");
    main.classList.toggle("recogido");
    asideBtn.classList.toggle("recogido");
});

//Funcion para mostrar cada seccion dentro de main
function cargarSeccion(url, CambiarUrl = true) {
    fetch(url)
    .then(data => data.text())
    .then(html => {
        const ruta = url.split("/").pop();
        main.innerHTML = html;
        history.pushState({}, "", "/main/" + ruta);
    });
}

//Funcion para mover el selector del menu lateral
function moverSelector(btn) {
  const selector = document.querySelector('.selector');

  selector.style.transform = `translateY(${btn}px)`;
}
//Funcionalidad de los botones laterales
const navButtons = document.querySelectorAll("aside nav button");

navButtons.forEach(navButton => {
    //Carga seccion, borra el estilo de todos los botones y se lo añade al actual
    const url = navButton.dataset.url;

    navButton.addEventListener('click', () => {
        cargarSeccion(url);

        navButtons.forEach(navButton => {
            navButton.classList.remove("selected");
        });

        navButton.classList.add("selected");

        moverSelector(navButton.offsetTop);

        localStorage.setItem("btnTop", navButton.offsetTop);
    });
});

//botón logout
const logout = document.getElementById("logout");

logout.addEventListener('click', () => {
    window.location.href = '/clientes/login';
});

//Funcionalidad del boton del modo oscuro
const darkMode = document.getElementById("darkMode");

darkMode.addEventListener('click', () => {
    document.body.classList.toggle("dark");
    
    if (document.body.classList.contains("dark")) {
        darkMode.textContent = "☀️";
        localStorage.setItem("darkMode", "on");
    } else {
        darkMode.textContent = "🌙";
        localStorage.setItem("darkMode", "off");
    }
});

//Funcionalidad del buscador
document.addEventListener('input', (e) => {
    if (e.target.classList.contains("search-input")) {
        const input = e.target;
        const text = input.value.toLowerCase();
        const section = input.closest("section");
        const filas = section.querySelectorAll("tbody tr");

        filas.forEach(fila => {
            const section = fila.textContent.toLowerCase();

            fila.style.display = section.includes(text) ? 
                "" :
                "none";
        });
    }
});