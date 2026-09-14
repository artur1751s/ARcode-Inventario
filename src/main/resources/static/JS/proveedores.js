console.log("proveedores.js cargado✅");
//Funcion para el modal de creacion
document.addEventListener("click", (event) => {
    if (event.target.closest("#module-proveedores")) {
        const botonCrear = event.target.closest("#new-supplier");
    
        if (botonCrear) {
            document.getElementById("modal-proveedores-create").classList.add("activo");
            return;
        }
    
        if (event.target.closest("#btn-close-modal-create")) {
            cerrarModalCrear();
            return;
        }
    
        if (event.target.closest("#btn-create-cancel")) {
            cerrarModalCrear();
            return;
        }
    }
});

function cerrarModalCrear() {
    const modal = document.getElementById("modal-proveedores-create");

    if (modal) {
        modal.classList.remove("activo");
    }
}

//Funcion para el modal de edicion
document.addEventListener("click", (event) => {
    if (event.target.closest("#module-proveedores")) {
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
    }
});

document.addEventListener("submit", (event) => {
    if (event.target.id === "form-edit-proveedores") {
        event.preventDefault();
        actualizarProveedor(event.target);
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
    document.getElementById("edit-company").value = celdas[3].textContent.trim();
    document.getElementById("edit-number").value = celdas[4].textContent.trim();
    document.getElementById("edit-email").value = celdas[5].textContent.trim();

    document.getElementById("modal-proveedores").classList.add("activo");
}

async function actualizarProveedor(form) {
    const id = document.getElementById("user-id").value;
    const datosFormulario = new FormData(form);
    const proveedor = Object.fromEntries(datosFormulario);

    delete proveedor.id_proveedor;

    try {
        const response = await fetch(`/api/proveedores/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(proveedor)
            }
        );

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const proveedorActualizado = await response.json();

        console.log("Proveedor actualizado:", proveedorActualizado);
        cerrarModal();
        alert("Proveedor actualizado correctamente");
    } catch (error) {
        console.error("Error al actualizar proveedor:", error);
        alert("No se pudo actualizar el proveedor");
    }
}

function cerrarModal() {
    const modal = document.getElementById("modal-proveedores");

    if (modal) {
        modal.classList.remove("activo");
    }
}

//Funcion boton de Deshabilitar/Habilitar Usuarios
document.addEventListener("click", (event) => {
    if (event.target.closest("#module-proveedores")) {
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
        alert("No se pudo suspender el proveedor");
    }
}

async function updUserDisable(id) {
    try {
        const response = await fetch(`/api/proveedores/${id}/estado`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: "SUSPENDIDO"
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const upd = await response.json();

        console.log("Proveedor suspendido:", upd);
        return true;
    } catch (error) {
        console.error("Error al suspender proveedor:", error);
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
        alert("No se pudo habilitar el proveedor");
    }
}


async function updUserEnable(id) {
    try {
        const response = await fetch(`/api/proveedores/${id}/estado`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: "ACTIVO"
        });

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const upd = await response.json();

        console.log("Proveedor habilitado:", upd);
        return true;
    } catch (error) {
        console.error("Error al habilitar proveedor:", error);
        return false;
    }
}