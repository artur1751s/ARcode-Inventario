const main = document.querySelector("main");

//Carga inicial de la pagina, comprueba el ultimo estado y lo carga
window.addEventListener('load', () => {
    localStorage.getItem("section") != undefined ?
        cargarSeccion(localStorage.getItem("section")) :
        cargarSeccion("/Componentes/Dashboard.html");
    moverSelector(localStorage.getItem("btnTop"));
});

//Funcionalidad del boton para ocultar y mostrar el menu lateral
const asideBtn = document.querySelector(".aside-btn");
const aside = document.querySelector("aside");
asideBtn.addEventListener('click', () => {
    aside.classList.toggle("disable");
    main.classList.toggle("recogido");
    asideBtn.classList.toggle("recogido")
});

//Funcion para mostrar cada seccion dentro de main
function cargarSeccion(url) {
    fetch(url)
    .then(data => data.text())
    .then(html => {
        main.innerHTML = html;
    });
}

//Funcion para mover el selector de seleccion de seccion del menu lateral
function moverSelector(btn) {
  const selector = document.querySelector('.selector');
  selector.style.transform = `translateY(${btn}px)`;
}
//Funcionalidad de los botones laterales
const navButtons = document.querySelectorAll("aside nav button");
navButtons.forEach(navButton => {
    //Comprueba el localstorage para cargar estilo menu lateral
    let url = navButton.dataset.url;
    localStorage.getItem("section") != url ?
        navButton.classList.remove("selected") :
        navButton.classList.add("selected");
    //Carga seccion, borra el estilo de todos los botones y se lo añade al actual
    navButton.addEventListener('click', () => {
        cargarSeccion(url);
        navButtons.forEach(navButton => {
            navButton.classList.remove("selected");
        });
        navButton.classList.add("selected");
        moverSelector(navButton.offsetTop);
        localStorage.setItem("btnTop", navButton.offsetTop)
        localStorage.setItem("section", url);
    });
});

//botón logout
const logout = document.getElementById("logout");
logout.addEventListener('click', () => {
    window.location.href = 'Login.html';
});

//Funcionalidad del boton del modo oscuro
const darkMode = document.getElementById("darkMode");
darkMode.addEventListener('click', () => {
  document.body.classList.toggle("dark");
  document.body.classList.contains("dark") ?
  darkMode.textContent = "☀️" : darkMode.textContent = "🌙"
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

main.addEventListener('click', (e) => {
    const element = e.target;
    const state = element.closest("td")?.previousElementSibling;
    if (!state) return;
    switch (element) {
        case element.closest("table .delete"):
            if (confirm(`¿Estas seguro de que quieres borrar este elemento?`)) {
                element.closest("tr").remove();
            }
            break;
            
        case element.closest("table .user-disable"):
            element.classList.add("user-enable");
            element.classList.remove("user-disable");
            element.setAttribute("aria-label", "Habilitar Cliente");
            state.textContent = "SUSPENDIDO";
            state.classList.add("state-disable");
            state.classList.remove("state-enable");
            break;
            
        case element.closest("table .user-enable"):
            element.classList.add("user-disable");
            element.classList.remove("user-enable");
            element.setAttribute("aria-label", "Deshabilitar Cliente");
            state.textContent = "ACTIVO";
            state.classList.add("state-enable");
            state.classList.remove("state-disable");
            break;
    }
});