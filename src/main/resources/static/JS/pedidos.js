console.log ("pedidos.js cargado✅")
//Funcion para el modal de creacion
document.addEventListener("click", (event) => {
    if (event.target.closest("#module-pedidos")) {
        const botonCrear = event.target.closest("#new-order");
    
        if (botonCrear) {
            document.getElementById("modal-pedidos-create").classList.add("activo");
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
    const modal = document.getElementById("modal-pedidos-create");

    if (modal) {
        modal.classList.remove("activo");
    }
}

//Funcion para el modal de edicion
document.addEventListener("click", (event) => {
    if (event.target.closest("#module-pedidos")) {
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
    if (event.target.id === "form-edit-pedido") {
        event.preventDefault();
        actualizarInventario(event.target);
    }
});

function abrirModalEditar(id) {
    const boton = document.querySelector(`.btn-editar[data-id="${id}"]`);
    
    if (!boton) {
        return;
    }
    
    const fila = boton.closest("tr");
    const celdas = fila.querySelectorAll("td");

    document.getElementById("order-id").value = id;
    
    document.getElementById("edit-client").value = boton.dataset.cliente;
    document.getElementById("edit-date").value = celdas[2].textContent.trim();
    document.getElementById("edit-order-status").value = celdas[3].textContent.trim();
    
    document.getElementById("modal-pedidos").classList.add("activo");
}

async function actualizarInventario(form) {
    const id = document.getElementById("order-id").value;
    const datosFormulario = new FormData(form);
    const pedido = Object.fromEntries(datosFormulario);

    delete pedido.id_pedido;

    try {
        const response = await fetch(`/api/pedidos/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(pedido)
            }
        );

        if (!response.ok) {
            throw new Error(`Error HTTP: ${response.status}`);
        }

        const pedidoActualizado = await response.json();

        console.log("Pedido actualizado:", pedidoActualizado);
        cerrarModal();
        alert("Pedido actualizado correctamente");
    } catch (error) {
        console.error("Error al actualizar pedido:", error);
        alert("No se pudo actualizar el pedido");
    }
}

function cerrarModal() {
    const modal = document.getElementById("modal-pedidos");

    if (modal) {
        modal.classList.remove("activo");
    }
}