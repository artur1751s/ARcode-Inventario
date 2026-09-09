document.addEventListener("click", (event) => {
    const botonEditar = event.target.closest(".btn-editar");

    if (botonEditar) {
        const id = botonEditar.dataset.id;
        abrirModalEditar(id);
        return;
    }

    if (event.target.closest("#btn-close-modal")) {
        cerrarModal();
        return;
    }

    if (event.target.closest("#btn-cancel")) {
        cerrarModal();
        return;
    }
});

document.addEventListener("submit", (event) => {
    if (event.target.id === "form-edit-clientes") {
        event.preventDefault();
        actualizarCliente(event.target);
    }
});

function abrirModalEditar(id) {
    const boton = document.querySelector(`.btn-editar[data-id="${id}"]`);

    if (!boton) {
        return;
    }

    const fila = boton.closest("tr");
    const celdas = fila.querySelectorAll("td");

    document.getElementById("user-id").value = id;

    document.getElementById("edit-name").value = celdas[1].textContent.trim();
    document.getElementById("edit-identification").value = celdas[2].textContent.trim();
    document.getElementById("edit-email").value = celdas[3].textContent.trim();
    document.getElementById("edit-number").value = celdas[4].textContent.trim();
    document.getElementById("edit-address").value = celdas[5].textContent.trim();

    document.getElementById("modal-clientes").classList.add("activo");
}

async function actualizarCliente(form) {
    const id = document.getElementById("user-id").value;
    const datosFormulario = new FormData(form);
    const cliente = Object.fromEntries(datosFormulario);

    delete cliente.id_cliente;

    try {
        const response = await fetch(`/api/clientes/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(cliente)
            }
        );

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const clienteActualizado = await response.json();
        console.log("Cliente actualizado:", clienteActualizado
        );
        cerrarModal();
        alert("Cliente actualizado correctamente");
    } catch (error) {
        console.error("Error al actualizar cliente:", error);
        alert("No se pudo actualizar el cliente");
    }
}

function cerrarModal() {
    const modal = document.getElementById("modal-clientes");

    if (modal) {
        modal.classList.remove("activo");
    }
}

//Funcion boton de Deshabilitar/Habilitar Usuarios
document.addEventListener("click", (event) => {
    const btnEnable = event.target.closest(".user-enable");
    const btnDisable = event.target.closest(".user-disable");

    if (btnEnable) {
        const id = btnEnable.dataset.id;
        userEnable(id);
        return;
    }
    
    if (btnDisable) {
        const id = btnDisable.dataset.id;
        userDisable(id);
        return;
    }
});

async function userDisable(id) {
    const boton = document.querySelector(`.user-disable[data-id="${id}"]`)

    if (!boton) {
        return;
    }

    const updated = await updUserDisable(id);

    if (updated) {
        window.location.reload();
    } else {
        alert("No se pudo suspender el cliente");
    }
}

async function updUserDisable(id) {
    try {
        const response = await fetch(`/api/clientes/${id}/estado`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: "SUSPENDIDO"
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const upd = await response.json();

        console.log("Cliente suspendido:", upd);
        return true;
    } catch (error) {
        console.error("Error al suspender cliente:", error);
        return false;
    }
}

async function userEnable(id) {
    const boton = document.querySelector(`.user-enable[data-id="${id}"]`);

    if (!boton) {
        return;
    }

    const updated = await updUserEnable(id);

    if (updated) {
        window.location.reload();
    } else {
        alert("No se pudo habilitar el cliente");
    }
}


async function updUserEnable(id) {
    try {
        const response = await fetch(`/api/clientes/${id}/estado`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: "ACTIVO"
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const upd = await response.json();

        console.log("Cliente habilitado:", upd);
        return true;
    } catch (error) {
        console.error("Error al habilitar cliente:", error);
        return false;
    }
}
console.log("clientes.js cargado✅");